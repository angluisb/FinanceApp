package com.angluisb.finance_app.controller;

import com.angluisb.finance_app.dto.request.TransactionRequest;
import com.angluisb.finance_app.dto.response.TransactionResponse;
import com.angluisb.finance_app.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;


    @PostMapping("create")
    public ResponseEntity<TransactionResponse> create(@RequestBody @Valid TransactionRequest transactionRequest) {
        TransactionResponse transaction =  transactionService.create(transactionRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
    }
}
