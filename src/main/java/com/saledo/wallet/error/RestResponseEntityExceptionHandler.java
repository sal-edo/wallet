package com.saledo.wallet.error;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.saledo.wallet.dto.error.ExceptionResponseDto;
import com.saledo.wallet.dto.error.FieldValidationErrorDto;

/**
 * Custom exception handler. Implemented to be able to use a custom response DTO object in
 * case of error, mostly in case of unsupported formats sent in the request (validation
 * error). Default error response object contains to much information that we don't need.
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String VALIDATING_FIELD_ERR = "Error while validating fields.";

	public RestResponseEntityExceptionHandler() {
		super();
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
			@Nullable Object body, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		if (body == null) {
			body = ExceptionResponseDto.builder().timestamp(Instant.now())
					.cause(ex.getMessage()).status(String.valueOf(status.value()))
					.error(status.getReasonPhrase())
					.path(((ServletWebRequest) request).getRequest().getRequestURI())
					.build();
		}
		return new ResponseEntity(body, headers, status);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		ExceptionResponseDto body = ExceptionResponseDto.builder()
				.timestamp(Instant.now()).cause(VALIDATING_FIELD_ERR)
				.status(String.valueOf(status.value())).error(status.getReasonPhrase())
				.path(((ServletWebRequest) request).getRequest().getRequestURI()).build();

		BindingResult result = ex.getBindingResult();
		if (result.hasErrors()) {
			List<FieldValidationErrorDto> fieldValidationErrors = result.getFieldErrors()
					.stream().map(e -> new FieldValidationErrorDto(e.getField(),
							e.getDefaultMessage()))
					.collect(Collectors.toList());
			body.setErrors(fieldValidationErrors);
		}

		return new ResponseEntity(body, headers, status);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(
			HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		ExceptionResponseDto body = ExceptionResponseDto.builder()
				.timestamp(Instant.now()).cause(ex.getRootCause().getMessage())
				.status(String.valueOf(status.value())).error(status.getReasonPhrase())
				.path(((ServletWebRequest) request).getRequest().getRequestURI()).build();
		return new ResponseEntity(body, headers, status);
	}

}
