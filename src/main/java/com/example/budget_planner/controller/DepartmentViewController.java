package com.example.budget_planner.controller;

import com.example.budget_planner.entity.Department;
import com.example.budget_planner.entity.Division;
import com.example.budget_planner.enums.PaymentStatus;
import com.example.budget_planner.service.budgetSummary.BudgetSummaryService;
import com.example.budget_planner.service.department.DepartmentService;
import com.example.budget_planner.service.division.DivisionService;
import com.example.budget_planner.service.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/departments")
public class DepartmentViewController {

    private final DepartmentService departmentService;
    private final DivisionService divisionService;
    private final PaymentService paymentService;
    private final BudgetSummaryService budgetSummaryService;

    @GetMapping("/{id}")
    public String departmentDetails(
            @PathVariable Long id,
            Model model
    ) {
        Department department = departmentService.findById(id);

        model.addAttribute("department", department);
        model.addAttribute("divisions", divisionService.findByDepartmentId(id));
        model.addAttribute("summary", budgetSummaryService.getDepartmentBudget(id));
        model.addAttribute("pendingApprovals", paymentService.findPendingDepartmentApprovals(id));

        return "departments/details";
    }

    @PostMapping("/{departmentId}/payments/{paymentId}/approve")
    public String approvePaymentByDepartment(
            @PathVariable Long departmentId,
            @PathVariable Long paymentId
    ) {
        paymentService.approveByDepartmentManager(paymentId);

        return "redirect:/departments/" + departmentId;
    }

    @PostMapping("/{departmentId}/payments/{paymentId}/reject")
    public String rejectPaymentByDepartment(
            @PathVariable Long departmentId,
            @PathVariable Long paymentId
    ) {
        paymentService.reject(paymentId);

        return "redirect:/departments/" + departmentId;
    }
}