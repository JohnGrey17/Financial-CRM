package com.example.budget_planner.service.division;

import com.example.budget_planner.entity.Division;

import java.util.List;

public interface DivisionService {

    Division create(Long departmentId, Division division);

    List<Division> findByDepartmentId(Long departmentId);

    Division findById(Long id);

    Division update(Long id, Division division);

    void delete(Long id);
}