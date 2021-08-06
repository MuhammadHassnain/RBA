package com.rba.security.auth.role;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RoleRepo extends CrudRepository<Role, Integer>{
	
	
	Set<Role> findByNameIn(List<String> roles);
	
	Optional<Role> findByName(String name);
	
	

}
