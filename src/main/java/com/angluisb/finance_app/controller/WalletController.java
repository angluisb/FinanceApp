package com.angluisb.finance_app.controller;

import com.angluisb.finance_app.dto.request.WalletRequest;
import com.angluisb.finance_app.dto.response.WalletResponse;
import com.angluisb.finance_app.entity.Wallet;
import com.angluisb.finance_app.service.UserService;
import com.angluisb.finance_app.service.WalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/wallets")
@RequiredArgsConstructor
public class WalletController {
    private final WalletService walletService;
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<WalletResponse> createWallet(@RequestBody @Valid WalletRequest walletRequest) {
        WalletResponse savedWallet = walletService.createWallet(walletRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedWallet);
    }
}
