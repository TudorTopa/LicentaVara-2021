package com.example.tudortopa.animo_radar.animo_radar.repository;

import com.example.tudortopa.animo_radar.animo_radar.model.Technology.ETechnologyCategory;
import com.example.tudortopa.animo_radar.animo_radar.model.Technology.Technologies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TechnologyRepository extends JpaRepository<Technologies,Long> {
    @Override
    List<Technologies> findAll();

    List<Technologies> getTechnologyByTechnologyCategory(ETechnologyCategory category);
    Optional<Technologies> findById(Long aLong);

    @Query("SELECT p.name,count(p.name) FROM ProjectTechnology p JOIN " +
            "Technologies t ON p.technologyId.technologyId = t.technologyId " +
            "WHERE t.technologyCategory = ?1 GROUP BY p.name ")
    List<Object> getTechnologiesCountByCategory(ETechnologyCategory category);


    @Query("SELECT t.technologyCategory FROM Technologies t WHERE t.technologyId = ?1")
    String getTechnolyCategoryByTechnolgyId(Long id);

    @Query("select t from Technologies t join ProjectTechnology pt ON" +
            " t.technologyId = pt.technologyId.technologyId WHERE " +
            "pt.technologyId.projectId = ?1")
    List<Technologies> getTechnologiesByProjectID(long projectId);

}


