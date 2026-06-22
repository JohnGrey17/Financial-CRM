package com.example.budget_planner.entity;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BudgetSummary {

    private BigDecimal total;
    private BigDecimal approved;
    private BigDecimal pending;
    private BigDecimal rejected;
}