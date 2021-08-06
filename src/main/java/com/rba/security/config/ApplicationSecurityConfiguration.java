package com.rba.security.config;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.rba.exception.RestAuthenticationEntryPoint;
import com.rba.security.auth.applicationuser.ApplicationUserRole;
import com.rba.security.auth.applicationuser.ApplicationUserService;
import com.rba.security.auth.jwt.JwtConfig;
import com.rba.security.auth.jwt.JwtTokenVerifier;
import com.rba.security.auth.jwt.JwtUsernameAndPasswordAuthFilter;






@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	
	private final PasswordEncoder passwordEncoder;
	private final ApplicationUserService appUserServcie;
	private final JwtConfig jwtConfig;
	private final SecretKey secretKey;
	
	
	@Autowired
	public ApplicationSecurityConfiguration(PasswordEncoder passwordEncoder, ApplicationUserService appUserServcie,JwtConfig jwtConfig,SecretKey secretKey) {
		super();
		this.jwtConfig = jwtConfig;
		this.passwordEncoder = passwordEncoder;
		this.appUserServcie = appUserServcie;
		this.secretKey = secretKey;
	}



	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http
		.csrf().disable()
		.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.exceptionHandling()
		.authenticationEntryPoint(new RestAuthenticationEntryPoint())
		.and()
		.addFilter(new JwtUsernameAndPasswordAuthFilter(authenticationManager(),jwtConfig,secretKey))
		.addFilterAfter(new JwtTokenVerifier(jwtConfig,secretKey), JwtUsernameAndPasswordAuthFilter.class)
		.authorizeRequests()
		.antMatchers("/","/user","/login","index","/css/*","/js/*").permitAll()
		.antMatchers("/ex").hasRole(ApplicationUserRole.ADMIN.name())
//		.antMatchers("/").hasRole(ApplicationUserRole.ADMIN.name())
		//.antMatchers("/api/v1/test/company/**").hasAnyAuthority(ApplicationUserRole.COMPANY.getGrantedAuthorities().toString())
		//.antMatchers("/api/v1/test/admin/**").hasRole(ApplicationUserRole.ADMIN.name())
		.anyRequest()
		.authenticated();
	}



	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(this.appUserServcie).passwordEncoder(this.passwordEncoder);

	}

}
