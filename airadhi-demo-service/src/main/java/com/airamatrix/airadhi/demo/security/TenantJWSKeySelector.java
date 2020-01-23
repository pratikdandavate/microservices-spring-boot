/**
 * 
 */
package com.airamatrix.airadhi.demo.security;

import java.net.URL;
import java.security.Key;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.airamatrix.airadhi.cache.redis.RedisClient;
import com.airamatrix.airadhi.demo.tenant.Tenant;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.KeySourceException;
import com.nimbusds.jose.proc.JWSAlgorithmFamilyJWSKeySelector;
import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.proc.JWTClaimsSetAwareJWSKeySelector;

/**
 * @author Jaikishan Gurav
 *
 */
@Component
public class TenantJWSKeySelector implements JWTClaimsSetAwareJWSKeySelector<SecurityContext> {

    private final Map<String, String> tenants = new HashMap<>();
    private final Map<String, JWSKeySelector<SecurityContext>> selectors = new HashMap<>();

    @Autowired
    private RedisClient<Tenant> redisClient;

    public TenantJWSKeySelector() {
	this.tenants.put("dev", "http://idp:9999/auth/realms/dev/protocol/openid-connect/certs");
	this.tenants.put("test", "http://idp:9999/auth/realms/test/protocol/openid-connect/certs");
    }

    @Override
    public List<? extends Key> selectKeys(JWSHeader jwsHeader, JWTClaimsSet jwtClaimsSet,
	    SecurityContext securityContext) throws KeySourceException {
	return this.selectors.computeIfAbsent(toTenant(jwtClaimsSet), this::fromTenant).selectJWSKeys(jwsHeader,
		securityContext);
    }

    private String toTenant(JWTClaimsSet claimSet) {
	return (String) claimSet.getClaim("tenant_id");
    }

    private JWSKeySelector<SecurityContext> fromTenant(String tenantAlias) {
	Tenant tenant = redisClient.getValue(tenantAlias);
	return Optional.ofNullable(tenant.getJwkSetUri()).map(this::fromUri)
		.orElseThrow(() -> new IllegalArgumentException("unknown tenant"));
    }

    private JWSKeySelector<SecurityContext> fromUri(String uri) {
	try {
	    return JWSAlgorithmFamilyJWSKeySelector.fromJWKSetURL(new URL(uri));
	} catch (Exception e) {
	    throw new IllegalArgumentException(e);
	}
    }

}
