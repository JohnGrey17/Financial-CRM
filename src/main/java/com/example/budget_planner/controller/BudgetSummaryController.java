package com.example.budget_planner.controller;

import com.example.budget_planner.entity.BudgetSummary;
import com.example.budget_planner.service.budgetSummary.BudgetSummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BudgetSummaryController {

    private final BudgetSummaryService budgetSummaryService;

    @GetMapping("/projects/{projectId}/budget")
    public BudgetSummary getProjectBudget(@PathVariable Long projectId) {
        return budgetSummaryService.getProjectBudget(projectId);
    }

    @GetMapping("/divisions/{divisionId}/budget")
    public BudgetSummary getDivisionBudget(@PathVariable Long divisionId) {
        return budgetSummaryService.getDivisionBudget(divisionId);
    }

    @GetMapping("/departments/{departmentId}/budget")
    public BudgetSummary getDepartmentBudget(@PathVariable Long departmentId) {
        return budgetSummaryService.getDepartmentBudget(departmentId);
    }
}