package com.saledo.wallet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class LowBalanceException extends RuntimeException {
	private static final String DEFAULT_MESSAGE = "Insufficient balance inside wallet.";

	public LowBalanceException() {
		super(DEFAULT_MESSAGE);
	}

	public LowBalanceException(String message) {
		super(message);
	}
}
