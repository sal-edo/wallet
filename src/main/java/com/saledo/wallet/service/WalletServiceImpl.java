package com.saledo.wallet.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saledo.wallet.entity.Wallet;
import com.saledo.wallet.exception.ResourceNotFoundException;
import com.saledo.wallet.repository.WalletRepository;

@Service("walletService")
public class WalletServiceImpl implements WalletService {
	@Autowired
	WalletRepository walletRepository;

	@Override
	public Wallet getByPlayerId(Long id) {
		Optional<Wallet> wallet = walletRepository.findById(id);
		return wallet.orElseThrow(ResourceNotFoundException::new);
	}
}
