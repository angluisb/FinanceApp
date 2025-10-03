package com.angluisb.finance_app.controller;

import com.angluisb.finance_app.dto.request.TransactionRequest;
import com.angluisb.finance_app.dto.response.TransactionResponse;
import com.angluisb.finance_app.dto.update.TransactionUpdate;
import com.angluisb.finance_app.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;


    @PostMapping
    public ResponseEntity<TransactionResponse> createTransaction(@RequestBody @Valid TransactionRequest transactionRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.create(transactionRequest));
    }
    @PutMapping("/{id}")
    public ResponseEntity<TransactionResponse> updateTransaction(@RequestBody @Valid TransactionUpdate transactionUpdate, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(transactionService.update(transactionUpdate, id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<TransactionResponse>> getTransactions(@PathVariable Long id) {
        List<TransactionResponse> transactionResponses = transactionService.findByWalletId(id);
        return ResponseEntity.ok(transactionResponses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        transactionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
