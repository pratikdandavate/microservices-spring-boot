/**
 * 
 */
package com.aira.services.resource.controller;

import java.security.Principal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jaikishan Gurav
 *
 */
@RestController
public class UserController {

    protected final Log logger = LogFactory.getLog(getClass());

    @GetMapping("/me")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Principal> get(final Principal principal) {
	return ResponseEntity.ok(principal);
    }
}
