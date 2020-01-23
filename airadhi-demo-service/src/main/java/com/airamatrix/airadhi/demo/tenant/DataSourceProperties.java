/**
 * 
 */
package com.airamatrix.airadhi.demo.tenant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Jaikishan Gurav
 *
 */
@Component
@ConfigurationProperties(prefix = "multitenancy.datasource")
public class DataSourceProperties {

    private String url;
    private String username;
    private String password;
    private String driverClassName;
    private int connectionTimeout;
    private int maxPoolSize;
    private int idleTimeout;
    private int minIdle;

    public String getUrl() {
	return url;
    }

    public void setUrl(String url) {
	this.url = url;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getDriverClassName() {
	return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
	this.driverClassName = driverClassName;
    }

    public int getConnectionTimeout() {
	return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
	this.connectionTimeout = connectionTimeout;
    }

    public int getMaxPoolSize() {
	return maxPoolSize;
    }

    public void setMaxPoolSize(int maxPoolSize) {
	this.maxPoolSize = maxPoolSize;
    }

    public int getIdleTimeout() {
	return idleTimeout;
    }

    public void setIdleTimeout(int idleTimeout) {
	this.idleTimeout = idleTimeout;
    }

    public int getMinIdle() {
	return minIdle;
    }

    public void setMinIdle(int minIdle) {
	this.minIdle = minIdle;
    }

}
