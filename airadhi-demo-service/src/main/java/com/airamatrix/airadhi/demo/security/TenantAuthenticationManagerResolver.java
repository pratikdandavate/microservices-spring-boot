/**
 * 
 */
package com.airamatrix.airadhi.demo.security;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.ReactiveAuthenticationManagerResolver;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoders;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtReactiveAuthenticationManager;
import org.springframework.stereotype.Component;

import com.airamatrix.airadhi.cache.redis.RedisClient;
import com.airamatrix.airadhi.demo.tenant.Tenant;
import com.airamatrix.airadhi.demo.tenant.TenantContextHolder;
import com.nimbusds.jwt.JWTParser;

import reactor.core.publisher.Mono;

/**
 * @author Jaikishan Gurav
 *
 */
@Component
public class TenantAuthenticationManagerResolver implements ReactiveAuthenticationManagerResolver<ServerHttpRequest> {
    private final CustomServerBearerTokenAuthenticationConverter bearerTokenConverter = new CustomServerBearerTokenAuthenticationConverter();

    private final Map<String, String> tenants = new HashMap<>();

    private final Map<String, ReactiveAuthenticationManager> authenticationManagers = new HashMap<>();

    @Autowired
    private RedisClient<Tenant> redisClient;

//    public TenantAuthenticationManagerResolver() {
//	this.tenants.put("dev", "http://idp:9999/auth/realms/dev");
//	this.tenants.put("test", "http://idp:9999/auth/realms/test");
//    }

    @Override
    public Mono<ReactiveAuthenticationManager> resolve(ServerHttpRequest request) {
	Mono<String> tenantIdMono = this.toTenant(request);
	Mono<ReactiveAuthenticationManager> authManagerMono = tenantIdMono
		.flatMap(tenantId -> this.fromTenant(tenantId));
	return Mono.zip(tenantIdMono, authManagerMono).filter(t -> t.getT1() != null && t.getT2() != null)
		.map(t -> this.authenticationManagers.putIfAbsent(t.getT1(), t.getT2()));
//		(tenantId, authManager) -> this.authenticationManagers.putIfAbsent(tenantId, authManager));

    }

    private Mono<String> toTenant(ServerHttpRequest request) {
//	ServerWebExchange exchange = new DefaultServerWebExchange(request, null, null, null, null);
	return this.bearerTokenConverter.convert(request)
		.map(authentication -> ((BearerTokenAuthenticationToken) authentication).getToken()).map(token -> {
		    String tenantId = null;
		    try {
			tenantId = (String) JWTParser.parse(token).getJWTClaimsSet().getClaim("tenant_id");
			TenantContextHolder.setTenant(tenantId);
		    } catch (ParseException e) {
			throw new IllegalArgumentException(e);
		    }
		    return tenantId;
		});

    }

    private Mono<ReactiveAuthenticationManager> fromTenant(String tenantAlias) {
	Tenant tenant = redisClient.getValue(tenantAlias);
//	String issuer = this.tenants.get(tenant);
	String issuer = tenant.getIssuerUri();
	ReactiveJwtDecoder jwtDecoder = ReactiveJwtDecoders.fromIssuerLocation(issuer);
	ReactiveAuthenticationManager reactiveAuthenticationManager = new JwtReactiveAuthenticationManager(jwtDecoder);
	return Mono.just(reactiveAuthenticationManager);
//	return Mono.just(this.tenants.get(tenant)).map(ReactiveJwtDecoders::fromIssuerLocation)
//		.map(JwtReactiveAuthenticationManager::new);
//		.doOnError(() -> new IllegalArgumentException("unknown tenant"))::authenticate;
//	return Optional.ofNullable(this.tenants.get(tenant)).map(ReactiveJwtDecoders::fromIssuerLocation)
//		.map(JwtReactiveAuthenticationManager::new)
//		.orElseThrow(() -> new IllegalArgumentException("unknown tenant"))::authenticate;
    }

}
