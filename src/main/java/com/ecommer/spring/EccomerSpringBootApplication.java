package com.ecommer.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(scanBasePackages = {
		"com.ecommer.spring.controller","com.ecommer.spring.repository","com.ecommer.spring.services","com.ecommer.spring.servicesImplement"
})
public class EccomerSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(EccomerSpringBootApplication.class, args);
	}

}
