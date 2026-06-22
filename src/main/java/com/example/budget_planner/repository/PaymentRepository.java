package com.example.budget_planner.repository;

import com.example.budget_planner.entity.Payment;
import com.example.budget_planner.enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByProject_Id(Long projectId);

    List<Payment> findByProject_Division_Id(Long divisionId);

    List<Payment> findByProject_Division_Department_Id(Long departmentId);

    List<Payment> findByProject_Division_Department_IdAndStatusIn(
            Long departmentId,
            List<PaymentStatus> statuses
    );

    List<Payment> findByProject_IdAndStatus(
            Long projectId,
            PaymentStatus status
    );

    List<Payment> findByProject_Division_IdAndStatus(
            Long divisionId,
            PaymentStatus status
    );
    List<Payment> findByProject_Division_Department_IdAndStatus(
            Long departmentId,
            PaymentStatus status
    );
}