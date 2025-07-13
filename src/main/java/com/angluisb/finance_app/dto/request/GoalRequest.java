package com.angluisb.finance_app.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class GoalRequest {

    @NotNull(message = "Name is required")
    @Size(min = 1, max = 50)
    private String name;

    @NotNull(message = "Amount is required")
    @Positive
    private Double amount;

    @NotNull(message = "DeadLine is required")
    private LocalDate deadline;

    @NotNull(message = "Description is required")
    @Size(min = 1, max = 100)
    private String description;

    @NotNull(message = "wallet ID is required")
    private Long walletId;


}
