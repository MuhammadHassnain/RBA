package com.rba.security.auth.jwt;

public class UsernameAndPasswordAuthRequest {
	
	private String username;
	private String password;
	public UsernameAndPasswordAuthRequest() {
		super();
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	
}
