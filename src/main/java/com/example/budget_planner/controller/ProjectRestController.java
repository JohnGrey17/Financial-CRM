package com.example.budget_planner.controller;

import com.example.budget_planner.entity.Project;
import com.example.budget_planner.service.project.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProjectRestController {

    private final ProjectService projectService;

    @PostMapping("/divisions/{divisionId}/projects")
    public Project createProject(
            @PathVariable Long divisionId,
            @RequestBody Project project
    ) {
        return projectService.create(divisionId, project);
    }

    @GetMapping("/divisions/{divisionId}/projects")
    public List<Project> getProjectsByDivision(@PathVariable Long divisionId) {
        return projectService.findByDivisionId(divisionId);
    }

    @GetMapping("/projects/{id}")
    public Project getProjectById(@PathVariable Long id) {
        return projectService.findById(id);
    }

    @PutMapping("/projects/{id}")
    public Project updateProject(
            @PathVariable Long id,
            @RequestBody Project project
    ) {
        return projectService.update(id, project);
    }

    @DeleteMapping("/projects/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectService.delete(id);
    }
}