/**
 * 
 */
package com.aira.services.tenant.payload;

import java.util.Set;

/**
 * @author Jaikishan Gurav
 *
 */
public class UserRole {

    private String emailId;

    private Set<Role> roles;

    public String getEmailId() {
	return emailId;
    }

    public void setEmailId(String emailId) {
	this.emailId = emailId;
    }

    public Set<Role> getRoles() {
	return roles;
    }

    public void setRoles(Set<Role> roles) {
	this.roles = roles;
    }

}
