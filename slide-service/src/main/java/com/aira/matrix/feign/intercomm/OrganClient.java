package com.aira.matrix.feign.intercomm;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.aira.matrix.vo.Organ;

import feign.Logger;


@FeignClient(name = "organ-service",fallback = OrganFeignImpl.class,configuration=OrganClientConfiguration.class)
public interface OrganClient {

	    @GetMapping("/organs/{id}")
	    public Optional<Organ> getOrganById(@PathVariable(value = "id") Long organId);
	   
}

@Component
class OrganFeignImpl implements OrganClient{
	
	@Override
	public Optional<Organ> getOrganById(Long organId) {
		System.out.println("In fallback method");
		return null;
	}

}

@Configuration
class OrganClientConfiguration {
	
	@Bean
	Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL;
	}

}