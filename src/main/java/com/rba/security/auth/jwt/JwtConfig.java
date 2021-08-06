package com.rba.security.auth.jwt;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.google.common.net.HttpHeaders;

@Component
@ConfigurationProperties(prefix = "application.jwt")
public class JwtConfig {
	
	
	private String secretKey;

	private String tokenPrefix;
	private Integer tokenExpirationDay;
	public JwtConfig() {
		super();
	}
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public String getTokenPrefix() {
		return tokenPrefix;
	}
	public void setTokenPrefix(String tokenPrefix) {
		this.tokenPrefix = tokenPrefix;
	}
	public Integer getTokenExpirationDay() {
		return tokenExpirationDay;
	}
	public void setTokenExpirationDay(Integer tokenExpirationDay) {
		this.tokenExpirationDay = tokenExpirationDay;
	}
	
	

	
	public String getAuthorizationHeader() {
		return HttpHeaders.AUTHORIZATION;
	}
	

}
