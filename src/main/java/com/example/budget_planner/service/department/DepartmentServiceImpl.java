package com.example.budget_planner.service.impl;

import com.example.budget_planner.entity.Department;
import com.example.budget_planner.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements com.example.budget_planner.service.department.DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public Department create(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Department findById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));
    }

    @Override
    public Department update(Long id, Department department) {
        Department existingDepartment = findById(id);
        existingDepartment.setName(department.getName());

        return departmentRepository.save(existingDepartment);
    }

    @Override
    public void delete(Long id) {
        Department department = findById(id);
        departmentRepository.delete(department);
    }
}