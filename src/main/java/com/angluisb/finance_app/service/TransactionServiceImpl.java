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
import com.angluisb.finance_app.repository.UserRepository;
import com.angluisb.finance_app.repository.WalletRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final WalletServiceImpl walletService;
    private final WalletRepository walletRepository;



    @Override
    @Transactional
    public TransactionResponse create(TransactionRequest transactionRequest) {
        Transaction transaction = transactionMapper.toTransaction(transactionRequest);

        Wallet fullWallet = walletService.getById(transactionRequest.getWalletId());

        if (fullWallet == null) {
            throw new ResourceNotFoundException("Wallet not found with id: " +  transactionRequest.getWalletId());
        }

        transaction.setWallet(fullWallet);
        Transaction savedTransaction =  transactionRepository.save(transaction);
        updateBalance(fullWallet,savedTransaction.getType(),savedTransaction.getAmount());

        return transactionMapper.toTransactionResponse(savedTransaction);
    }

    @Override
    @Transactional
    public TransactionResponse update(TransactionUpdate transaction, Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Transaction id cannot be null");
        }

        Optional<Transaction> optionalTransaction = transactionRepository.findById(id);

        if (optionalTransaction.isEmpty()) {
            throw new ResourceNotFoundException("Transaction not found with id: " + id);
        }

        Transaction transactionToUpdate = optionalTransaction.get();

        transactionToUpdate.setAmount(transaction.getAmount());
        transactionToUpdate.setType(transaction.getType());
        transactionToUpdate.setDate(transaction.getDate());
        transactionToUpdate.setCategory(transaction.getCategory());

        return transactionMapper.toTransactionResponse(transactionRepository.save(transactionToUpdate));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (id == null){
            throw new IllegalArgumentException("Transaction id cannot be null");
        }

        if (!transactionRepository.existsById(id)){
            throw new ResourceNotFoundException("Transaction not found with id: " + id);
        }
        Transaction transaction = getById(id);
        Wallet wallet =  transaction.getWallet();
        reverseBalance(wallet, transaction.getType(), transaction.getAmount());

        transactionRepository.deleteById(id);
    }

    @Override
    public Transaction getById(Long id) {
        if (id == null){
            throw new IllegalArgumentException("Transaction id cannot be null");
        }

        Optional<Transaction> optionalTransaction = transactionRepository.findById(id);

        if (optionalTransaction.isEmpty()) {
            throw new ResourceNotFoundException("Transaction not found with id: " + id);
        }

        return optionalTransaction.get();
    }

    @Override
    public List<TransactionResponse> findByWalletId(Long id) {
        if (id == null){
            throw new IllegalArgumentException("Wallet id cannot be null");
        }

        List<Transaction> transactions = transactionRepository.findAllByWalletId(id);

        if (transactions.isEmpty()) {
            throw new ResourceNotFoundException("Transactions not found with Wallet id: " + id);
        }

        return transactionMapper.toTransactionResponse(transactions);
    }


    public void updateBalance(Wallet fullWallet, TransactionType type, Double amount) {

        if (type.equals(TransactionType.EXPENSE)){
            if (fullWallet.getBalance() < amount){
                throw new BusinessException("Insufficient funds");
            }
            fullWallet.setBalance(fullWallet.getBalance() - amount);
        }
        else if (type.equals(TransactionType.INCOME)){
            fullWallet.setBalance(fullWallet.getBalance() + amount);
        }
        walletRepository.save(fullWallet);
    }

    public void reverseBalance (Wallet wallet, TransactionType type, Double amount) {
        if (type.equals(TransactionType.EXPENSE)) {
            wallet.setBalance(wallet.getBalance() + amount);
        } else if (type.equals(TransactionType.INCOME)) {
            wallet.setBalance(wallet.getBalance() - amount);
        }
    }
}
