package com.instant.utils.jwt;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.instant.model.TUser;

import io.jsonwebtoken.ExpiredJwtException;

/**
* 	类说明 
*
* @author grl
* @date 2019年12月19日  新建 
*/
//使用io.jsonwebtoken包
public class TestJjwt {

	public static void main(String[] args) {
		TUser user = new TUser();
		user.setUserId(10);
		user.setUserNickname("张三");
		user.setUserPortrait("imag111111.jpg");
		// jwt所面向的用户，放登录的用户名等
		String subject = JSON.toJSONString(user);
		JSONObject claims = new JSONObject();
		try {
			// "Jack"是jwt签发者，"李四"是jwt接收者
			String jwt = JjwtUtil.generalJWTToken("fagnrjhebg44758",subject,claims,6000L);
			System.out.println("JWT：" + jwt);
			System.out.println("JWT长度：" + jwt.length());
			System.out.println("\njwt三个组成部分中间payload部分的解密：");
//			Claims c = JjwtUtil.parseJWT("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI5NjVjMDdiMi0yMWUyLTQ5Y2QtYTQxMS0wOGJmNTQ0MWJiMGYiLCJpYXQiOjE1NzY3NTA1ODEsInN1YiI6IntcInVzZXJJZFwiOjEwLFwidXNlck5pY2tuYW1lXCI6XCLlvKDkuIlcIixcInVzZXJQb3J0cmFpdFwiOlwiaW1hZzExMTExMS5qcGdcIn0iLCJleHAiOjE1NzY3NTA1ODd9.XSDyLpbB3gsrvYoNQ2QXpOOpQ9RY7vWOJfd2YOA0JmA","fagnrjhebg44758");
/*			System.out.println("jti用户id：" + c.getId());
			System.out.println("iat登录时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getIssuedAt()));
			System.out.println("iss签发者：" + c.getIssuer());
			System.out.println("sub用户信息列表：" + c.getSubject());
			System.out.println("exp过期时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getExpiration()));
			System.out.println("aud接收者：" + c.getAudience());
			System.out.println("登录的用户名：" + c.get("username"));
			// 或
			System.out.println("登录的用户名：" + c.get("username", String.class));
			System.out.println("登录的密码：" + c.get("password", String.class));*/
		} catch(ExpiredJwtException e) {
			System.out.println("过期");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
