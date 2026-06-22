package com.example.budget_planner.controller;

import com.example.budget_planner.entity.Division;
import com.example.budget_planner.service.division.DivisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DivisionRestController {

    private final DivisionService divisionService;

    @PostMapping("/departments/{departmentId}/divisions")
    public Division createDivision(
            @PathVariable Long departmentId,
            @RequestBody Division division
    ) {
        return divisionService.create(departmentId, division);
    }

    @GetMapping("/departments/{departmentId}/divisions")
    public List<Division> getDivisionsByDepartment(@PathVariable Long departmentId) {
        return divisionService.findByDepartmentId(departmentId);
    }

    @GetMapping("/divisions/{id}")
    public Division getDivisionById(@PathVariable Long id) {
        return divisionService.findById(id);
    }

    @PutMapping("/divisions/{id}")
    public Division updateDivision(
            @PathVariable Long id,
            @RequestBody Division division
    ) {
        return divisionService.update(id, division);
    }

    @DeleteMapping("/divisions/{id}")
    public void deleteDivision(@PathVariable Long id) {
        divisionService.delete(id);
    }
}