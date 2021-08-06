package com.rba.security.auth.jwt;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rba.exception.ApiRequestException;

import io.jsonwebtoken.Jwts;

public class JwtUsernameAndPasswordAuthFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authManager;
	
	private final JwtConfig jwtConfig;
	private final SecretKey secretKey;

	
	
	
	public JwtUsernameAndPasswordAuthFilter(AuthenticationManager authManager,JwtConfig jwtConfig,SecretKey secretKey) {
		this.authManager = authManager;
		this.jwtConfig = jwtConfig;
		this.secretKey = secretKey;
		this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
	}


	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		
		
		try {
			UsernameAndPasswordAuthRequest authRequest = new ObjectMapper().readValue(request.getInputStream(), UsernameAndPasswordAuthRequest.class);
			Authentication authentication = new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());
			
			return this.authManager.authenticate(authentication);
			
		} 
		catch (IOException e) {

			response.setContentType("application/json");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			throw new RuntimeException(e);
		}
		catch(AuthenticationException e) {
			System.out.println("TEST2");
			throw new ApiRequestException(e.getMessage());
		}
		
	}

	
	
	
	





	@Override
	protected void successfulAuthentication(HttpServletRequest request,
											HttpServletResponse response,
											FilterChain chain,
											Authentication authResult)
											
													throws IOException, ServletException {
		
		SecretKey  key = secretKey;
		String token = Jwts.builder().setSubject(authResult.getName())
						.claim("authorities", authResult.getAuthorities())
						.setIssuedAt(new Date())
						.setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getTokenExpirationDay())))
						.signWith(key)
						.compact();
		
		response.addHeader(jwtConfig.getAuthorizationHeader(), jwtConfig.getTokenPrefix() + token);
		
		
	}
	
	
	
	
	
	

}
