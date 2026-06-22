package com.example.budget_planner.service.budgetSummary;


import com.example.budget_planner.entity.BudgetSummary;

public interface BudgetSummaryService {

    BudgetSummary getProjectBudget(Long projectId);

    BudgetSummary getDivisionBudget(Long divisionId);

    BudgetSummary getDepartmentBudget(Long departmentId);
}