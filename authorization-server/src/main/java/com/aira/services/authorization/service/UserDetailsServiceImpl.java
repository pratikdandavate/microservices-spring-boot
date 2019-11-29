/**
 * 
 */
package com.aira.services.authorization.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aira.services.authorization.dao.IUserRepository;
import com.aira.services.authorization.model.User;
import com.aira.services.authorization.payload.AuthUserDetail;

/**
 * @author Jaikishan Gurav
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	Optional<User> user = userRepository.findByEmail(email);

	user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + email));
	return user.map(AuthUserDetail::new).get();
    }

}
