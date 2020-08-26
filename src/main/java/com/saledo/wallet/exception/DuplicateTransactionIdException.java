package com.saledo.wallet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DuplicateTransactionIdException extends RuntimeException {
	private static final String DEFAULT_MESSAGE = "Duplicated transaction id sent inside request.";

	public DuplicateTransactionIdException() {
		super(DEFAULT_MESSAGE);
	}

	public DuplicateTransactionIdException(String message) {
		super(message);
	}
}
