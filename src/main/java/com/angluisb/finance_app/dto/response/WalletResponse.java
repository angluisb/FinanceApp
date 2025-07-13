package com.angluisb.finance_app.dto.response;

import com.angluisb.finance_app.dto.summary.GoalSummary;
import com.angluisb.finance_app.dto.summary.TransactionSummary;
import com.angluisb.finance_app.dto.summary.UserSummary;
import com.angluisb.finance_app.entity.Enum.CurrencyType;

import java.time.LocalDate;
import java.util.List;

public class WalletResponse {
    private Long id;
    private String name;
    private Double balance;
    private CurrencyType currency;
    private LocalDate createdAt;
    private UserSummary user;
    private List<TransactionSummary> transactions;
    private List<GoalSummary> goals;
}
