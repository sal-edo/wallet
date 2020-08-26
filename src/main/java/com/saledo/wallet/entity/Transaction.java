package com.saledo.wallet.entity;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.saledo.wallet.util.ETransactionType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "TRANSACTION")
public class Transaction extends BaseData {
	@Column(name = "TRANSACTION_ID", nullable = false, unique = true)
	private String transactionId;

	@Column(name = "TYPE", nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private ETransactionType transactionType;

	@Column(name = "AMOUNT", precision = 16, scale = 2)
	private BigDecimal transactionAmount;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "WALLET_ID", nullable = false)
	private Wallet wallet;

	@Transient
	private Long playerId;

	public Transaction(String transactionId, ETransactionType transactionType,
			BigDecimal transactionAmount, Wallet wallet) {
		this.transactionId = transactionId;
		this.transactionType = transactionType;
		this.transactionAmount = transactionAmount;
		this.wallet = wallet;
	}
}
