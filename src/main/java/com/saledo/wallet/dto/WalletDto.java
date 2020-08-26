package com.saledo.wallet.dto;

import java.math.BigDecimal;
import java.time.Instant;

import lombok.Data;

@Data
public class WalletDto extends BaseDataDto {
	private Instant updated;
	private String playerName;
	private BigDecimal balance;
}
