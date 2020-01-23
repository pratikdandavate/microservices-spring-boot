/**
 * 
 */
package com.airamatrix.airadhi.demo.multitenancy;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;

import org.hibernate.MultiTenancyStrategy;
import org.hibernate.cfg.Environment;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.airamatrix.airadhi.demo.tenant.HibernateProperties;

/**
 * @author Jaikishan Gurav
 *
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = { "com.airamatrix.airadhi" })
@EnableJpaRepositories(basePackages = {
	"com.airamatrix.airadhi" }, entityManagerFactoryRef = "tenantEntityManagerFactory", transactionManagerRef = "tenantTransactionManager")
public class TenantHibernateConfig {

    @Autowired
    private HibernateProperties properties;

    @Bean(name = "tenantJpaVendorAdapter")
    public JpaVendorAdapter jpaVendorAdapter() {
	return new HibernateJpaVendorAdapter();
    }

    @Bean(name = "tenantTransactionManager")
    public JpaTransactionManager transactionManager(EntityManagerFactory tenantEntityManager) {
	JpaTransactionManager transactionManager = new JpaTransactionManager();
	transactionManager.setEntityManagerFactory(tenantEntityManager);
	return transactionManager;
    }

    @Bean(name = "datasourceBasedMultitenantConnectionProvider")
    public MultiTenantConnectionProvider multiTenantConnectionProvider() {
	// Autowires the multi connection provider
	return new DataSourceBasedMultiTenantConnectionProvider();
    }

    @Bean(name = "currentTenantIdentifierResolver")
    public CurrentTenantIdentifierResolver currentTenantIdentifierResolver() {
	return new CurrentTenantIdentifierResolverImpl();
    }

    /**
     * Creates the entity manager factory bean which is required to access the JPA
     * functionalities provided by the JPA persistence provider, i.e. Hibernate in
     * this case.
     * 
     * @param connectionProvider
     * @param tenantResolver
     * @return
     */
    @Bean(name = "tenantEntityManagerFactory")
    @ConditionalOnBean(name = "datasourceBasedMultitenantConnectionProvider")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
	    @Qualifier("datasourceBasedMultitenantConnectionProvider") MultiTenantConnectionProvider multiTenantconnectionProvider,
	    @Qualifier("currentTenantIdentifierResolver") CurrentTenantIdentifierResolver currentTenantIdentifierResolver) {

	Map<String, Object> jpaPropertiesMap = new HashMap<>();

	// Properties related to MultiTenancyStrategy Schema
	jpaPropertiesMap.put(Environment.MULTI_TENANT, MultiTenancyStrategy.SCHEMA);
	jpaPropertiesMap.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, multiTenantconnectionProvider);
	jpaPropertiesMap.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, currentTenantIdentifierResolver);

	jpaPropertiesMap.put(Environment.FORMAT_SQL, properties.isFormatSql());
	jpaPropertiesMap.put(Environment.SHOW_SQL, properties.isShowSql());
	jpaPropertiesMap.put(Environment.HBM2DDL_AUTO, properties.getDdlAuto());

	LocalContainerEntityManagerFactoryBean emfBean = new LocalContainerEntityManagerFactoryBean();
	// All tenant related entities, repositories and service classes must be scanned
	emfBean.setPackagesToScan(properties.getPackagesToScan());
	emfBean.setJpaVendorAdapter(jpaVendorAdapter());
	emfBean.setPersistenceUnitName("tenantdb-persistence-unit");

	// ImprovedNamingStrategy is deprecated and unsupported in Hibernate 5
	// properties.put("hibernate.ejb.naming_strategy",
	// "org.hibernate.cfg.ImprovedNamingStrategy");
//	properties.put(org.hibernate.cfg.Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
//

	emfBean.setJpaPropertyMap(jpaPropertiesMap);
	return emfBean;
    }

}
