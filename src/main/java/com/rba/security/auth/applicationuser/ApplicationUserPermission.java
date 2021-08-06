package com.rba.security.auth.applicationuser;

public enum ApplicationUserPermission {
	
	USER_READ("user:read"),
	USER_WRITE("user:write"),
	ADMIN_READ("admin:read"),
	ADMIN_WRITE("admin:write");
	
	private final String permission;
	
	private ApplicationUserPermission(String permission) {
		// TODO Auto-generated constructor stub

		this.permission = permission;
	
	}

	public String getPermission() {
		return permission;
	}
	
}
