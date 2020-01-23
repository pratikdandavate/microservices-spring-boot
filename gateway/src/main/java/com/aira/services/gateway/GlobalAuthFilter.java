//package com.aira.services.gateway;
//
//import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;
//
//import java.net.URI;
//
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.web.server.ServerWebExchange;
//
//import reactor.core.publisher.Mono;
//
//@Configuration
//public class GlobalAuthFilter {
//
//    @Bean
//    @Order(-1)
//    public GlobalFilter a() {
//	return (exchange, chain) -> {
//	    ServerHttpRequest request = exchange.getRequest();
//
//	    URI uri = exchange.getRequiredAttribute(GATEWAY_REQUEST_URL_ATTR);
//
//	    // send access token and signup request without auth check.
//	    if (uri.getPath().equalsIgnoreCase("/oauth/token") || uri.getPath().equalsIgnoreCase("/signup")) {
//		return chain.filter(exchange);
//	    }
//
//	    if (!request.getHeaders().containsKey("Authorization")) {
//		return this.onError(exchange, "No Authorization header", HttpStatus.UNAUTHORIZED);
//	    }
//
////	    String authorizationHeader = request.getHeaders().get("Authorization").get(0);
//
////	    if (!this.isAuthorizationValid(authorizationHeader)) {
////		return this.onError(exchange, "Invalid Authorization header", HttpStatus.UNAUTHORIZED);
////	    }
//
//	    ServerHttpRequest modifiedRequest = exchange.getRequest().mutate().build();
//
//	    return chain.filter(exchange.mutate().request(modifiedRequest).build());
//
//	};
//    }
//
//    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
//	ServerHttpResponse response = exchange.getResponse();
//	response.setStatusCode(httpStatus);
//	return response.setComplete();
//    }
//
//    private boolean isAuthorizationValid(String authorizationHeader) {
//
//	// Logic for checking the authorization token
//	if (authorizationHeader.equalsIgnoreCase("Authorization"))
//	    return true;
//
//	return false;
//    }
//
//}
