package com.pratap.clinicals.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClinicalServiceException extends RuntimeException {

	private static final long serialVersionUID = 7266893778038671537L;

	public ClinicalServiceException(String message) {
		super(message);
	}

}
