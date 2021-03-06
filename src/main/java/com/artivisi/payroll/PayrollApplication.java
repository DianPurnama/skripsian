package com.artivisi.payroll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.dialect.springdata.SpringDataDialect;

@SpringBootApplication
public class PayrollApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayrollApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder(); }

	@Bean
	public SpringDataDialect springDataDialect(){return new SpringDataDialect();}
}
