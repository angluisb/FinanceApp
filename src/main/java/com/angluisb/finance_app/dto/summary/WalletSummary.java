package com.angluisb.finance_app.dto.summary;

import com.angluisb.finance_app.entity.Enum.CurrencyType;
import lombok.Data;

@Data
public class WalletSummary {
    private Long id;
    private String name;
    private Double balance;
    private CurrencyType currency;
}
