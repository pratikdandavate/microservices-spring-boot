/**
 * 
 */
package com.airamatrix.airadhi.demo.tenant;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.HikariDataSource;

/**
 * @author Jaikishan Gurav
 *
 */
public final class DataSourceUtil {

    private static final Logger LOG = LoggerFactory.getLogger(DataSourceUtil.class);

    public static DataSource createAndConfigureDataSource(String tenantAlias,
	    DataSourceProperties dataSourceProperties) {
	HikariDataSource ds = new HikariDataSource();

	ds.setUsername(dataSourceProperties.getUsername());
	ds.setPassword(dataSourceProperties.getPassword());
	ds.setJdbcUrl(dataSourceProperties.getUrl() + "/" + tenantAlias);
	ds.setDriverClassName(dataSourceProperties.getDriverClassName());

	// Maximum waiting time for a connection from the pool
	ds.setConnectionTimeout(dataSourceProperties.getConnectionTimeout());

	// Minimum number of idle connections in the pool
	ds.setMinimumIdle(dataSourceProperties.getMinIdle());

	// Maximum number of actual connection in the pool
	ds.setMaximumPoolSize(dataSourceProperties.getMaxPoolSize());

	// Maximum time that a connection is allowed to sit idle in the pool
	ds.setIdleTimeout(dataSourceProperties.getIdleTimeout());

	// Setting up a pool name for each tenant datasource
	String tenantConnectionPoolName = tenantAlias + "-connection-pool";
	ds.setPoolName(tenantConnectionPoolName);
	LOG.info("Configured datasource:" + tenantAlias + ". Connection poolname:" + tenantConnectionPoolName);
	return ds;

    }

}
