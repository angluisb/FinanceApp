package com.angluisb.finance_app.dto.summary;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GoalSummary {
    private Long id;
    private String name;
    private double amount;
    private LocalDate deadline;
    private String description;
}
