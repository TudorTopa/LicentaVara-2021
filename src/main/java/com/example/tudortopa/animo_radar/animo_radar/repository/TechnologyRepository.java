package com.example.tudortopa.animo_radar.animo_radar.repository;

import com.example.tudortopa.animo_radar.animo_radar.model.Technology.ETechnologyCategory;
import com.example.tudortopa.animo_radar.animo_radar.model.Technology.Technology;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TechnologyRepository extends JpaRepository<Technology,Long> {
    @Override
    List<Technology> findAll();

    List<Technology> getTechnologyByTechnologyCategory(ETechnologyCategory category);
    Optional<Technology> findById(Long aLong);
}


