package com.instant.session;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.instant.authentication.UserSecurity;
import com.instant.constant.CommonConst;
import com.instant.model.TAccount;
import com.instant.utils.redis.RedisUtil;

import io.jsonwebtoken.Claims;

/**
 * 类说明
 *
 * @author grl
 * @date 2019年12月20日 新建
 */
public class AccountSessionHelper {

	@Autowired
	private UserSecurity userSecurity;
	
	@Autowired
	private RedisUtil redisUtil;

	public String getAppkey(HttpServletRequest request) {
		String appKey = request.getHeader(CommonConst.DEFAULT_APPKEY_KEY);
		if (StringUtils.isBlank(appKey)) {
			appKey = request.getParameter(CommonConst.DEFAULT_APPKEY_KEY);
		}
		return appKey;
	}

	public String getToken(HttpServletRequest request) {
		String token = request.getHeader(CommonConst.JSON_WEB_TOKEN_KEY);
		if (StringUtils.isBlank(token)) {
			token = request.getParameter(CommonConst.JSON_WEB_TOKEN_KEY);
		}
		return token;
	}

	public boolean verifyAppKey(HttpServletRequest request) {
		boolean valid = false;
		String appkey = getAppkey(request);
		if (StringUtils.isNotBlank(appkey)) {
			valid = verifyAppKey(appkey);
		}
		return valid;
	}

	public boolean verifyAppKey(String appKey) {
		boolean valid = false;
		TAccount account = userSecurity.getAccount(appKey);
		if (account != null) {
			valid = true;
		}
		return valid;
	}

	public boolean verifyToken(HttpServletRequest request) {
		String appkey = getAppkey(request);
		String token = getToken(request);
		return verifyToken(appkey, token);
	}

	public boolean verifyToken(String appKey, String token) {
		boolean valid = false;
		if (StringUtils.isNotBlank(appKey) && StringUtils.isNotBlank(token)) {
			TAccount account = userSecurity.getAccount(appKey);
			if (account != null) {
				String jwtSecret = account.getAccountSecretKey();
				Claims parseToken = userSecurity.parseToken(token, jwtSecret);
				if (parseToken != null) {
					valid = true;
				}
			}
		}
		return valid;
	}
	
	/**
	 * <p>
	 *   账号的存储 appkey:TAccount
	 * </p>
	 * @author grl
	 * @param appkey
	 * @param Account
	 */
	public void putAppkeyAndAccount(String appkey,TAccount Account) {
		if(StringUtils.isNotBlank(appkey) && Account != null) {
			JSONObject accountMap = getTAccountMap();
			accountMap.put(appkey, Account);
			redisUtil.hmset(CommonConst.DEFAULT_TACCOUNT_MAP_KEY, accountMap);
		}
	}
	/**
	 * <p>
	 *    获取 TAccount
	 * </p>
	 * @author grl
	 * @param appkey
	 * @return
	 */
	public TAccount getTAccountByAppkey(String appkey) {
		TAccount tAccount = null;
		if(StringUtils.isNotBlank(appkey)) {
			JSONObject accountMap = getTAccountMap();
			if(accountMap.containsKey(appkey)) {
				tAccount = (TAccount) accountMap.get(appkey);
			}
		}
		return tAccount;
	}
	/**
	 * <p>
	 * 	库中获取 TAccountMap
	 * 存储的是 appkey 和 TAccount的键值对
	 * </p>
	 * @author grl
	 * @return
	 */
	public JSONObject getTAccountMap() {
		JSONObject accountMap = null;
		if(redisUtil.hasKey(CommonConst.DEFAULT_TACCOUNT_MAP_KEY)) {
			Map<Object, Object> hmget = redisUtil.hmget(CommonConst.DEFAULT_TACCOUNT_MAP_KEY);
			String string = JSON.toJSONString(hmget);
			accountMap = JSON.parseObject(string);
		}
		if(accountMap == null) {
			accountMap = new JSONObject();
		}
		return accountMap;
	}
	/**
	 * <p>
	 * 用户信息存储 appkey appUserId TUser
	 * allUserMap = JSONObject<appkey,JSONObject<appUserId,TUser>>
	 * </p>
	 * @author grl
	 * @return
	 */
	public JSONObject getAllUserMap() {
		return null;
	}
	
}
