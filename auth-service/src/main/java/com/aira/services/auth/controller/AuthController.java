package com.aira.services.auth.controller;

import java.net.URI;
import java.util.Collections;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.aira.services.auth.dao.RoleRepository;
import com.aira.services.auth.dao.UserRepository;
import com.aira.services.auth.exception.AppException;
import com.aira.services.auth.model.Role;
import com.aira.services.auth.model.RoleName;
import com.aira.services.auth.model.User;
import com.aira.services.auth.payload.ApiResponse;
import com.aira.services.auth.payload.JwtAuthenticationResponse;
import com.aira.services.auth.payload.LoginRequest;
import com.aira.services.auth.payload.SignUpRequest;
import com.aira.services.auth.security.JwtTokenProvider;

@RestController
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

	Authentication authentication = authenticationManager.authenticate(
		new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));

	SecurityContextHolder.getContext().setAuthentication(authentication);

	String jwt = tokenProvider.generateToken(authentication);
	return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
	if (userRepository.existsByUserName(signUpRequest.getUsername())) {
	    return new ResponseEntity(new ApiResponse(false, "Username is already taken!"), HttpStatus.BAD_REQUEST);
	}

	if (userRepository.existsByEmail(signUpRequest.getEmail())) {
	    return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"), HttpStatus.BAD_REQUEST);
	}

	// Creating user's account
	User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
		signUpRequest.getPassword());

	user.setPassword(passwordEncoder.encode(user.getPassword()));

	Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
		.orElseThrow(() -> new AppException("User Role not set."));

	user.setRoles(Collections.singleton(userRole));

	User result = userRepository.save(user);

	URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{username}")
		.buildAndExpand(result.getUserName()).toUri();

	return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }

    @GetMapping("/user")
    public String user() {
	return ("<h1>Welcome User</h1>");
    }

    @GetMapping("/admin")
    public String admin() {
	return ("<h1>Welcome Admin</h1>");
    }
}
