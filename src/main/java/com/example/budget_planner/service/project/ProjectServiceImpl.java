package com.example.budget_planner.service.project;

import com.example.budget_planner.entity.Division;
import com.example.budget_planner.entity.Project;
import com.example.budget_planner.repository.DivisionRepository;
import com.example.budget_planner.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final DivisionRepository divisionRepository;

    @Override
    public Project create(Long divisionId, Project project) {
        Division division = divisionRepository.findById(divisionId)
                .orElseThrow(() -> new RuntimeException("Division not found with id: " + divisionId));

        project.setDivision(division);

        return projectRepository.save(project);
    }

    @Override
    public List<Project> findByDivisionId(Long divisionId) {
        return projectRepository.findByDivisionId(divisionId);
    }

    @Override
    public Project findById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + id));
    }

    @Override
    public Project update(Long id, Project project) {
        Project existingProject = findById(id);
        existingProject.setName(project.getName());

        return projectRepository.save(existingProject);
    }

    @Override
    public void delete(Long id) {
        Project project = findById(id);
        projectRepository.delete(project);
    }
}