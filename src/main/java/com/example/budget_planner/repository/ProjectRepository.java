package com.example.budget_planner.repository;

import com.example.budget_planner.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByDivisionId(Long divisionId);
}