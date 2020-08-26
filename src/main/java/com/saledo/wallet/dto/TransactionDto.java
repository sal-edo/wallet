package com.saledo.wallet.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.saledo.wallet.util.ETransactionType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TransactionDto extends BaseDataDto {
	@Schema(example = "Trans123")
	@NotBlank
	@Size(min = 6, max = 8, message = "It must be between 6 and 8 characters")
	@Pattern(regexp = "[a-zA-Z0-9]+", message = "It must not contain special characters")
	private String transactionId;

	@NotNull
	private ETransactionType transactionType;

	@Schema(example = "5.00", description = "format i.e. 5.00")
	@NotNull
	private BigDecimal transactionAmount;

	@NotNull
	private Long playerId;
}
