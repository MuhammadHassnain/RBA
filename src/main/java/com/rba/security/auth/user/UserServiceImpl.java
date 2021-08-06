package com.rba.security.auth.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

	
	@Autowired UserRepo userRepo;
	
	//@Autowired PasswordEncoder passwordEncoder;
	
	
	@Override
	public void createUser(User user) {
		userRepo.save(user);
	}
	
	
	@Override	
	public Optional<User> findUserByName(String name){
		return userRepo.findByUserName(name);
	}

}
