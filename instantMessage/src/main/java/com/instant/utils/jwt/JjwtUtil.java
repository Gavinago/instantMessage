package com.instant.utils.jwt;

import java.util.Base64;
import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.instant.constant.CommonConst;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * 类说明
 *
 * @author grl
 * @date 2019年12月19日 新建
 */
public class JjwtUtil {

	// 由字符串生成加密key
	public static SecretKey generalKey(String jwtSecret) {
		// 本地的密码解码
		byte[] encodedKey = Base64.getEncoder().encode(jwtSecret.getBytes());
		// 根据给定的字节数组使用AES加密算法构造一个密钥
		SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
		return key;
	}

	// 创建jwt
	public static String generalJWTToken(String jwtSecret, String subject, Map<String, Object> claims, long expTime)
			throws Exception {
		// 指定header那部分签名的时候使用的签名算法，jjwt已经将这部分内容封装好了，只有{"alg":"HS256"}
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		// 生成JWT的时间
		Date issuedAt = new Date();
		// 生成签名的时候使用的秘钥secret，切记这个秘钥不能外露，是你服务端的私钥，在任何场景都不应该流露出去，一旦客户端得知这个secret，那就意味着客户端是可以自我签发jwt的
		SecretKey key = generalKey(jwtSecret);
		// 为payload添加各种标准声明和私有声明
		JwtBuilder builder = Jwts.builder() // 表示new一个JwtBuilder，设置jwt的body
				// 设置头部信息
                //.setHeader(header)
				// 如果有私有声明，一定要先设置自己创建的这个私有声明，这是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明
				.setClaims(claims) 
				// jti(JWTID)：jwt的唯一身份标识，根据业务需要，可以设置为一个不重复的值，主要用来作为一次性token，从而回避重放攻击
				// .setId(jwtId)
				//iat(issuedAt)：
				//jwt的签发时间
				.setIssuedAt(issuedAt) 
				// iss(issuer)：jwt签发者
				.setIssuer(CommonConst.JSON_WEB_TOKEN_ISSUER)
				// sub(subject)：jwt所面向的用户，放登录的用户名，一个json格式的字符串，可存放userid，roldid之类，作为用户的唯一标志
				.setSubject(subject)
				// 设置签名，使用的是签名算法和签名使用的秘钥
				.signWith(signatureAlgorithm, key); 
		// 设置过期时间
		if (expTime >= 0) {
			long exp = issuedAt.getTime() + expTime;
			builder.setExpiration(new Date(exp));
		}
		return builder.compact();
	}

	// 解密jwt
	public static Claims parseJWT(String jwt, String jwtSecret) throws Exception {
		SecretKey key = generalKey(jwtSecret); // 签名秘钥，和生成的签名的秘钥一模一样
		Claims claims = Jwts.parser() // 得到DefaultJwtParser
				.setSigningKey(key) // 设置签名的秘钥
				.parseClaimsJws(jwt).getBody(); // 设置需要解析的jwt
		return claims;
	}

}
