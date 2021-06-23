package com.example.tudortopa.animo_radar.animo_radar.controller;

import com.example.tudortopa.animo_radar.animo_radar.model.Company.Company;
import com.example.tudortopa.animo_radar.animo_radar.model.Technology.ETechnologyCategory;
import com.example.tudortopa.animo_radar.animo_radar.model.Technology.ProjectTechnology;
import com.example.tudortopa.animo_radar.animo_radar.model.Technology.Technologies;
import com.example.tudortopa.animo_radar.animo_radar.repository.TechnologyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/technologies")
public class TechnologyController {

    @Autowired
    TechnologyRepository technologyRepository;

    @PostMapping
    public ResponseEntity<Technologies> createProject(@RequestBody Technologies technology) {
        try {
            Technologies _technology = technologyRepository
                    .save(new Technologies(technology.getTechnologyName(), technology.getTechnologyCategory()));
            return new ResponseEntity<>(_technology, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Technologies>> getAllTechnologies() {
        List<Technologies> technologies = new ArrayList<>();
        technologyRepository.findAll().forEach(technologies::add);
        if (technologies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(technologies, HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Technologies>> getAllTechnologiesByCategory(@PathVariable("category") ETechnologyCategory category) {
        List<Technologies> technologies = new ArrayList<>();
        technologyRepository.getTechnologyByTechnologyCategory(category).forEach(technologies::add);
        if (technologies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(technologies, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Technologies> getById(@PathVariable("id") Long technologyId) {
        Technologies technology = technologyRepository.findById(technologyId).get();
        if (technology.equals(null)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(technology, HttpStatus.OK);
    }

    @GetMapping("/categoryCount/{category}")
    public ResponseEntity<List<Object>> getAllTechnologyCountByCategory(@PathVariable("category") ETechnologyCategory category) {
        List<Object> technologies = new ArrayList<>();
        technologyRepository.getTechnologiesCountByCategory(category).forEach(technologies::add);
        if (technologies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(technologies, HttpStatus.OK);
    }

}

