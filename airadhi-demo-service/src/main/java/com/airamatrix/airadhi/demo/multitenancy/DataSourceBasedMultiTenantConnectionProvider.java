/**
 * 
 */
package com.airamatrix.airadhi.demo.multitenancy;

import java.util.Map;
import java.util.TreeMap;

import javax.sql.DataSource;

import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.airamatrix.airadhi.demo.tenant.DataSourceUtil;
import com.airamatrix.airadhi.demo.tenant.TenantContextHolder;

/**
 * @author Jaikishan Gurav
 *
 */
@Configuration
public class DataSourceBasedMultiTenantConnectionProvider
	extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {

    private static final long serialVersionUID = 1L;

    /**
     * Map to store the tenant ids as key and the data source as the value
     */
    private Map<String, DataSource> dataSourcesMap = new TreeMap<>();

    public DataSourceBasedMultiTenantConnectionProvider() {
	dataSourcesMap.put("dev", DataSourceUtil.createAndConfigureDataSource("dev"));
	dataSourcesMap.put("demo", DataSourceUtil.createAndConfigureDataSource("demo"));
    }

    @Override
    protected DataSource selectAnyDataSource() {
	if (dataSourcesMap.isEmpty()) {
	    // TODO Add code to fetch tenants from keycloak or cache.
	}
	return this.dataSourcesMap.values().iterator().next();
    }

    @Override
    protected DataSource selectDataSource(String tenantAlias) {
	if (dataSourcesMap.isEmpty()) {
	    // TODO Add code to fetch tenants from keycloak or cache.
	}
	return this.dataSourcesMap.get(tenantAlias);
    }

    /**
     * Initialize tenantId based on the logged in user if the tenant Id got lost in
     * after form submission in a user session.
     * 
     * @param tenantIdentifier
     * @return tenantIdentifier
     */
    private String initializeTenantIfLost(String tenantAlias) {
	if (TenantContextHolder.getTenant() == null) {

	    SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();

	    TenantContextHolder.setTenant(tenantAlias);
	}

	if (tenantAlias != TenantContextHolder.getTenant()) {
	    tenantAlias = TenantContextHolder.getTenant();
	}
	return tenantAlias;
    }

}
