package com.saledo.wallet.dto.error;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Custom validation error response DTO which will return information about error which
 * cause binding exception and field for which it happened.
 */
@Data
@AllArgsConstructor
public class FieldValidationErrorDto {
	private String field;
	private String defaultMessage;
}
