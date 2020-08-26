package com.saledo.wallet.util;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.saledo.wallet.exception.UnsupportedEnumValueException;

public enum ETransactionType {
	CREDIT(0, "Credit"), DEBIT(1, "Debit");

	private int code;
	private String label;

	ETransactionType(int code, String label) {
		this.code = code;
		this.label = label;
	}

	public int getCode() {
		return code;
	}

	@JsonValue
	public String getLabel() {
		return label;
	}

	@JsonCreator
	public static ETransactionType forValue(String transactionType)
			throws UnsupportedEnumValueException {
		for (ETransactionType type : ETransactionType.values()) {
			if (transactionType.toUpperCase().equals(type.toString())) {
				return type;
			}
		}
		throw new UnsupportedEnumValueException(
				Arrays.asList(ETransactionType.values()).toString());
	}
}
