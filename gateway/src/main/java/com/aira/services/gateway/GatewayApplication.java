package com.aira.services.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.security.oauth2.gateway.TokenRelayGatewayFilterFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

    public static void main(String[] args) {
	SpringApplication.run(GatewayApplication.class, args);
    }

    @Autowired
    private TokenRelayGatewayFilterFactory tokenRelayFilter;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
	return builder.routes()
		.route("authorization-server",
			r -> r.path("/oauth2/**").filters(f -> f.stripPrefix(1).hystrix(
				c -> c.setName("authorization-server-fallback").setFallbackUri("forward:/fallback")))
				.uri("lb://authorization-server"))
		.route("greeting-service", r -> r.path("/greeting**").filters(
			f -> f.hystrix(c -> c.setName("greeting-service-fallback").setFallbackUri("forward:/fallback")))
			.uri("lb://greeting-service"))
		.route("resource-server",
			r -> r.path("/me")
				.filters(f -> f.filter(tokenRelayFilter.apply()).hystrix(
					c -> c.setName("resource-server-fallback").setFallbackUri("forward:/fallback")))
				.uri("lb://resource-server"))
		.route("organ-service",
			r -> r.path("/api/organs/**")
				.filters(f -> f.filter(tokenRelayFilter.apply()).hystrix(
					c -> c.setName("organ-service-fallback").setFallbackUri("forward:/fallback")))
				.uri("lb://organ-service"))
		.build();
    }
}
