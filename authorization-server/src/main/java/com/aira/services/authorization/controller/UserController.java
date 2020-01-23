/**
 * 
 */
package com.aira.services.authorization.controller;

import java.net.URI;
import java.util.Collections;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.aira.services.authorization.dao.IRoleRepository;
import com.aira.services.authorization.dao.IUserRepository;
import com.aira.services.authorization.exception.AppException;
import com.aira.services.authorization.model.Role;
import com.aira.services.authorization.model.RoleName;
import com.aira.services.authorization.model.User;
import com.aira.services.authorization.payload.ApiResponse;
import com.aira.services.authorization.payload.SignUpRequest;

/**
 * @author Jaikishan Gurav
 *
 */
@RestController
public class UserController {

    @Autowired
    IUserRepository userRepository;

    @Autowired
    IRoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {

	if (userRepository.existsByEmail(signUpRequest.getEmail())) {
	    return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"), HttpStatus.BAD_REQUEST);
	}

	// Creating user's account
	User user = new User(signUpRequest.getFirstName(), signUpRequest.getLastName(), signUpRequest.getEmail(),
		signUpRequest.getPassword());

	user.setPassword(passwordEncoder.encode(user.getPassword()));

	Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
		.orElseThrow(() -> new AppException("User Role not set."));

	user.setRoles(Collections.singleton(userRole));

	User result = userRepository.save(user);

	URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{username}")
		.buildAndExpand(result.getEmail()).toUri();

	return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }

}
