/**
 * 
 */
package com.airamatrix.airadhi.demo.tenant;

/**
 * @author Jaikishan Gurav
 *
 */
public class Tenant {

    public static final Tenant DEFAULT_TENANT = new Tenant("demo");

    private String alias;

    private String issuerUri;

    private String jwkSetUri;

    public Tenant() {
    }

    public Tenant(String alias) {
	this.alias = alias;
    }

    public String getAlias() {
	return alias;
    }

    public void setAlias(String alias) {
	this.alias = alias;
    }

    public String getIssuerUri() {
	return issuerUri;
    }

    public void setIssuerUri(String issuerUri) {
	this.issuerUri = issuerUri;
    }

    public String getJwkSetUri() {
	return jwkSetUri;
    }

    public void setJwkSetUri(String jwkSetUri) {
	this.jwkSetUri = jwkSetUri;
    }
}
