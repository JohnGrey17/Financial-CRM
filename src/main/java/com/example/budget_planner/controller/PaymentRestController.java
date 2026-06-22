package com.example.budget_planner.controller;

import com.example.budget_planner.entity.Payment;
import com.example.budget_planner.enums.PaymentStatus;
import com.example.budget_planner.service.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PaymentRestController {

    private final PaymentService paymentService;

    @PostMapping("/projects/{projectId}/payments")
    public Payment createPayment(
            @PathVariable Long projectId,
            @RequestBody Payment payment
    ) {
        return paymentService.create(projectId, payment);
    }

    @GetMapping("/projects/{projectId}/payments")
    public List<Payment> getPaymentsByProject(@PathVariable Long projectId) {
        return paymentService.findByProjectId(projectId);
    }

    @GetMapping("/payments/{id}")
    public Payment getPaymentById(@PathVariable Long id) {
        return paymentService.findById(id);
    }

    @PutMapping("/payments/{id}")
    public Payment updatePayment(
            @PathVariable Long id,
            @RequestBody Payment payment
    ) {
        return paymentService.update(id, payment);
    }

    @DeleteMapping("/payments/{id}")
    public void deletePayment(@PathVariable Long id) {
        paymentService.delete(id);
    }

    @PatchMapping("/payments/{id}/approve/division")
    public Payment approveByDivisionManager(@PathVariable Long id) {
        return paymentService.approveByDivisionManager(id);
    }

    @PatchMapping("/payments/{id}/approve/department")
    public Payment approveByDepartmentManager(@PathVariable Long id) {
        return paymentService.approveByDepartmentManager(id);
    }

    @PatchMapping("/payments/{id}/reject")
    public Payment rejectPayment(@PathVariable Long id) {
        return paymentService.reject(id);
    }

    @GetMapping("/departments/{departmentId}/payments/pending")
    public List<Payment> getPendingPaymentsByDepartment(
            @PathVariable Long departmentId
    ) {
        return paymentService.findPendingByDepartmentId(departmentId);
    }

    @GetMapping("/projects/{projectId}/payments/status/{status}")
    public List<Payment> getPaymentsByProjectAndStatus(
            @PathVariable Long projectId,
            @PathVariable PaymentStatus status
    ) {
        return paymentService.findByProjectIdAndStatus(projectId, status);
    }
}