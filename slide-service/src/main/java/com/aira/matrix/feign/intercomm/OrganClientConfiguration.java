package com.aira.matrix.feign.intercomm;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;

import feign.Logger;
import feign.RequestInterceptor;

@Configuration
public class OrganClientConfiguration {

//	@Value("${security.oauth2.client.access-token-uri}")
//	private String accessTokenUri;
	@Value("${security.oauth2.client.client-id}")
	private String clientId;
	@Value("${security.oauth2.client.client-secret}")
	private String clientSecret;
//	@Value("${security.oauth2.client.scope}")
//	private String scope;

	@Bean
	RequestInterceptor oauth2FeignRequestInterceptor() {
		return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), resource());
	}

	@Bean
	Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL;
	}

	private OAuth2ProtectedResourceDetails resource() {
		ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
		resourceDetails.setUsername("pratik");
		resourceDetails.setPassword("pratik123");
		resourceDetails.setAccessTokenUri("http://localhost:9191/oauth/token");
		resourceDetails.setClientId(clientId);
		resourceDetails.setClientSecret(clientSecret);
		resourceDetails.setGrantType("password");
		resourceDetails.setScope(Arrays.asList("READ","WRITE"));
		return resourceDetails;
	}

}