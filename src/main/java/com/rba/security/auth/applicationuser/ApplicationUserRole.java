package com.rba.security.auth.applicationuser;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.common.collect.Sets;
import com.rba.security.auth.role.Role;

public enum ApplicationUserRole {

	ADMIN(Sets.newHashSet(
			ApplicationUserPermission.USER_READ,
			ApplicationUserPermission.USER_WRITE,
			ApplicationUserPermission.ADMIN_READ,
			ApplicationUserPermission.ADMIN_WRITE)),
	USER(Sets.newHashSet(
			ApplicationUserPermission.USER_READ,
			ApplicationUserPermission.USER_WRITE
			));
	
	
	
	private final Set<ApplicationUserPermission> permissions;

	ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
		this.permissions = permissions;	
	}

	public Set<ApplicationUserPermission> getPermissions() {
		return permissions;
	}
	
	public Set<Role> getRoleWithPermissions(){
		Set<Role> collections = getPermissions()
				.stream()
				.map(p -> new Role(p.getPermission().toString()))
				.collect(Collectors.toSet());
		collections.add(new Role("ROLE_" + this.name()));
		return collections;
	}
	
	public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
		// TODO Auto-generated method stub
		Set<SimpleGrantedAuthority> collections = getPermissions()
													.stream()
													.map(p -> new SimpleGrantedAuthority(p.getPermission()))
													.collect(Collectors.toSet());
		collections.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
		return collections;
	}



}
