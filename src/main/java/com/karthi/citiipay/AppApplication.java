package com.karthi.citiipay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableAutoConfiguration

//If the username and password are encrypted in ConnectionUtil-> there is no need of DataSourceAutoConfiguartion
@ServletComponentScan("com.karthi.citiipay")
public class AppApplication  extends SpringBootServletInitializer{
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AppApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

}
