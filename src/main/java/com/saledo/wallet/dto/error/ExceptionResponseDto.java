package com.saledo.wallet.dto.error;

import java.time.Instant;
import java.util.List;

import lombok.Builder;
import lombok.Data;

/**
 * Custom error response DTO.
 */
@Data
@Builder
public class ExceptionResponseDto {
	private Instant timestamp;
	private String status;
	private String error;
	private List<FieldValidationErrorDto> errors;
	private String path;
	private String cause;
}
