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
//    @Value("${spring.datasource.url}")
//    private String dbUrl;
//    @Value("${spring.datasource.username}")
//    private String dbUsername;
//    @Value("${spring.datasource.password}")
//    private String dbPassword;
//    @Value("${spring.datasource.driverClassName}")
//    private String dbDriverClassName;
//    @Value("${spring.datasource.connectionTimeout}")
//    private int dbConnectionTimeout;
//    @Value("${spring.datasource.maxPoolSize}")
//    private int dbMaxPoolSize;
//    @Value("${spring.datasource.idleTimeout}")
//    private int dbIdleTimeout;
//    @Value("${spring.datasource.minIdle}")
//    private int dbMinIdle;

//    public static void setDbUrl(String dbUrl) {
//	DataSourceUtil.dbUrl = dbUrl;
//    }
//
//
//    public static void setDbUsername(String dbUsername) {
//	DataSourceUtil.dbUsername = dbUsername;
//    }
//
//
//    public static void setDbPassword(String dbPassword) {
//	DataSourceUtil.dbPassword = dbPassword;
//    }
//
//
//    public static void setDbDriverClassName(String dbDriverClassName) {
//	DataSourceUtil.dbDriverClassName = dbDriverClassName;
//    }
//
//
//    public static void setDbConnectionTimeout(int dbConnectionTimeout) {
//	DataSourceUtil.dbConnectionTimeout = dbConnectionTimeout;
//    }
//
//
//    public static void setDbMaxPoolSize(int dbMaxPoolSize) {
//	DataSourceUtil.dbMaxPoolSize = dbMaxPoolSize;
//    }
//
//
//    public static void setDbIdleTimeout(int dbIdleTimeout) {
//	DataSourceUtil.dbIdleTimeout = dbIdleTimeout;
//    }
//
//
//    public static void setDbMinIdle(int dbMinIdle) {
//	DataSourceUtil.dbMinIdle = dbMinIdle;
//    }

    public static DataSource createAndConfigureDataSource(String tenantAlias) {
	DataSourceUtil util = new DataSourceUtil();
	HikariDataSource ds = new HikariDataSource();
	ds.setUsername("root");
	ds.setPassword("root1234");
	ds.setJdbcUrl("jdbc:mysql://172.28.42.132:3306" + "/" + tenantAlias);
	ds.setDriverClassName("com.mysql.cj.jdbc.Driver");

	// Maximum waiting time for a connection from the pool
	ds.setConnectionTimeout(20000);

	// Minimum number of idle connections in the pool
	ds.setMinimumIdle(10);

	// Maximum number of actual connection in the pool
	ds.setMaximumPoolSize(10);

	// Maximum time that a connection is allowed to sit idle in the pool
	ds.setIdleTimeout(300000);

	// Setting up a pool name for each tenant datasource
	String tenantConnectionPoolName = tenantAlias + "-connection-pool";
	ds.setPoolName(tenantConnectionPoolName);
	LOG.info("Configured datasource:" + tenantAlias + ". Connection poolname:" + tenantConnectionPoolName);
	return ds;

    }

}
