package com.angluisb.finance_app.dto.response;

import com.angluisb.finance_app.dto.summary.WalletSummary;
import com.angluisb.finance_app.entity.Enum.CategoryType;
import com.angluisb.finance_app.entity.Enum.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {
    private Long id;
    private TransactionType  type;
    private Double amount;
    private CategoryType  category;
    private WalletSummary  wallet;
    private LocalDate date;
    private LocalDate createdAt;
}
