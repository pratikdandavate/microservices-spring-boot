package com.aira.matrix.feign.intercomm;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.aira.matrix.vo.Organ;

@FeignClient(name = "organ-service",configuration = OrganClientConfiguration.class)
public interface OrganClient {

	    @GetMapping("/organs/{id}")
	    public Optional<Organ> getOrganById(@PathVariable(value = "id") Long organId);

}
