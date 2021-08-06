package com.rba.security.auth.jwt;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class JwtTokenVerifier extends OncePerRequestFilter{
	
	
	private final JwtConfig jwtConfig;
	private final SecretKey secretKey;
	
	

	public JwtTokenVerifier(JwtConfig jwtConfig,SecretKey secretKey) {
		this.jwtConfig = jwtConfig;
		this.secretKey = secretKey;
	}




	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authorizationHeader = request.getHeader("Authorization");
		if(authorizationHeader == null  || authorizationHeader == "") {
			filterChain.doFilter(request, response);
			return;
		}
		if(!authorizationHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		SecretKey key = secretKey;
		String token = authorizationHeader.replace(jwtConfig.getTokenPrefix(), "");
		
		try {
			
			
			Jws<Claims> claimJws = Jwts.parserBuilder()
									.setSigningKey(key).build()
									.parseClaimsJws(token);
			
			Claims body = claimJws.getBody();
			String username = body.getSubject();
			
			@SuppressWarnings("unchecked")
			var authorities  = (List<Map<String,String>>) body.get("authorities");
			
			Set<SimpleGrantedAuthority> grantedAuthorities = authorities.stream().map(m-> new SimpleGrantedAuthority(m.get("authority"))).collect(Collectors.toSet());
			
			Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, grantedAuthorities);

			SecurityContextHolder.getContext().setAuthentication(authentication);
			
		}catch(JwtException e) {
			throw new IllegalStateException(String.format("Token %s cannot be trusted", token));
		}
		
		filterChain.doFilter(request, response);
		
	}
	
	

}
