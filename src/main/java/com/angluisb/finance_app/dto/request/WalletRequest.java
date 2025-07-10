package com.angluisb.finance_app.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class WalletRequest {

    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    @PositiveOrZero
    @NotNull
    private Double balance;
}
