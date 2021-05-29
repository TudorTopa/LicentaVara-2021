package com.example.tudortopa.animo_radar.animo_radar.repository;


import com.example.tudortopa.animo_radar.animo_radar.model.Technology.ProjectTechnology;
import com.example.tudortopa.animo_radar.animo_radar.model.Technology.TechnologyId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProjectTechnolgyRepository extends JpaRepository<ProjectTechnology, TechnologyId> {

    Optional<ProjectTechnology> findByTechnologyIdProjectId(Long projectId);
    Optional<ProjectTechnology> findByTechnologyIdTechnologyId(Long technologyId);

    @Query("SELECT t.technologyId.technologyId,t.name,COUNT(t.technologyId.technologyId) FROM " +
            " ProjectTechnology t" +
            " GROUP BY t.technologyId.technologyId,t.name" +
            " ORDER BY  " +
            " COUNT(t.technologyId.technologyId) DESC"
             )
    List<Object> getTechnologyCount();
}
