package com.saledo.wallet.dto;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BaseDataDto {
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Long id;
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Instant created;
}
