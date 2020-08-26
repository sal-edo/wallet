package com.saledo.wallet.service;

import com.saledo.wallet.entity.Wallet;

public interface WalletService {

	Wallet getByPlayerId(Long id);

}
