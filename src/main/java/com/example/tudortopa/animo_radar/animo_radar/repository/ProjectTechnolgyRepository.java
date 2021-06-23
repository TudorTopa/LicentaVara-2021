package com.example.tudortopa.animo_radar.animo_radar.repository;


import com.example.tudortopa.animo_radar.animo_radar.model.Technology.EState;
import com.example.tudortopa.animo_radar.animo_radar.model.Technology.ETechnologyCategory;
import com.example.tudortopa.animo_radar.animo_radar.model.Technology.ProjectTechnology;
import com.example.tudortopa.animo_radar.animo_radar.model.Technology.TechnologyId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProjectTechnolgyRepository extends JpaRepository<ProjectTechnology, TechnologyId> {

    Optional<List<ProjectTechnology>> findByTechnologyIdProjectId(Long projectId);
    Optional<List<ProjectTechnology>> findByTechnologyIdTechnologyId(Long technologyId);

    @Query("SELECT t.technologyId.technologyId,t.name,COUNT(t.technologyId.technologyId) FROM " +
            " ProjectTechnology t" +
            " GROUP BY t.technologyId.technologyId,t.name" +
            " ORDER BY  " +
            " COUNT(t.technologyId.technologyId) DESC"
             )
    List<Object> getTechnologyCount();

    @Query("SELECT t.technologyId.technologyId,t.name,COUNT(t.technologyId.technologyId) FROM " +
            " ProjectTechnology t" +
            " JOIN Project p ON p.projectId = t.technologyId.projectId" +
            " WHERE p.company.companyId = ?1 " +
            " GROUP BY t.technologyId.technologyId,t.name" +" ORDER BY " +
            " COUNT(t.technologyId.technologyId) DESC"
    )
    List<Object> getTechnologyCountForCompany(long companyId);

    @Query("SELECT p.name,count(p.name) FROM ProjectTechnology p WHERE p.state = ?1 GROUP BY p.name ")
    List<Object> getAllByState(EState state);


    @Query("SELECT p.name,count(p.name) FROM ProjectTechnology p " +
            "JOIN Project pr ON p.technologyId.projectId = pr.projectId " +
            "WHERE p.state = ?1 AND pr.company.companyId = ?2 " +
            "group by p.name")
    List<Object> getAllByStateAndCompany(EState state, Long companyId);

    List<ProjectTechnology> getProjectTechnologiesByTechnologyIdProjectId(long projectId);

    @Query("SELECT pt FROM  ProjectTechnology pt JOIN  " +
            "Technologies t ON pt.technologyId.technologyId = t.technologyId WHERE t.technologyCategory = ?1")
    List<ProjectTechnology> getProjectTechnologiesByTechnologyCategory(ETechnologyCategory category);

}
