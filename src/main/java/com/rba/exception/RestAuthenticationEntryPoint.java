package com.rba.exception;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import io.jsonwebtoken.io.IOException;

public class RestAuthenticationEntryPoint  implements AuthenticationEntryPoint{

    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException, ServletException {
    
    	
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        System.out.println(authenticationException.getMessage());
        if(authenticationException instanceof BadCredentialsException) {
        	System.out.println("YOUR ARE FUCKING BAD");
        }
        try {
			response.getOutputStream().println("{ \"error\": \"" + authenticationException.getMessage() + "\" }");
//			response.getOutputStream().println(new ApiException("Authentication Exception", HttpStatus.UNAUTHORIZED, null).toString());
		} catch (java.io.IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


    }


}
