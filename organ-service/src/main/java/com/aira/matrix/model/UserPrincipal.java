package com.aira.matrix.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


    private String firstName;

    private String lastName;

    private String email;


    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal( String firstName, String lastName, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.authorities = authorities;
    }


    public String getfirstName() {
        return firstName;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getlastName() {
        return lastName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


	@Override
	public String getPassword() {
		return null;
	}


	@Override
	public String toString() {
		return "UserPrincipal [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", authorities=" + authorities + "]";
	}

}