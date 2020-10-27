package com.target.retail.demo.exception;

import java.io.IOException;

import org.springframework.http.HttpStatus;

public class RestTemplateException extends IOException {
	
	private static final long serialVersionUID = 5771930271059349019L;
	
	private HttpStatus httpStatus;
	
	public RestTemplateException(String message) {
		super(message);
		
	}
	public RestTemplateException(HttpStatus status, String message) {
		super(message);
		this.httpStatus = status;
	}

}
