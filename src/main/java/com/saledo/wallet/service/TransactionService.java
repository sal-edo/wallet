package com.saledo.wallet.service;

import java.util.List;

import com.saledo.wallet.entity.Transaction;

public interface TransactionService {

	List<Transaction> getPlayerHistory(Long id);

	Transaction registerTransaction(Transaction transaction);

}
