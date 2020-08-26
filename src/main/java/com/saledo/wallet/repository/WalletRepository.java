package com.saledo.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saledo.wallet.entity.Wallet;

@Repository("walletRepository")
public interface WalletRepository extends JpaRepository<Wallet, Long> {
	Wallet findByPlayerName(String playerName);
}
