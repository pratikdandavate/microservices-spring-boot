/**
 * 
 */
package com.aira.services.resource.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

/**
 * @author Jaikishan Gurav
 *
 */
@Configuration
public class ApplicationConfiguration {

    @Primary
    @Bean
    public RemoteTokenServices tokenServices() {
	final RemoteTokenServices tokenService = new RemoteTokenServices();
	tokenService.setCheckTokenEndpointUrl("http://localhost:8080/oauth/check_token");
	tokenService.setClientId("clientId");
	tokenService.setClientSecret("secret");
	return tokenService;
    }
}
