package com.rba.exception;

import java.nio.file.AccessDeniedException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
	
	@ExceptionHandler(value = {ApiRequestException.class})
	public ResponseEntity<Object> handleApiRequestException(ApiRequestException e){
		
		HttpStatus badRequest = HttpStatus.BAD_REQUEST;
		ApiException apiException = new ApiException(e.getMessage(), badRequest , ZonedDateTime.now(ZoneId.of("Z")));
		return new ResponseEntity<>(apiException,badRequest);
	}
	
	
	@ExceptionHandler(value = {AuthenticationException.class})
	public ResponseEntity<Object> handleAuthRequestException(ApiRequestException e){
		// 1. Create payload containing exception details
		
		
		System.out.println("handleAuthRe");
		
		HttpStatus badRequest = HttpStatus.BAD_REQUEST;
		
		ApiException apiException = new ApiException(e.getMessage(), badRequest , ZonedDateTime.now(ZoneId.of("Z")));
		//2 . Return the response entity
		return new ResponseEntity<>(apiException,badRequest);
	}
	
	@ExceptionHandler(value = {AccessDeniedException.class})
	public ResponseEntity<Object> AccessDeniedException(ApiRequestException e){
		// 1. Create payload containing exception details
		
		
		System.out.println("access denied");
		
		HttpStatus badRequest = HttpStatus.BAD_REQUEST;
		
		ApiException apiException = new ApiException(e.getMessage(), badRequest , ZonedDateTime.now(ZoneId.of("Z")));
		//2 . Return the response entity
		return new ResponseEntity<>(apiException,badRequest);
	}
}
