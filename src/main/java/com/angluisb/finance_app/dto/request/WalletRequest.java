package com.angluisb.finance_app.dto.request;

import com.angluisb.finance_app.entity.Enum.CurrencyType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class WalletRequest {

    @NotNull(message = "Name is required")
    @Size(min = 3, max = 50)
    private String name;

    @PositiveOrZero
    @NotNull(message = "Balance is required")
    private Double balance;

    @NotNull(message = "User is required")
    private Long userId;

    @NotNull(message = "Currency is required")
    private CurrencyType currency;
}
