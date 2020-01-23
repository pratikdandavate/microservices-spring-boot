/**
 * 
 */
package com.aira.services.gateway.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * @author Jaikishan Gurav
 *
 */
@EnableWebFluxSecurity
public class SecurityConfiguration {

    @Bean
    SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) throws Exception {
	http.csrf().disable().authorizeExchange().pathMatchers("/oauth2/**").permitAll().anyExchange().authenticated()
		.and().oauth2Login().and().oauth2ResourceServer().opaqueToken();
	return http.build();
    }

    @Bean
    CorsWebFilter corsWebFilter() {
	CorsConfiguration corsConfig = new CorsConfiguration();
	corsConfig.setAllowedOrigins(Arrays.asList("*"));
	corsConfig.setMaxAge(3600L);
	corsConfig.addAllowedMethod("*");
	corsConfig.addAllowedHeader("*");

	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	source.registerCorsConfiguration("/**", corsConfig);

	return new CorsWebFilter(source);
    }

}
