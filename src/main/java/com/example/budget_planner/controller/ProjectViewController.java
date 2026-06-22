package com.example.budget_planner.controller;

import com.example.budget_planner.entity.BudgetSummary;
import com.example.budget_planner.entity.Payment;
import com.example.budget_planner.entity.Project;

import com.example.budget_planner.service.budgetSummary.BudgetSummaryService;
import com.example.budget_planner.service.payment.PaymentService;
import com.example.budget_planner.service.project.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectViewController {

    private final ProjectService projectService;
    private final PaymentService paymentService;
    private final BudgetSummaryService budgetSummaryService;

    @GetMapping("/{id}")
    public String projectDetails(@PathVariable Long id, Model model) {
        Project project = projectService.findById(id);
        BudgetSummary summary = budgetSummaryService.getProjectBudget(id);

        model.addAttribute("project", project);
        model.addAttribute("summary", summary);
        model.addAttribute("payments", paymentService.findByProjectId(id));

        return "projects/details";
    }

    @PostMapping("/{projectId}/payments")
    public String createPaymentFromProjectPage(
            @PathVariable Long projectId,
            @ModelAttribute Payment payment
    ) {
        paymentService.create(projectId, payment);

        return "redirect:/projects/" + projectId;
    }

    @PostMapping("/{projectId}/payments/{paymentId}/submit")
    public String submitPaymentToDivisionApproval(
            @PathVariable Long projectId,
            @PathVariable Long paymentId
    ) {
        paymentService.submitToDivisionApproval(paymentId);

        return "redirect:/projects/" + projectId;
    }
}