/**
 * 
 */
package com.airamatrix.airadhi.demo.tenant;

/**
 * @author Jaikishan Gurav
 *
 */
public class TenantContextHolder {

    private static final ThreadLocal<String> CONTEXT = new ThreadLocal<>();

    public static String getTenant() {
	return CONTEXT.get();
    }

    public static void setTenant(String tenant) {
	CONTEXT.set(tenant);
    }

    public static void clearTenant() {
	CONTEXT.remove();
    }
}
