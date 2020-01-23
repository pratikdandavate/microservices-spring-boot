/**
 * 
 */
package com.airamatrix.airadhi.demo.multitenancy;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.sql.DataSource;

import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.airamatrix.airadhi.demo.tenant.DataSourceProperties;
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

    @Autowired
    private DataSourceProperties dataSourceProperties;

    private Set<String> tenantSet = new HashSet<>();

    public DataSourceBasedMultiTenantConnectionProvider() {
	tenantSet.add("dev");
	tenantSet.add("test");
//	dataSourcesMap.put("dev", DataSourceUtil.createAndConfigureDataSource("dev", dataSourceProperties));
//	dataSourcesMap.put("demo", DataSourceUtil.createAndConfigureDataSource("demo", dataSourceProperties));
    }

    @Override
    protected DataSource selectAnyDataSource() {
	if (dataSourcesMap.isEmpty()) {
	    // TODO Add code to fetch tenants from keycloak or cache.
	    tenantSet.stream().forEach(tenant -> this.dataSourcesMap.put(tenant,
		    DataSourceUtil.createAndConfigureDataSource(tenant, dataSourceProperties)));
	}
	return this.dataSourcesMap.values().iterator().next();

    }

    @Override
    protected DataSource selectDataSource(String tenantAlias) {
	// TODO Add code to fetch tenants from keycloak or cache.
	if (!this.dataSourcesMap.containsKey(tenantAlias)) {
	    dataSourcesMap.put(tenantAlias,
		    DataSourceUtil.createAndConfigureDataSource(tenantAlias, dataSourceProperties));
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
