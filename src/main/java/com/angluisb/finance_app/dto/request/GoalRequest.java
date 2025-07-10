package com.angluisb.finance_app.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class GoalRequest {
    @NotNull
    @Size(min = 1, max = 50)
    private String name;

    @NotNull
    @Positive
    private Double amount;

    @NotNull
    private LocalDate deadline;

    @NotNull
    @Size(min = 1, max = 100)
    private String description;

}
