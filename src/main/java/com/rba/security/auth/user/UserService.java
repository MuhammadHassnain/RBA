package com.rba.security.auth.user;

import java.util.Optional;




public interface UserService {
	
	void createUser(User user);
	Optional<User> findUserByName(String name);
	

}
