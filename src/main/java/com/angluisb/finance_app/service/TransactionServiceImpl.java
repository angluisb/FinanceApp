package com.angluisb.finance_app.service;

import com.angluisb.finance_app.dto.request.TransactionRequest;
import com.angluisb.finance_app.dto.response.TransactionResponse;
import com.angluisb.finance_app.dto.update.TransactionUpdate;
import com.angluisb.finance_app.entity.Enum.TransactionType;
import com.angluisb.finance_app.entity.Transaction;
import com.angluisb.finance_app.entity.Wallet;
import com.angluisb.finance_app.exception.BusinessException;
import com.angluisb.finance_app.exception.ResourceNotFoundException;
import com.angluisb.finance_app.mapper.TransactionMapper;
import com.angluisb.finance_app.repository.TransactionRepository;
import com.angluisb.finance_app.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final WalletService walletService;
    private final WalletRepository walletRepository;



    @Override
    @Transactional
    public TransactionResponse create(TransactionRequest transactionRequest) {
        if (transactionRequest.getAmount() <= 0) {
            throw new BusinessException("Amount must be greater than 0");
        }

        Wallet fullWallet = walletService.getById(transactionRequest.getWalletId());
        updateBalance(fullWallet,transactionRequest.getType(),transactionRequest.getAmount());

        Transaction transaction = transactionMapper.toTransaction(transactionRequest);
        transaction.setWallet(fullWallet);
        Transaction savedTransaction =  transactionRepository.save(transaction);

        return transactionMapper.toTransactionResponse(savedTransaction);
    }

    @Override
    @Transactional
    public TransactionResponse update(TransactionUpdate transactionUpdate, Long id) {
        Transaction existingTransaction = getById(id);
        Wallet wallet = existingTransaction.getWallet();

        reverseBalance(wallet,existingTransaction.getType(),existingTransaction.getAmount());

        Optional.ofNullable(transactionUpdate.getAmount()).ifPresent(existingTransaction::setAmount);
        Optional.ofNullable(transactionUpdate.getType()).ifPresent(existingTransaction::setType);
        Optional.ofNullable(transactionUpdate.getDate()).ifPresent(existingTransaction::setDate);
        Optional.ofNullable(transactionUpdate.getCategory()).ifPresent(existingTransaction::setCategory);

        updateBalance(wallet, existingTransaction.getType(),existingTransaction.getAmount());


        return transactionMapper.toTransactionResponse(transactionRepository.save(existingTransaction));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Transaction transaction = getById(id);
        Wallet wallet =  transaction.getWallet();

        reverseBalance(wallet, transaction.getType(), transaction.getAmount());
        transactionRepository.delete(transaction);
    }

    @Override
    public Transaction getById(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id: " + id));
    }

    @Override
    public List<TransactionResponse> findByWalletId(Long id) {
        walletService.getById(id);

        List<Transaction> transactions = transactionRepository.findAllByWalletId(id);
        return transactionMapper.toTransactionResponse(transactions);
    }


    private void updateBalance(Wallet wallet, TransactionType type, Double amount) {
        switch (type) {
            case EXPENSE:
                if (wallet.getBalance() < amount) {
                    throw new BusinessException("Insufficient funds");
                }
                wallet.setBalance(wallet.getBalance() - amount);
                break;
            case INCOME:
                wallet.setBalance(wallet.getBalance() + amount);
                break;
            default:
                throw new IllegalArgumentException("Invalid transaction type: " + type);
        }
        walletRepository.save(wallet);
    }

    private void reverseBalance (Wallet wallet, TransactionType type, Double amount) {
        switch (type){
            case EXPENSE:
                wallet.setBalance(wallet.getBalance() + amount);
                break;
            case INCOME:
                if (wallet.getBalance() < amount) {
                    throw new BusinessException("Cannot reverse: would result in negative balance");
                }
                wallet.setBalance(wallet.getBalance() - amount);
                break;
            default:
                throw new IllegalArgumentException("Invalid transaction type: " + type);
        }
        walletRepository.save(wallet);
    }
}
