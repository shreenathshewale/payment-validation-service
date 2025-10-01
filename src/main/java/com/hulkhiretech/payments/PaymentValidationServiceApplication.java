package com.hulkhiretech.payments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
public class PaymentValidationServiceApplication { 

	public static void main(String[] args) {
		SpringApplication.run(PaymentValidationServiceApplication.class, args);
	}

}
