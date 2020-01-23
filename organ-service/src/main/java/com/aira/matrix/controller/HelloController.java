package com.aira.matrix.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.aiit.ccmsresourcetokenservice.model.CustomPrincipal;

@RestController
@RequestScope
public class HelloController {

	@Value("${msg}")
	private String msg;

	@GetMapping("/organs/msg")
	public String sayHello() {
		return this.msg;
	}

	@GetMapping("/user")
	public ResponseEntity<Object> user(@AuthenticationPrincipal CustomPrincipal principal) {
//      Map<String, String> map = new LinkedHashMap<>();
//      map.put("name", principal.getName());
//      return map;
		System.out.println(principal.toString());
		System.out.println("Retrieved user with authorities: " + principal.getFirstName());
		return ResponseEntity.ok(principal);
	}
}
