package com.rba.security.auth.applicationuser;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.rba.security.auth.role.Role;



public  class ApplicationUserBuilder {
	
	
	private String username;
	private String password;
	private Set<? extends GrantedAuthority> authorities;
	private boolean isAccountNonExpired;
	private boolean isAccountNonLocked;
	private boolean isCredentialsNonExpired;
	private boolean isEnabled;
	
	private ApplicationUserBuilder() {
		
	}
	
	public ApplicationUser build() {
		return new ApplicationUser(username, password, authorities, isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled);
	}
	
	public static ApplicationUserBuilder builder() {
		return new ApplicationUserBuilder();
	}
	
	public ApplicationUserBuilder username(String username) {
		this.username = username;
		return this;
	}
	
	public ApplicationUserBuilder password(String password) {
		this.password = password;
		return this;
	}
	
	public ApplicationUserBuilder authorities(Set<Role> roles) {
		Set<SimpleGrantedAuthority> collections = roles
				.stream()
				.map(p -> new SimpleGrantedAuthority(p.getName()))
				.collect(Collectors.toSet());
		this.authorities = collections;
		return this;
	}
	
	public ApplicationUserBuilder isAccountNonExpired(boolean isAccountNonExpired) {
		this.isAccountNonExpired = isAccountNonExpired;
		return this;
	}
	public ApplicationUserBuilder isAccountNonLocked(boolean isAccountNonLocked) {
		this.isAccountNonLocked = isAccountNonLocked;
		return this;
	}
	public ApplicationUserBuilder isCredentialsNonExpired(boolean isCredentialsNonExpired) {
		this.isCredentialsNonExpired = isCredentialsNonExpired;
		return this;
	}
	public ApplicationUserBuilder isEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
		return this;
	}

}
