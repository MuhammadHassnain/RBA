package com.rba.security.auth.role;

import java.util.List;
import java.util.Set;

public interface RoleService {
	
	
	void CreateRole(Role role);
	
	void CreateRoles(Set<Role> roles);
	
	Set<Role> findByNameIn(List<String> roles);



}
