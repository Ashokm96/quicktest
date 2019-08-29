package com.quick.questions.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.quick.questions.ws.security.AppProperties;

@SpringBootApplication
public class QuickquestionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuickquestionsApplication.class, args);
	}
	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder) {
		
		return applicationBuilder.sources(QuickquestionsApplication.class);
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SpringApplicationContext springApplicationContext() {
		return new SpringApplicationContext();
	}
	
	@Bean(name="AppProperties")
	public AppProperties getAppProperties() {
		return new AppProperties();
	}
}
