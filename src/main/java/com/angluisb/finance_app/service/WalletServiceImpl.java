package com.angluisb.finance_app.service;

import com.angluisb.finance_app.dto.request.WalletRequest;
import com.angluisb.finance_app.dto.response.WalletResponse;
import com.angluisb.finance_app.dto.update.WalletUpdate;
import com.angluisb.finance_app.entity.User;
import com.angluisb.finance_app.entity.Wallet;
import com.angluisb.finance_app.exception.ResourceNotFoundException;
import com.angluisb.finance_app.mapper.WalletMapper;
import com.angluisb.finance_app.repository.UserRepository;
import com.angluisb.finance_app.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletMapper walletMapper;
    private final WalletRepository walletRepository;
    private final UserService userService;



    @Override
    public WalletResponse createWallet(WalletRequest walletRequest) {
        Wallet wallet = walletMapper.toEntity(walletRequest);

        User fullUser = userService.getById(walletRequest.getUserId());
        wallet.setUser(fullUser);

        Wallet savedWallet = walletRepository.save(wallet);

        return walletMapper.toWalletResponse(savedWallet);
    }

    @Override
    public List<WalletResponse> findAllWalletsByUser(Long userId) {
        User user = userService.getById(userId);

        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }

        List<Wallet> walletList = walletRepository.findAllByUser(user);

        return walletMapper.toWalletResponse(walletList);
    }

    @Override
    public Wallet getById(Long id) {
        return walletRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found with id:" +id));
    }

    @Override
    public WalletResponse updateWallet(WalletUpdate walletRequest, Long id) {
        if (id == null) {
            throw new IllegalArgumentException("wallet id must not be null");
        }

        Optional<Wallet> optionalWallet = walletRepository.findById(id);

        if (optionalWallet.isEmpty()){
            throw new ResourceNotFoundException("Wallet not found with id: "+ id);
        }

        Wallet walletToUpdate = optionalWallet.get();

        walletToUpdate.setBalance(walletRequest.getBalance());
        walletToUpdate.setName(walletRequest.getName());
        walletToUpdate.setCurrency(walletRequest.getCurrency());


        return walletMapper.toWalletResponse(walletRepository.save(walletToUpdate));
    }



    @Override
    public void deleteWallet(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("wallet id must not be null");
        }

        if (walletRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Not found Wallet with id: "+ id);
        }

        walletRepository.deleteById(id);
    }
}
