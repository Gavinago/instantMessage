package com.instant.authentication;

import com.alibaba.fastjson.JSONObject;
import com.instant.model.TAccount;
import com.instant.model.TUser;

import io.jsonwebtoken.Claims;

/**
 * 类说明
 *
 * @author grl
 * @date 2019年12月19日 新建
 */
public interface UserSecurity {

	JSONObject getToken(String appKey, String appUserId);
	
	JSONObject refreshToken(String token, String jwtSecret);

	Claims parseToken(String token, String jwtSecret);
	
	TAccount getAccount(String appKey);
	
	JSONObject registerUser(String appKey, TUser user );
	
	boolean verifyTAccount(TAccount tAccount);
}
