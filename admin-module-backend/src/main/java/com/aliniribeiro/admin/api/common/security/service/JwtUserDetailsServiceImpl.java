package com.aliniribeiro.admin.api.common.security.service;

import com.aliniribeiro.admin.api.model.user.UserEntity;
import com.aliniribeiro.admin.api.common.security.jwt.JwtUserFactory;
import com.aliniribeiro.admin.api.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<UserEntity> user = userRepository.findByEmail(email);
		
		if (user.isPresent()) {
			return JwtUserFactory.create(user.get());
		}

		throw new UsernameNotFoundException(String.format("No user found with username '%s';", email));
	}

}
