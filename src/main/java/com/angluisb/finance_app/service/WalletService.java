package com.angluisb.finance_app.service;

import com.angluisb.finance_app.dto.request.WalletRequest;
import com.angluisb.finance_app.dto.response.WalletResponse;
import com.angluisb.finance_app.entity.Wallet;

public interface WalletService {
    WalletResponse createWallet(WalletRequest walletRequest);
    Wallet getById(Long id);
    WalletResponse updateWallet(WalletRequest walletRequest, Long id);
    void deleteWallet(Long id);
}
