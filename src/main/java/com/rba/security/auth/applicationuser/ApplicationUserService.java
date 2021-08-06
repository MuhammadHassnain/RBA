package com.rba.security.auth.applicationuser;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rba.security.auth.user.User;
import com.rba.security.auth.user.UserService;


@Service
public class ApplicationUserService implements UserDetailsService {
	
	@Autowired
	UserService userservice;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Optional<User> user = userservice.findUserByName(username);
		
		if(user.isPresent()) {
			
			User currUser  = user.get();
			
			
			ApplicationUser appUser  = ApplicationUserBuilder.builder()
							.username(currUser.getUsername())
							.password(currUser.getPassword())
							.isAccountNonExpired(currUser.isAccountNonExpired())
							.isAccountNonLocked(currUser.isAccountNonLocked())
							.isCredentialsNonExpired(currUser.isCredentialsNonExpired())
							.isEnabled(currUser.isEnabled())
							.authorities(currUser.getRoles())
							.build();
			
			return appUser;
			
			
			
			
		}else {
			throw new UsernameNotFoundException(username);
		}
		

	}

	
	
}
