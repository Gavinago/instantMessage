package com.instant.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@EnableTransactionManagement
@EnableAspectJAutoProxy(proxyTargetClass = true)
@MapperScan(basePackages = { "com.instant.mapper"})
@ComponentScan(basePackages = {
		"com.instant.config",
		"com.instant.service.**",
		"com.instant.utils.redis.**",
		"com.instant.authentication.**",
		"com.instant.subsidiary.**"
		
})
@SpringBootApplication
public class InstantMessageApplication {

	public static void main(String[] args) {
		SpringApplication.run(InstantMessageApplication.class, args);
	}

}
