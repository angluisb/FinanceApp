package com.angluisb.finance_app.controller;

import com.angluisb.finance_app.dto.request.WalletRequest;
import com.angluisb.finance_app.dto.response.WalletResponse;
import com.angluisb.finance_app.dto.update.WalletUpdate;
import com.angluisb.finance_app.entity.Wallet;
import com.angluisb.finance_app.service.UserService;
import com.angluisb.finance_app.service.WalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/wallets")
@RequiredArgsConstructor
public class WalletController {
    private final WalletService walletService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<WalletResponse> createWallet(@RequestBody @Valid WalletRequest walletRequest) {
        WalletResponse savedWallet = walletService.createWallet(walletRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedWallet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WalletResponse> updateWallet(@RequestBody @Valid WalletUpdate walletRequest, @PathVariable Long id) {
        return ResponseEntity.ok(walletService.updateWallet(walletRequest,id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<WalletResponse>> getWallets(@PathVariable Long id){
        List<WalletResponse> walletResponseList = walletService.findAllWalletsByUser(id);
        return ResponseEntity.ok(walletResponseList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWallet(@PathVariable Long id){
        walletService.deleteWallet(id);
        return ResponseEntity.ok("Wallet deleted");
    }
}
