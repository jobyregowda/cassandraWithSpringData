package com.target.retail.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//@EnableCassandraRepositories 
@ComponentScan(basePackages = "com.target.retail.demo")
public class TargetRetaildemoApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(TargetRetaildemoApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}


}
