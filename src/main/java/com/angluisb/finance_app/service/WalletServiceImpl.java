package com.angluisb.finance_app.service;

import com.angluisb.finance_app.dto.request.WalletRequest;
import com.angluisb.finance_app.dto.response.WalletResponse;
import com.angluisb.finance_app.entity.User;
import com.angluisb.finance_app.entity.Wallet;
import com.angluisb.finance_app.mapper.WalletMapper;
import com.angluisb.finance_app.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletMapper walletMapper;
    private final WalletRepository walletRepository;
    private final UserServiceImpl userService;



    @Override
    public WalletResponse createWallet(WalletRequest walletRequest) {
        Wallet wallet = walletMapper.toEntity(walletRequest);
        Wallet savedWallet = walletRepository.save(wallet);

        User fullUser = userService.getById(walletRequest.getUserId());
        savedWallet.setUser(fullUser);

        return walletMapper.toWalletResponse(savedWallet);
    }

    @Override
    public Wallet getById(Long id) {
        Optional<Wallet> optionalWallet = walletRepository.findById(id);

        if (optionalWallet.isEmpty()) {
            return null;
        }

        return optionalWallet.get();

    }

    @Override
    public WalletResponse updateWallet(WalletRequest walletRequest, Long id) {
        return null;
    }

    @Override
    public void deleteWallet(Long id) {

    }
}
