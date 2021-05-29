package com.example.tudortopa.animo_radar.animo_radar.repository;

import com.example.tudortopa.animo_radar.animo_radar.model.Projects.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findProjectByProjectId(long id);

    @Query("SELECT p FROM Project p JOIN ProjectTechnology t ON  p.projectId = t.technologyId.projectId WHERE t.technologyId.technologyId = ?1")
    List<Project> findProjectsByTechnologyId(long technologyId);
}
