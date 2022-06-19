package com.ty.hospitalapi.exception;

public class NoIdFoundException extends RuntimeException {
	
	private String message = "Id does not exist";
	public NoIdFoundException() {}
	public NoIdFoundException(String message) {
		this.message=message;
	}
	
	public String getMessage() {
		return message;
	}
}
