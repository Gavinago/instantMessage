package com.instant.config;



import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations= {"classpath:config/application-bean.xml","classpath:config/applicationContext-cache.xml"})
public class Config {
	 
}
