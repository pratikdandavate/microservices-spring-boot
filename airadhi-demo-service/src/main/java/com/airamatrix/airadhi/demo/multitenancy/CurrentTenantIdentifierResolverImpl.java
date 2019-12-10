/**
 * 
 */
package com.airamatrix.airadhi.demo.multitenancy;

import java.util.Optional;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

import com.airamatrix.airadhi.demo.tenant.Tenant;
import com.airamatrix.airadhi.demo.tenant.TenantContextHolder;

/**
 * @author Jaikishan Gurav
 *
 */

public class CurrentTenantIdentifierResolverImpl implements CurrentTenantIdentifierResolver {

    @Override
    public String resolveCurrentTenantIdentifier() {
	String tenantAlias = Optional.ofNullable(TenantContextHolder.getTenant())
		.orElse(Tenant.DEFAULT_TENANT.getAlias());
	return tenantAlias;
    }

    @Override
    public boolean validateExistingCurrentSessions() {
	return true;
    }

}
