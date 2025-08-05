package com.angluisb.finance_app.service;

import com.angluisb.finance_app.dto.request.WalletRequest;
import com.angluisb.finance_app.dto.response.WalletResponse;
import com.angluisb.finance_app.dto.update.WalletUpdate;
import com.angluisb.finance_app.entity.Wallet;

import java.util.List;

public interface WalletService {
    WalletResponse createWallet(WalletRequest walletRequest);
    List<WalletResponse> findAllWalletsByUser(Long userId);
    Wallet getById(Long id);
    WalletResponse updateWallet(WalletUpdate walletRequest, Long id);
    void deleteWallet(Long id);

}
