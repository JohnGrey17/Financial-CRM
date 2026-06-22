package com.example.budget_planner.repository;

import com.example.budget_planner.entity.Division;
import com.example.budget_planner.entity.Payment;
import com.example.budget_planner.enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DivisionRepository extends JpaRepository<Division, Long> {

    List<Division> findByDepartmentId(Long departmentId);


}