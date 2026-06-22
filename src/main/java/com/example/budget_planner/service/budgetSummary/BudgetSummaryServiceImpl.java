package com.example.budget_planner.service.budgetSummary;

import com.example.budget_planner.entity.BudgetSummary;
import com.example.budget_planner.entity.Payment;
import com.example.budget_planner.enums.PaymentStatus;
import com.example.budget_planner.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BudgetSummaryServiceImpl implements BudgetSummaryService {

    private final PaymentRepository paymentRepository;

    @Override
    public BudgetSummary getProjectBudget(Long projectId) {
        List<Payment> payments = paymentRepository.findByProject_Id(projectId);
        return calculateSummary(payments);
    }

    @Override
    public BudgetSummary getDivisionBudget(Long divisionId) {
        List<Payment> payments = paymentRepository.findByProject_Division_Id(divisionId);
        return calculateSummary(payments);
    }

    @Override
    public BudgetSummary getDepartmentBudget(Long departmentId) {
        List<Payment> payments = paymentRepository.findByProject_Division_Department_Id(departmentId);
        return calculateSummary(payments);
    }

    private BudgetSummary calculateSummary(List<Payment> payments) {
        BigDecimal total = BigDecimal.ZERO;
        BigDecimal approved = BigDecimal.ZERO;
        BigDecimal pending = BigDecimal.ZERO;
        BigDecimal rejected = BigDecimal.ZERO;

        for (Payment payment : payments) {
            BigDecimal amount = payment.getAmount();

            total = total.add(amount);

            if (payment.getStatus() == PaymentStatus.APPROVED) {
                approved = approved.add(amount);
            } else if (
                    payment.getStatus() == PaymentStatus.PENDING_DIVISION_APPROVAL ||
                    payment.getStatus() == PaymentStatus.PENDING_DEPARTMENT_APPROVAL ||
                    payment.getStatus() == PaymentStatus.DRAFT
            ) {
                pending = pending.add(amount);
            } else if (payment.getStatus() == PaymentStatus.REJECTED) {
                rejected = rejected.add(amount);
            }
        }

        return BudgetSummary.builder()
                .total(total)
                .approved(approved)
                .pending(pending)
                .rejected(rejected)
                .build();
    }
}