/**
 * 
 */
package com.airamatrix.airadhi.demo.security;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtIssuerValidator;
import org.springframework.stereotype.Component;

import com.airamatrix.airadhi.cache.redis.RedisClient;
import com.airamatrix.airadhi.demo.tenant.Tenant;

/**
 * @author Jaikishan Gurav
 *
 */
@Component
public class TenantJwtIssuerValidator implements OAuth2TokenValidator<Jwt> {

    private final Map<String, String> tenants = new HashMap<>();
    private final Map<String, JwtIssuerValidator> validators = new HashMap<>();

    @Autowired
    private RedisClient<Tenant> redisClient;

    public TenantJwtIssuerValidator() {
	this.tenants.put("dev", "http://idp:9999/auth/realms/dev");
	this.tenants.put("test", "http://idp:9999/auth/realms/test");
    }

    @Override
    public OAuth2TokenValidatorResult validate(Jwt token) {
	return this.validators.computeIfAbsent(toTenant(token), this::fromTenant).validate(token);
    }

    private String toTenant(Jwt jwt) {
	return jwt.getClaim("tenant_id");
    }

    private JwtIssuerValidator fromTenant(String tenantAlias) {
	Tenant tenant = redisClient.getValue(tenantAlias);
	return Optional.ofNullable(tenant.getIssuerUri()).map(JwtIssuerValidator::new)
		.orElseThrow(() -> new IllegalArgumentException("unknown tenant"));
    }

}
