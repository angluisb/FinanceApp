package com.angluisb.finance_app.dto.request;

import com.angluisb.finance_app.entity.Enum.CategoryType;
import com.angluisb.finance_app.entity.Enum.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TransactionRequest {

    @NotNull(message = "category is required")
    @NotBlank(message = "Category cannot be blank")
    private CategoryType category;

    @NotNull(message = "Amount is required")

    private Double amount;

    @NotNull(message = "Transaction type is required")
    private TransactionType type;

    @NotNull(message = "date is required")
    private LocalDate date;

    @NotNull(message = "wallet ID is required")
    private Long walletId;

}
