package com.rba;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.rba.security.auth.applicationuser.ApplicationUserRole;
import com.rba.security.auth.role.Role;
import com.rba.security.auth.role.RoleService;
import com.rba.security.auth.user.User;
import com.rba.security.auth.user.UserService;




@Component
public class RunAfterStartup {
	
	private final UserService userService;
	private final PasswordEncoder passwordEncoder;
	private final RoleService roleService;
	
	
	
	
	@Autowired
	public RunAfterStartup(UserService userService, PasswordEncoder passwordEncoder, RoleService roleService) {
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
		this.roleService = roleService;
	}





	@EventListener(ApplicationReadyEvent.class)
	public void runAfterStartup() {
		
		
		for (ApplicationUserRole role : EnumSet.allOf(ApplicationUserRole.class)) {
			Set<Role> roles = role.getRoleWithPermissions();
			roleService.CreateRoles(roles);
		}
		
		
		List<String> admin_roles = ApplicationUserRole.ADMIN.getRoleWithPermissions().stream()
									.map(item -> item.getName())
									.collect(Collectors.toList());
		
		Set<Role> roles = roleService.findByNameIn(admin_roles);
		User user = new User("admin",passwordEncoder.encode("admin"),true,true,true,true,roles);
		userService.createUser(user);

		
		List<String> user_roles = ApplicationUserRole.USER.getRoleWithPermissions().stream()
				.map(item -> item.getName())
				.collect(Collectors.toList());
		roles = roleService.findByNameIn(user_roles);
		user = new User("user",passwordEncoder.encode("user"),true,true,true,true,roles);
		userService.createUser(user);

		System.out.println("After startup");
	}

}
