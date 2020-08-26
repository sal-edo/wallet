package com.saledo.wallet.dto.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FieldValidationErrorDto {
	private String field;
	private String defaultMessage;
}
