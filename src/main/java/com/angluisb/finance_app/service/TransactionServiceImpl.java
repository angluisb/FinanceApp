package com.angluisb.finance_app.service;

import com.angluisb.finance_app.dto.request.TransactionRequest;
import com.angluisb.finance_app.dto.response.TransactionResponse;
import com.angluisb.finance_app.entity.Enum.TransactionType;
import com.angluisb.finance_app.entity.Transaction;
import com.angluisb.finance_app.entity.Wallet;
import com.angluisb.finance_app.mapper.TransactionMapper;
import com.angluisb.finance_app.repository.TransactionRepository;
import com.angluisb.finance_app.repository.UserRepository;
import com.angluisb.finance_app.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final WalletServiceImpl walletService;
    private final WalletRepository walletRepository;



    @Override
    public TransactionResponse create(TransactionRequest transactionRequest) {

        Transaction transaction = transactionMapper.toTransaction(transactionRequest);
        Transaction savedTransaction = transactionRepository.save(transaction);

        Wallet fullWallet = walletService.getById(transactionRequest.getWalletId());
        savedTransaction.setWallet(fullWallet);
        updateBalance(transactionRequest);

        return transactionMapper.toTransactionResponse(savedTransaction);
    }

    @Override
    public TransactionResponse update(TransactionRequest transaction, Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Transaction getById(Long id) {
        return null;
    }

    @Override
    public List<TransactionResponse> findAll() {
        return List.of();
    }

    @Override
    public List<TransactionResponse> findByUserId(Long id) {
        return List.of();
    }

    public void updateBalance(TransactionRequest transactionRequest) {

        Wallet fullWallet = walletService.getById(transactionRequest.getWalletId());
        TransactionType type = transactionRequest.getType();
        Double amount = transactionRequest.getAmount();

        if (type.equals(TransactionType.EXPENSE)){
            fullWallet.setBalance(fullWallet.getBalance() - amount);
        }
        if (type.equals(TransactionType.INCOME)){
            fullWallet.setBalance(fullWallet.getBalance() + amount);
        }
        walletRepository.save(fullWallet);
    }
}
