package com.retail.ecom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.retail.ecom.aspect.LoginLogger;
import com.retail.ecom.aspect.OrderLogger;

@SpringBootApplication
@EnableScheduling
@EnableAspectJAutoProxy
public class Ecom1Application extends SpringBootServletInitializer {

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Ecom1Application.class);
    }
	
	public static void main(String[] args) {
		SpringApplication.run(Ecom1Application.class, args);
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
	@Bean
	public LoginLogger loginLogger() {
		return new LoginLogger();
	}
	
	@Bean
	public OrderLogger orderLogger() {
		return new OrderLogger();
	}
}
