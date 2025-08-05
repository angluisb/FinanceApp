package com.angluisb.finance_app.mapper;

import com.angluisb.finance_app.dto.request.TransactionRequest;
import com.angluisb.finance_app.dto.response.TransactionResponse;
import com.angluisb.finance_app.dto.summary.TransactionSummary;
import com.angluisb.finance_app.entity.Transaction;
import com.angluisb.finance_app.entity.Wallet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionResponse toTransactionResponse(Transaction transaction);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "wallet", source = "walletId", qualifiedByName = "mapWalletIdToWallet")
    Transaction toTransaction(TransactionRequest transactionRequest);

    List<TransactionResponse> toTransactionResponse(List<Transaction> transactions);

    @Named("mapWalletIdToWallet")
    static Wallet mapWalletIdToWallet(Long walletId) {
        if (walletId == null) {
            return null;
        }
        Wallet wallet = new Wallet();
        wallet.setId(walletId);
        return wallet;
    }

    TransactionSummary toTransactionSummary(Transaction transaction);
}
