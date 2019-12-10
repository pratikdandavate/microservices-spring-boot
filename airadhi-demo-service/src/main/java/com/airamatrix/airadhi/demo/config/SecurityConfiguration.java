/**
 * 
 */
package com.airamatrix.airadhi.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * @author Jaikishan Gurav
 *
 */
@EnableWebFluxSecurity
public class SecurityConfiguration {

    @Bean
    SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) throws Exception {
	http.csrf().disable().authorizeExchange().anyExchange().permitAll();
	return http.build();
    }
}
