package com.angluisb.finance_app.service;

import com.angluisb.finance_app.dto.request.WalletRequest;
import com.angluisb.finance_app.dto.response.WalletResponse;
import com.angluisb.finance_app.dto.update.WalletUpdate;
import com.angluisb.finance_app.entity.User;
import com.angluisb.finance_app.entity.Wallet;
import com.angluisb.finance_app.exception.BusinessException;
import com.angluisb.finance_app.exception.ResourceNotFoundException;
import com.angluisb.finance_app.mapper.WalletMapper;
import com.angluisb.finance_app.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WalletServiceImpl implements WalletService {

    private final WalletMapper walletMapper;
    private final WalletRepository walletRepository;
    private final UserService userService;



    @Override
    @Transactional
    public WalletResponse createWallet(WalletRequest walletRequest) {
        User user = userService.getById(walletRequest.getUserId());
        if (walletRepository.existsByNameAndUser(walletRequest.getName(), user)){
            throw new BusinessException("Wallet with this name already exists");
        }

        Wallet wallet = walletMapper.toEntity(walletRequest);
        wallet.setUser(user);
        Wallet savedWallet = walletRepository.save(wallet);
        return walletMapper.toWalletResponse(savedWallet);
    }

    @Override
    public List<WalletResponse> findAllWalletsByUser(Long userId) {
        User user = userService.getById(userId);
        List<Wallet> walletList = walletRepository.findAllByUser(user);
        return walletMapper.toWalletResponse(walletList);
    }

    @Override
    public Wallet getById(Long id) {
        return walletRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found with id: " + id));
    }

    @Override
    @Transactional
    public WalletResponse updateWallet(WalletUpdate walletRequest, Long id) {
        Wallet walletToUpdate = getById(id);

        Optional.ofNullable(walletRequest.getBalance()).ifPresent(walletToUpdate::setBalance);
        Optional.ofNullable(walletRequest.getName()).ifPresent(walletToUpdate::setName);
        Optional.ofNullable(walletRequest.getCurrency()).ifPresent(walletToUpdate::setCurrency);

        return walletMapper.toWalletResponse(walletRepository.save(walletToUpdate));
    }



    @Override
    @Transactional
    public void deleteWallet(Long id) {
        Wallet wallet = getById(id);
        walletRepository.delete(wallet);
    }
}
