package com.angluisb.finance_app.dto.summary;

import com.angluisb.finance_app.entity.Enum.CategoryType;
import com.angluisb.finance_app.entity.Enum.TransactionType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TransactionSummary {
    private Long id;
    private TransactionType type;
    private Double amount;
    private CategoryType  category;
    private LocalDate  date;
}
