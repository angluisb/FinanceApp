package com.angluisb.finance_app.dto.update;

import com.angluisb.finance_app.entity.Enum.CurrencyType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class WalletUpdate {

    @NotNull(message = "Name is required")
    @Size(min = 3, max = 50, message = "name size must be between 3 and 50")
    private String name;

    @PositiveOrZero
    @NotNull(message = "Balance is required")
    private Double balance;

    @NotNull(message = "Currency is required")
    private CurrencyType currency;
}
