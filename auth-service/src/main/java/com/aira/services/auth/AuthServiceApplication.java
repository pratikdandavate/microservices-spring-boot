package com.aira.services.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = { "com.aira.services.auth.dao" })
public class AuthServiceApplication {

    public static void main(String[] args) {
	SpringApplication.run(AuthServiceApplication.class, args);
    }

}
