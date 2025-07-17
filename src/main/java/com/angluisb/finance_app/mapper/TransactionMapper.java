package com.angluisb.finance_app.mapper;

import com.angluisb.finance_app.dto.request.TransactionRequest;
import com.angluisb.finance_app.dto.response.TransactionResponse;
import com.angluisb.finance_app.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionResponse toTransactionResponse(Transaction transaction);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Transaction toTransaction(TransactionRequest transactionRequest);
}
