package com.rba.security.auth.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/***
 * A java interface to define function for 
 * 
 * */

@Repository
public interface UserRepo extends CrudRepository<User, Integer> {


	@Query(value = "SELECT * FROM user where username = ?1",nativeQuery = true)
	public Optional<User> findByUserName(String username) ;
	
	
	
	
	
	

}
