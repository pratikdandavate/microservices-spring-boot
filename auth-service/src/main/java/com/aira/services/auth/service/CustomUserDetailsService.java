package com.aira.services.auth.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aira.services.auth.dao.UserRepository;
import com.aira.services.auth.model.User;
import com.aira.services.auth.model.UserPrincipal;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
	Optional<User> user = userRepository.findByUserNameOrEmail(userName, userName);

	user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));
	return user.map(UserPrincipal::new).get();
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
	User user = userRepository.findById(id)
		.orElseThrow(() -> new UsernameNotFoundException("User not found with id : " + id));

	return new UserPrincipal(user);
    }

}
