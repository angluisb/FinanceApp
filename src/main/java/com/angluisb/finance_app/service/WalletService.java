package com.angluisb.finance_app.service;

import com.angluisb.finance_app.dto.request.WalletRequest;
import com.angluisb.finance_app.dto.response.WalletResponse;

public interface WalletService {
    WalletResponse createWallet(WalletRequest walletRequest);
    WalletResponse getById(Long id);
    WalletResponse updateWallet(WalletRequest walletRequest, Long id);
    void deleteWallet(Long id);
}
