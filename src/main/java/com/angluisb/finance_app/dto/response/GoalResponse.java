package com.angluisb.finance_app.dto.response;

import com.angluisb.finance_app.dto.summary.WalletSummary;
import lombok.Data;

import java.time.LocalDate;

@Data
public class GoalResponse {
    private Long id;
    private String name;
    private Double amount;
    private LocalDate deadline;
    private LocalDate createdAt;
    private WalletSummary wallet;
    private String description;
}
