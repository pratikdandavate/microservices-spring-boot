/**
 * 
 */
package com.airamatrix.airadhi.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airamatrix.airadhi.demo.payload.AiradhiRequest;
import com.airamatrix.airadhi.demo.payload.AiradhiResponse;
import com.airamatrix.airadhi.demo.service.DemoService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Jaikishan Gurav
 *
 */
@RestController
@RequestMapping(path = "/demo")
public class DemoController {

    @Autowired
    DemoService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<AiradhiResponse> add(@RequestBody AiradhiRequest request) {
	return service.add(request);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<AiradhiResponse> get() {

	return null;
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    Mono<AiradhiResponse> getById(@PathVariable String id) {
	return null;
    }

}
