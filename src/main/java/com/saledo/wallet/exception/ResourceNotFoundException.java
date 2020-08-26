package com.saledo.wallet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	private static final String DEFAULT_MESSAGE = "Record not found inside DB.";

	public ResourceNotFoundException() {
		super(DEFAULT_MESSAGE);
	}

	public ResourceNotFoundException(String message) {
		super(message);
	}
}
