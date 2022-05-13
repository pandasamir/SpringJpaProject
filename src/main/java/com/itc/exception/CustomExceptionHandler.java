package com.itc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
	@ExceptionHandler(value = EmployeeNotFoundException.class)
	public ResponseEntity<ErrorResponse> method1(EmployeeNotFoundException e) {
		HttpStatus stat = HttpStatus.NOT_FOUND;
		String message = e.getMessage();
		ErrorResponse errorResponse = new ErrorResponse(stat, message);
		ResponseEntity<ErrorResponse> responseEntity = new ResponseEntity<ErrorResponse>(errorResponse,
				HttpStatus.NOT_FOUND);
		return responseEntity;

	}

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ErrorResponse> method2(Exception e) {
		HttpStatus stat = HttpStatus.INTERNAL_SERVER_ERROR;
		String message = e.getMessage();
		ErrorResponse errorResponse = new ErrorResponse(stat, message);
		ResponseEntity<ErrorResponse> responseEntity = new ResponseEntity<ErrorResponse>(errorResponse,
				HttpStatus.INTERNAL_SERVER_ERROR);
		return responseEntity;
	}
}
