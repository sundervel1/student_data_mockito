package com.cg.boot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cg.boot.exception.AuthenticationFailedException;
import com.cg.boot.exception.NotLoggedInException;
import com.cg.boot.exception.StudentNotFoundException;

@RestControllerAdvice
public class CentralizedExceptionHandler {
	@ExceptionHandler(StudentNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleStudentNotFound(StudentNotFoundException e) {		
		return e.getMessage();
	}
	@ExceptionHandler(AuthenticationFailedException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public String handleAuthenticationFailedException(AuthenticationFailedException e) {		
		return e.getMessage();
	}
	@ExceptionHandler(NotLoggedInException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public String handleNotLoggedInException(NotLoggedInException e) {		
		return e.getMessage();
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
	public String handleError(Exception e) {		
		return e.getMessage();
	}

}
