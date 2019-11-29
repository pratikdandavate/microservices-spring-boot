package com.aira.services.auth.model;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails {

    private static final long serialVersionUID = -944111753078776068L;
    private Long id;
    private String name;
    private String userName;
    private String email;
    private String password;
    private List<GrantedAuthority> authorities;

    public UserPrincipal(User user) {
	this.id = user.getId();
	this.name = user.getName();
	this.userName = user.getUserName();
	this.email = user.getEmail();
	this.password = user.getPassword();
	this.authorities = user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName().name()))
		.collect(Collectors.toList());
    }

    public Long getId() {
	return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
	return authorities;
    }

    @Override
    public String getPassword() {
	return password;
    }

    @Override
    public String getUsername() {
	return userName;
    }

    public String getName() {
	return name;
    }

    public String getEmail() {
	return email;
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
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((email == null) ? 0 : email.hashCode());
	result = prime * result + ((userName == null) ? 0 : userName.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	UserPrincipal other = (UserPrincipal) obj;
	if (email == null) {
	    if (other.email != null)
		return false;
	} else if (!email.equals(other.email))
	    return false;
	if (userName == null) {
	    if (other.userName != null)
		return false;
	} else if (!userName.equals(other.userName))
	    return false;
	return true;
    }

}
