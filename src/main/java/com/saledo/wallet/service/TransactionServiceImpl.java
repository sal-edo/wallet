package com.saledo.wallet.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.saledo.wallet.entity.Transaction;
import com.saledo.wallet.entity.Wallet;
import com.saledo.wallet.exception.DuplicateTransactionIdException;
import com.saledo.wallet.exception.LowBalanceException;
import com.saledo.wallet.exception.ResourceNotFoundException;
import com.saledo.wallet.repository.TransactionRepository;
import com.saledo.wallet.repository.WalletRepository;
import com.saledo.wallet.util.ETransactionType;

@Service("transactionService")
public class TransactionServiceImpl implements TransactionService {

	private static final String NO_TRANSACTIONS_FOR_PLAYER = "No transcations found inside DB for player with id = %s.";

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	WalletRepository walletRepository;

	@Override
	public List<Transaction> getPlayerHistory(Long id) {
		List<Transaction> transactions = transactionRepository.findByWalletId(id);
		if (transactions.isEmpty()) {
			throw new ResourceNotFoundException(
					String.format(NO_TRANSACTIONS_FOR_PLAYER, id));
		}
		return transactions;
	}

	@Transactional
	@Override
	public Transaction registerTransaction(Transaction transaction) {
		Optional<Transaction> existingTransaction = transactionRepository
				.findByTransactionId(transaction.getTransactionId());
		if (existingTransaction.isPresent()) {
			throw new DuplicateTransactionIdException();
		}
		Optional<Wallet> walletOpt = walletRepository.findById(transaction.getPlayerId());
		Wallet wallet = walletOpt.orElseThrow(ResourceNotFoundException::new);
		if (transaction.getTransactionType().equals(ETransactionType.DEBIT)) {
			if (wallet.getBalance().compareTo(transaction.getTransactionAmount()) == -1) {
				throw new LowBalanceException();
			}
			wallet.updateBalance(transaction.getTransactionAmount().negate());
		}
		else {
			wallet.updateBalance(transaction.getTransactionAmount());
		}
		walletRepository.save(wallet);
		transaction.setWallet(wallet);
		if (transaction.getCreated() == null) {
			transaction.setCreated(Instant.now());
		}
		return transactionRepository.save(transaction);
	}
}
