package com.angluisb.finance_app.dto.summary;

import com.angluisb.finance_app.entity.Enum.CurrencyType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WalletSummary {
    private Long id;
    private String name;
    private Double balance;
    private CurrencyType currency;
}
