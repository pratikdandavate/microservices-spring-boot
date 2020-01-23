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
@ConfigurationProperties(prefix = "multitenancy.hibernate")
public class HibernateProperties {

    private String packagesToScan;

    private boolean showSql;

    private boolean formatSql;

    private String ddlAuto;

    public String getPackagesToScan() {
	return packagesToScan;
    }

    public void setPackagesToScan(String packagesToScan) {
	this.packagesToScan = packagesToScan;
    }

    public boolean isShowSql() {
	return showSql;
    }

    public void setShowSql(boolean showSql) {
	this.showSql = showSql;
    }

    public boolean isFormatSql() {
	return formatSql;
    }

    public void setFormatSql(boolean formatSql) {
	this.formatSql = formatSql;
    }

    public String getDdlAuto() {
	return ddlAuto;
    }

    public void setDdlAuto(String ddlAuto) {
	this.ddlAuto = ddlAuto;
    }

}
