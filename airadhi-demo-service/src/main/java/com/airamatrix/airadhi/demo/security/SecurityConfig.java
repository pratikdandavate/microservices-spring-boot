/**
 * 
 */
package com.airamatrix.airadhi.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.ReactiveAuthenticationManagerResolver;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * @author Jaikishan Gurav
 *
 */
@EnableWebFluxSecurity
public class SecurityConfig {

    @Autowired
    ReactiveAuthenticationManagerResolver<ServerHttpRequest> authenticationManagerResolver;

    @Bean
    SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) throws Exception {
	http.csrf().disable().authorizeExchange().anyExchange().authenticated().and()
		.oauth2ResourceServer(o -> o.authenticationManagerResolver(this.authenticationManagerResolver));
	return http.build();
    }

}
