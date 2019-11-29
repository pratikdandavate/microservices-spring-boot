package com.aira.matrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.aira.matrix.intercomm.UserFeignClientInterceptor;

import feign.RequestInterceptor;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@EnableResourceServer
public class SlideServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SlideServiceApplication.class, args);
	}

	@Bean
	public RequestInterceptor getUserFeignClientInterceptor() {
		return new UserFeignClientInterceptor();
	}
}
