package com.aira.services.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/fallback")
public class HystrixController {

//	@GetMapping
//    public ResponseEntity<List<?>> firstServiceFallback(){
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("fallback", "true");
//		return ResponseEntity.ok().headers(headers).body(Collections.emptyList());
//    }
	
	@GetMapping
	public Mono<String> fallback() {
        return Mono.just("fallback");
    }
}
