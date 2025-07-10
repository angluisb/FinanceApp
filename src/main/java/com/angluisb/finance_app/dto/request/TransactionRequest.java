package com.angluisb.finance_app.dto.request;

import com.angluisb.finance_app.entity.Enum.CategoryType;
import com.angluisb.finance_app.entity.Enum.TransactionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TransactionRequest {

    @NotNull
    private CategoryType category;

    @NotNull
    private Double amount;

    @NotNull
    private TransactionType type;

    @NotNull
    private LocalDate date;

}
