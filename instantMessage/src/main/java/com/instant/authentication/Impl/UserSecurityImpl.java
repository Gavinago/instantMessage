package com.instant.authentication.Impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.instant.authentication.UserSecurity;
import com.instant.constant.CommonConst;
import com.instant.constant.ReturnMesg;
import com.instant.model.TAccount;
import com.instant.model.TUser;
import com.instant.service.TAccountService;
import com.instant.service.TUserService;
import com.instant.session.AccountSessionHelper;
import com.instant.utils.jwt.JjwtUtil;

import io.jsonwebtoken.Claims;

/**
 * 类说明
 *
 * @author grl
 * @date 2019年12月19日 新建
 */
@Component
public class UserSecurityImpl implements UserSecurity {

	private static final Logger log = LoggerFactory.getLogger(UserSecurityImpl.class);

	@Autowired
	private TAccountService tAccountService;
	@Autowired
	private TUserService tUserService;
	@Autowired
	private AccountSessionHelper sessionHelper;

	@Override
	public JSONObject getToken(String appKey, String appUserId) {
		// 用户状态 0- 冻结 1-正常 2-加入黑名单
		JSONObject data = getUserAndAccount(appKey, appUserId);
		if (data != null) {
			TUser user = (TUser) data.get(CommonConst.DEFAULT_TUSER_KEY);
			String userToken = user.getUserToken();
			data.clear();
			data.put(CommonConst.JSON_WEB_TOKEN_KEY, userToken);
			return data;
		}
		data = new JSONObject();
		data.put(CommonConst.DEFAULT_MSG_POP_KEY, ReturnMesg.Parameters_Error.getName());
		return data;
	}

	/**
	 * <p>
	 * 刷新token
	 * </p>
	 * 
	 * @author grl
	 * @param token
	 * @param jwtSecret
	 * @return
	 */
	@Override
	public JSONObject refreshToken(String token, String jwtSecret) {
		try {
			Claims parseToken = parseToken(token, jwtSecret);
			String appKey = (String) parseToken.get(CommonConst.DEFAULT_APPKEY_KEY);
			String appUserId = (String) parseToken.get(CommonConst.DEFAULT_APPUSERID_KEY);
			if (StringUtils.isNotBlank(appKey) && StringUtils.isNotBlank(appUserId)) {
				JSONObject data = getUserAndAccount(appKey, appUserId);
				if (data != null) {
					TUser user = (TUser) data.get(CommonConst.DEFAULT_TUSER_KEY);
					TAccount account = (TAccount) data.get(CommonConst.DEFAULT_TACCOUNT_KEY);
					return getToken(account, user);
				}
			}
		} catch (Exception e) {
			log.error(" token {} {}", token, ReturnMesg.Token_Expired.getName());
			e.printStackTrace();
		}
		JSONObject data = new JSONObject();
		data.put(CommonConst.DEFAULT_MSG_POP_KEY, ReturnMesg.Token_Expired.getName());
		return data;
	}

	@Override
	public Claims parseToken(String token, String jwtSecret) {
		try {
			return JjwtUtil.parseJWT(token, jwtSecret);
		} catch (Exception e) {
			log.error(" token {} {}", token, ReturnMesg.Token_Expired.getName());
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * <p>
	 * 获取可以正常访问的账号
	 * </p>
	 * 
	 * @author grl
	 * @param appKey
	 * @return
	 */
	@Override
	public TAccount getAccount(String appKey) {
		TAccount account = sessionHelper.getTAccountByAppkey(appKey);
		if (account == null) {
			account = tAccountService.selectTAccountByAppKey(appKey);
			if (verifyTAccount(account)) {
				sessionHelper.putAppkeyAndAccount(appKey, account);
			}
		}
		return account;
	}

	@Override
	public JSONObject registerUser(String appKey, TUser user) {
		TAccount account = getAccount(appKey);
		if (account != null) {
			user.setUserBelongKey(appKey);
			tUserService.insertSelective(user);
			return getToken(account, user);
		}

		return null;
	}

	@Override
	public boolean verifyTAccount(TAccount account) {
		boolean vaild = false;
		if (account != null) {
			// 0-冻结 1-正常
			Integer accountStatus = account.getAccountStatus();
			if (accountStatus != null && accountStatus.equals(1)) {
				Date accountExpireTime = account.getAccountExpireTime();
				Long nowTime = System.currentTimeMillis();
				// appKey 提前一分钟失效
				if (accountExpireTime != null && (accountExpireTime.getTime() - 1000 * 60) > nowTime) {
					vaild = true;
				}
			}
		}
		return vaild;
	}

	private JSONObject getToken(TAccount account, TUser user) {
		String jwtSecret = account.getAccountSecretKey();
		String appUserId = user.getAppUserId();
		String appKey = account.getAccountAppKey();
		JSONObject subjectJson = new JSONObject();
		String userNickname = user.getUserNickname();
		String userPortrait = user.getUserPortrait();
		Integer userId = user.getUserId();
		subjectJson.put(CommonConst.DEFAULT_APPUSERID_KEY, appUserId);
		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put(CommonConst.DEFAULT_APPUSERID_KEY, appUserId);
		claims.put(CommonConst.DEFAULT_APPKEY_KEY, appKey);
		claims.put(CommonConst.DEFAULT_TUSER_NICKNAME, userNickname);
		claims.put(CommonConst.DEFAULT_TUSER_PORTRAIT, userPortrait);
		claims.put(CommonConst.DEFAULT_TUSER_ID, userId);

		Date accountExpireTime = account.getAccountExpireTime();
		Long expTime = CommonConst.JSON_WEB_TOKEN_EXPTIME;
		if (accountExpireTime != null) {
			long accountExpireMillis = accountExpireTime.getTime();
			long currentTimeMillis = System.currentTimeMillis();
			long finalExpireMills = currentTimeMillis + expTime;
			if (finalExpireMills > accountExpireMillis) {
				expTime = accountExpireMillis - currentTimeMillis;
				try {
					String generalJWTToken = JjwtUtil.generalJWTToken(jwtSecret, subjectJson.toJSONString(), claims,
							expTime);
					subjectJson.clear();
					subjectJson.put(CommonConst.JSON_WEB_TOKEN_KEY, generalJWTToken);
					// 更换数据库中的token
					TUser tuser = new TUser();
					tuser.setUserId(userId);
					tuser.setUserToken(generalJWTToken);
					tUserService.updateSelective(tuser);
					return subjectJson;
				} catch (Exception e) {
					log.error("获取token 失败 account {}  user {}", JSON.toJSONString(account), JSON.toJSONString(user));
					e.printStackTrace();
				}
			}
		}
		subjectJson.clear();
		subjectJson.put(CommonConst.JSON_WEB_TOKEN_KEY, user.getUserToken());
		return subjectJson;
	}

	private JSONObject getUserAndAccount(String appKey, String appUserId) {
		TUser tUser = null;
		TAccount account = getAccount(appKey);
		if (account != null) {
			TUser user = tUserService.selectTUserByAppKeyAndAppUserId(appKey, appUserId);
			if (user != null) {
				Integer userStatus = user.getUserStatus();
				if (userStatus != null && userStatus.equals(1)) {
					JSONObject data = new JSONObject();
					data.put(CommonConst.DEFAULT_TUSER_KEY, tUser);
					data.put(CommonConst.DEFAULT_TACCOUNT_KEY, account);
					return data;
				}
			}
		}
		return null;
	}
}
