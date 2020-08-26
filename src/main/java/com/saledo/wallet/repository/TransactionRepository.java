package com.saledo.wallet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saledo.wallet.entity.Transaction;

@Repository("transactionRepository")
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	List<Transaction> findByWalletId(Long id);

	Optional<Transaction> findByTransactionId(String id);
}
