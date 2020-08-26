package com.saledo.wallet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UnsupportedEnumValueException extends RuntimeException {
	private static final String DEFAULT_MESSAGE = "Unsupported enum value sent inside request. Possible enum values: %s.";

	public UnsupportedEnumValueException(String message) {
		super(String.format(DEFAULT_MESSAGE, message));
	}
}
