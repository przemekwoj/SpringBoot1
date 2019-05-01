package com.przemo.RestAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCrypt;

@SpringBootApplication
@EnableJpaAuditing
public class RestApiApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(RestApiApplication.class, args);
	}

}
