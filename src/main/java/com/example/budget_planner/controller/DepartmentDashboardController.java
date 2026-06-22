package com.example.budget_planner.controller;

import com.example.budget_planner.entity.Department;
import com.example.budget_planner.entity.Division;
import com.example.budget_planner.service.budgetSummary.BudgetSummaryService;
import com.example.budget_planner.service.department.DepartmentService;
import com.example.budget_planner.service.division.DivisionService;
import com.example.budget_planner.service.payment.PaymentService;
import com.example.budget_planner.service.project.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/departments")
public class DepartmentDashboardController {

    private final DepartmentService departmentService;
    private final DivisionService divisionService;
    private final ProjectService projectService;
    private final PaymentService paymentService;
    private final BudgetSummaryService budgetSummaryService;

    @GetMapping("/{departmentId}/dashboard")
    public String dashboard(
            @PathVariable Long departmentId,
            @RequestParam(required = false) Long divisionId,
            Model model
    ) {
        Department department = departmentService.findById(departmentId);

        model.addAttribute("department", department);
        model.addAttribute("divisions", divisionService.findByDepartmentId(departmentId));
        model.addAttribute("departmentSummary", budgetSummaryService.getDepartmentBudget(departmentId));
        model.addAttribute("departmentPendingApprovals", paymentService.findPendingDepartmentApprovals(departmentId));

        if (divisionId != null) {
            Division selectedDivision = divisionService.findById(divisionId);

            model.addAttribute("selectedDivision", selectedDivision);
            model.addAttribute("divisionSummary", budgetSummaryService.getDivisionBudget(divisionId));
            model.addAttribute("projects", projectService.findByDivisionId(divisionId));
            model.addAttribute("divisionPendingPayments", paymentService.findPendingByDivisionId(divisionId));
        }

        return "departments/dashboard";
    }

    @PostMapping("/{departmentId}/payments/{paymentId}/approve-from-dashboard")
    public String approveFromDashboard(
            @PathVariable Long departmentId,
            @PathVariable Long paymentId
    ) {
        paymentService.approveByDepartmentManager(paymentId);
        return "redirect:/departments/" + departmentId + "/dashboard";
    }

    @PostMapping("/{departmentId}/payments/{paymentId}/reject-from-dashboard")
    public String rejectFromDashboard(
            @PathVariable Long departmentId,
            @PathVariable Long paymentId
    ) {
        paymentService.reject(paymentId);
        return "redirect:/departments/" + departmentId + "/dashboard";
    }

    @PostMapping("/{departmentId}/divisions")
    public String createDivisionFromDashboard(
            @PathVariable Long departmentId,
            @RequestParam String name
    ) {
        Division division = new Division();
        division.setName(name);

        divisionService.create(departmentId, division);

        return "redirect:/departments/" + departmentId + "/dashboard";
    }
}