package com.shivan.app.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerRestExceptionHandler {
	
	// Add an exception handler for CustomerNotFoundException
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException (CustomerNotFoundException exc) {
		
		//create CustomerErrorResponse
		
		CustomerErrorResponse error = new CustomerErrorResponse(HttpStatus.NOT_FOUND.value(), 
																exc.getMessage(), 
																System.currentTimeMillis());
		
			return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	
	}
	
	//Add exception handler for any other exception
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException (Exception exc) {
		
		//create CustomerErrorResponse
		
		CustomerErrorResponse error = new CustomerErrorResponse(HttpStatus.BAD_REQUEST.value(), 
																exc.getMessage(), 
																System.currentTimeMillis());
		
			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	// add mapping for POST / customers - add new customer
	
	
	
	

}
