/**
 * 
 */
package com.aira.services.authorization.payload;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.aira.services.authorization.model.User;

/**
 * @author Jaikishan Gurav
 *
 */
public class AuthUserDetail extends User implements UserDetails, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 6991635292045982577L;

    public AuthUserDetail(User user) {
	super(user);
    }

    public AuthUserDetail() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
	List<GrantedAuthority> grantedAuthority = new ArrayList<>();

	getRoles().forEach(role -> {
	    grantedAuthority.add(new SimpleGrantedAuthority(role.getName().toString()));
	    role.getPermissions().forEach(permission -> {
		grantedAuthority.add(new SimpleGrantedAuthority(permission.getName().toString()));
	    });
	});
	return grantedAuthority;
    }

    @Override
    public String getPassword() {
	return super.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
	return super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
	return super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
	return super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
	return super.isEnabled();
    }

    @Override
    public String getUsername() {
	return super.getEmail();
    }
}
