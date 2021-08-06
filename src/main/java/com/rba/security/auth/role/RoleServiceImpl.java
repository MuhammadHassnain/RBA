package com.rba.security.auth.role;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired RoleRepo roleRepo;
	
	
	
	
	
	

	@Override
	public void CreateRole(Role role) {

		Optional<Role> dRole = roleRepo.findByName(role.getName());
		
		if(!dRole.isPresent()) {
			roleRepo.save(role);
		}
		
	
	}


	@Override
	public void CreateRoles(Set<Role> roles) {
		for (Role role : roles) {
			CreateRole(role);
		}
		
	}


	@Override
	public Set<Role> findByNameIn(List<String> roles) {
		return roleRepo.findByNameIn(roles);
	}


	
	
	

}
