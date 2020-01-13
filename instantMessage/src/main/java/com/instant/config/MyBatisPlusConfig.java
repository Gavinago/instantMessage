package com.instant.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;

/**
* 	类说明 
*
* @author grl
* @date 2019年11月27日  新建 
*/
@Configuration
public class MyBatisPlusConfig {

	@Bean
	public PaginationInterceptor paginationInterceptor() {
		PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
		paginationInterceptor.setDialectType("mysql");
		paginationInterceptor.setOverflow(true);
		return paginationInterceptor;
	}
}