package com.angluisb.finance_app.service;

import com.angluisb.finance_app.dto.request.TransactionRequest;
import com.angluisb.finance_app.dto.response.TransactionResponse;
import com.angluisb.finance_app.entity.Transaction;

import java.util.List;

public interface TransactionService {
    public TransactionResponse create(TransactionRequest transaction);
    public TransactionResponse update(TransactionRequest transaction, Long id);
    void delete(Long id);
    public Transaction getById(Long id);
    List<TransactionResponse> findAll();
    List<TransactionResponse> findByUserId(Long id);

}
