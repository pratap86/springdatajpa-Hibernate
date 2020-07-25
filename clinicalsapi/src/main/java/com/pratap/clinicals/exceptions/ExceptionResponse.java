package com.pratap.clinicals.exceptions;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class ExceptionResponse {

	private LocalDateTime timestamp;
	private String message;
	private List<String> errors;
	
	public ExceptionResponse(LocalDateTime timestamp, String message, List<String> errors) {
		this.timestamp = timestamp;
		this.message = message;
		this.errors = errors;
	}

	public ExceptionResponse(LocalDateTime timestamp, String message, String error) {
		this.timestamp = timestamp;
		this.message = message;
		errors = Arrays.asList(error);
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	
}
