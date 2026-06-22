package com.example.budget_planner.service.project;

import com.example.budget_planner.entity.Project;

import java.util.List;

public interface ProjectService {

    Project create(Long divisionId, Project project);

    List<Project> findByDivisionId(Long divisionId);

    Project findById(Long id);

    Project update(Long id, Project project);

    void delete(Long id);
}