package com.example.tudortopa.animo_radar.animo_radar.controller;

import com.example.tudortopa.animo_radar.animo_radar.model.Technology.ProjectTechnology;
import com.example.tudortopa.animo_radar.animo_radar.repository.ProjectTechnolgyRepository;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/projectTechnologies")
public class ProjectTechnologyController {

    @Autowired
    ProjectTechnolgyRepository projectTechnolgyRepository;

    @PostMapping
    public ResponseEntity<ProjectTechnology> createProject(@RequestBody ProjectTechnology projectTechnology) {
        try {
            ProjectTechnology _Project_technology = projectTechnolgyRepository
                    .save(new ProjectTechnology(projectTechnology.getTechnologyId(), projectTechnology.getName(), projectTechnology.getState()));
            return new ResponseEntity<>(_Project_technology, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping
    public ResponseEntity<List<ProjectTechnology>> getAllTechnologies(){
        List<ProjectTechnology> technologies = new ArrayList<>();
        projectTechnolgyRepository.findAll().forEach(technologies::add);
        if (technologies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(technologies, HttpStatus.OK);
    }
    @GetMapping("/technologyId/{id}")
    public ResponseEntity<List<ProjectTechnology>> getTechnologiesByTechnologyId(@PathVariable("id") Long technologyId){
        List<ProjectTechnology> technologies = new ArrayList<>();
        technologies = projectTechnolgyRepository.findByTechnologyIdTechnologyId(technologyId).stream().collect(Collectors.toList());
        if (technologies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(technologies, HttpStatus.OK);
    }
    @GetMapping("/projectId/{id}")
    public ResponseEntity<List<ProjectTechnology>> getTechnologiesByProjectId(@PathVariable("id") Long projectId){
        List<ProjectTechnology> technologies = new ArrayList<>();
        technologies = projectTechnolgyRepository.findByTechnologyIdProjectId(projectId).stream().collect(Collectors.toList());
        if (technologies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(technologies, HttpStatus.OK);
    }
    @GetMapping("/common")
    public ResponseEntity<List<Object>> getMostUsedTechnologies()
    {
        List<Object> technologies = new ArrayList<>();
        technologies = projectTechnolgyRepository.getTechnologyCount();
        if (technologies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(technologies, HttpStatus.OK);
    }
}
