package com.example.budget_planner.controller;

import com.example.budget_planner.entity.Division;
import com.example.budget_planner.entity.Project;
import com.example.budget_planner.enums.PaymentStatus;
import com.example.budget_planner.service.division.DivisionService;
import com.example.budget_planner.service.payment.PaymentService;
import com.example.budget_planner.service.project.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/divisions")
public class DivisionViewController {

    private final DivisionService divisionService;
    private final ProjectService projectService;
    private final PaymentService paymentService;

    @GetMapping("/{id}")
    public String details(
            @PathVariable Long id,
            @RequestParam(required = false) Long projectId,
            @RequestParam(required = false) PaymentStatus status,
            Model model
    ) {
        Division division = divisionService.findById(id);

        model.addAttribute("division", division);
        model.addAttribute("projects", projectService.findByDivisionId(id));
        model.addAttribute("selectedProjectId", projectId);
        model.addAttribute("selectedStatus", status);

        if (projectId != null && status != null) {
            model.addAttribute("payments", paymentService.findByProjectIdAndStatus(projectId, status));
        } else if (projectId != null) {
            model.addAttribute("payments", paymentService.findByProjectId(projectId));
        } else {
            model.addAttribute("payments", paymentService.findPendingByDivisionId(id));
        }

        return "divisions/details";
    }

    @PostMapping("/{divisionId}/projects")
    public String createProjectFromDivisionPage(
            @PathVariable Long divisionId,
            @RequestParam String name
    ) {
        Project project = new Project();
        project.setName(name);

        projectService.create(divisionId, project);

        return "redirect:/divisions/" + divisionId;
    }

    @PostMapping("/{divisionId}/payments/{paymentId}/approve")
    public String approvePayment(
            @PathVariable Long divisionId,
            @PathVariable Long paymentId
    ) {
        paymentService.approveByDivisionManager(paymentId);

        return "redirect:/divisions/" + divisionId;
    }

    @PostMapping("/{divisionId}/payments/{paymentId}/reject")
    public String rejectPayment(
            @PathVariable Long divisionId,
            @PathVariable Long paymentId
    ) {
        paymentService.reject(paymentId);

        return "redirect:/divisions/" + divisionId;
    }
}