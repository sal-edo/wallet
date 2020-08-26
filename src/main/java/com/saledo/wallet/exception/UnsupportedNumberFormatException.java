package com.saledo.wallet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UnsupportedNumberFormatException extends RuntimeException {

	private static final String DEFAULT_MESSAGE = "Unsupported number format %s sent inside request. Please type number like *.##.";

	public UnsupportedNumberFormatException(String message) {
		super(String.format(DEFAULT_MESSAGE, message));
	}
}
