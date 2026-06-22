package com.example.budget_planner.controller;

import com.example.budget_planner.entity.Department;
import com.example.budget_planner.service.department.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentRestController {

    private final DepartmentService departmentService;

    @PostMapping
    public Department createDepartment(@RequestBody Department department) {
        return departmentService.create(department);
    }

    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentService.findAll();
    }

    @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable Long id) {
        return departmentService.findById(id);
    }

    @PutMapping("/{id}")
    public Department updateDepartment(
            @PathVariable Long id,
            @RequestBody Department department
    ) {
        return departmentService.update(id, department);
    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable Long id) {
        departmentService.delete(id);
    }
}