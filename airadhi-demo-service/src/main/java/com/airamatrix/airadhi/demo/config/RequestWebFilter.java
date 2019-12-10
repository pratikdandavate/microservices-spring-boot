/**
 * 
 */
package com.airamatrix.airadhi.demo.config;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import com.airamatrix.airadhi.demo.tenant.TenantContextHolder;

import reactor.core.publisher.Mono;

/**
 * @author Jaikishan Gurav
 *
 */
@Component
public class RequestWebFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
	String tenantAlias = exchange.getRequest().getHeaders().get("Tenant").get(0);

	TenantContextHolder.setTenant(tenantAlias);
	return chain.filter(exchange);
    }

}
