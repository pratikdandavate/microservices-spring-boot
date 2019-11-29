/**
 * 
 */
package com.aira.services.authorization.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * @author Jaikishan Gurav
 *
 */
@Configuration
public class ApplicationConfiguration {

    @Bean
    public MessageSource messageSource() {
	ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	messageSource.setBasename("classpath:message");
	messageSource.setDefaultEncoding("UTF-8");
	return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean validator(MessageSource messageSource) {
	LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
	bean.setValidationMessageSource(messageSource);
	return bean;
    }
}
