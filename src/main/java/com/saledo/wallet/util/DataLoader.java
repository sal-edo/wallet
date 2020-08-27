package com.saledo.wallet.util;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.saledo.wallet.entity.Transaction;
import com.saledo.wallet.entity.Wallet;
import com.saledo.wallet.repository.TransactionRepository;
import com.saledo.wallet.repository.WalletRepository;

/**
 * Populating DB data for testing purposes on application startup.
 */
@Component
public class DataLoader implements ApplicationRunner {
	private WalletRepository walletRepository;
	private TransactionRepository transactionRepository;

	@Autowired
	public DataLoader(WalletRepository walletRepository,
			TransactionRepository transactionRepository) {
		this.walletRepository = walletRepository;
		this.transactionRepository = transactionRepository;
	}

	public void run(ApplicationArguments args) {
		initWallets();
		initTransactions();
	}

	private List<Wallet> initWallets() {
		List<Wallet> wallets = new ArrayList<>();
		for (int i = 1; i <= 3; i++) {
			Wallet wallet = new Wallet(Instant.now(), String.format("Player %s", i),
					new BigDecimal(100 * i));
			wallets.add(wallet);
		}
		return walletRepository.saveAll(wallets);
	}

	private void initTransactions() {
		List<Transaction> transactions = new ArrayList<>();
		for (int i = 1; i <= 15; i++) {
			Transaction transaction = new Transaction(String.format("TRANS%s", i),
					ETransactionType.values()[i % 2], new BigDecimal(50 + 5 * i),
					walletRepository
							.findByPlayerName(String.format("Player %s", i % 3 + 1)));
			transactions.add(transaction);
		}
		transactionRepository.saveAll(transactions);
	}
}
