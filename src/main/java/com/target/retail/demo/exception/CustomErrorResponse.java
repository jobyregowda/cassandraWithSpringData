package com.target.retail.demo.exception;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomErrorResponse {

	String errorMsg;
	String statusCode;
	int status;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	LocalDateTime datetime;
	
	public CustomErrorResponse(String statusCode, String errorMsg) {
		super();
		this.errorMsg = errorMsg;
		this.statusCode = statusCode;
	}
}
