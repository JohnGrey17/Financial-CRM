package com.example.budget_planner.service.division;

import com.example.budget_planner.entity.Department;
import com.example.budget_planner.entity.Division;
import com.example.budget_planner.repository.DepartmentRepository;
import com.example.budget_planner.repository.DivisionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DivisionServiceImpl implements DivisionService {

    private final DivisionRepository divisionRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public Division create(Long departmentId, Division division) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + departmentId));

        division.setDepartment(department);

        return divisionRepository.save(division);
    }

    @Override
    public List<Division> findByDepartmentId(Long departmentId) {
        return divisionRepository.findByDepartmentId(departmentId);
    }

    @Override
    public Division findById(Long id) {
        return divisionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Division not found with id: " + id));
    }

    @Override
    public Division update(Long id, Division division) {
        Division existingDivision = findById(id);
        existingDivision.setName(division.getName());

        return divisionRepository.save(existingDivision);
    }

    @Override
    public void delete(Long id) {
        Division division = findById(id);
        divisionRepository.delete(division);
    }
}