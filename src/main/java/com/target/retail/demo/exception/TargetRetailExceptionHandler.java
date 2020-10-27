package com.target.retail.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TargetRetailExceptionHandler {
	
	
	@RequestMapping(produces = "application/json")
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(ProductNotFoundException.class)
	
	public ResponseEntity<CustomErrorResponse> handleProductNotFoundException(ProductNotFoundException ex) {
		CustomErrorResponse error = new CustomErrorResponse("NOT_FOUND_ERROR", ex.getMessage());
		
		 return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
}
