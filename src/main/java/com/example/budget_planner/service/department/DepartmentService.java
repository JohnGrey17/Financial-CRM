package com.example.budget_planner.service.department;

import com.example.budget_planner.entity.Department;

import java.util.List;

public interface DepartmentService {

    Department create(Department department);

    List<Department> findAll();

    Department findById(Long id);

    Department update(Long id, Department department);

    void delete(Long id);
}