package com.example.budget_planner.service.payment;

import com.example.budget_planner.entity.Payment;
import com.example.budget_planner.enums.PaymentStatus;

import java.util.List;

public interface PaymentService {

    Payment create(Long projectId, Payment payment);

    List<Payment> findByProjectId(Long projectId);

    Payment findById(Long id);

    Payment update(Long id, Payment payment);

    void delete(Long id);

    Payment approveByDivisionManager(Long id);

    Payment approveByDepartmentManager(Long id);

    Payment reject(Long id);

    List<Payment> findPendingByDepartmentId(Long departmentId);

    List<Payment> findByProjectIdAndStatus(Long projectId, PaymentStatus status);

    Payment submitToDivisionApproval(Long id);

    List<Payment> findPendingByDivisionId(Long divisionId);

    List<Payment> findPendingDepartmentApprovals(Long departmentId);
}