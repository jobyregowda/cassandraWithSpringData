package com.target.retail.demo.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductNotFoundException  extends Exception{
	
	private static final long serialVersionUID = 5771930271059349019L;
	
	private HttpStatus httpStatus;
	
	public ProductNotFoundException(String message) {
		super(message);
		
	}
	public ProductNotFoundException(HttpStatus status, String message) {
		super(message);
		this.httpStatus = status;
	}

}
