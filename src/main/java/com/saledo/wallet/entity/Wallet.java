package com.saledo.wallet.entity;

import java.math.BigDecimal;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "WALLET")
public class Wallet extends BaseData {
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@Type(type = "org.hibernate.type.InstantType")
	@Column(name = "UPDATED", nullable = false)
	private Instant updated = Instant.now();

	@Column(name = "PLAYER_NAME", nullable = false)
	private String playerName;

	@Column(name = "BALANCE", precision = 16, scale = 2)
	private BigDecimal balance;

	public void updateBalance(BigDecimal transactionAmount) {
		this.updated = Instant.now();
		this.balance = this.balance.add(transactionAmount);
	}
}
