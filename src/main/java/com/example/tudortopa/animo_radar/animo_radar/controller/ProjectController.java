package com.example.tudortopa.animo_radar.animo_radar.controller;

import com.example.tudortopa.animo_radar.animo_radar.model.Projects.Project;
import com.example.tudortopa.animo_radar.animo_radar.repository.CompanyRepository;
import com.example.tudortopa.animo_radar.animo_radar.repository.ProjectRepository;
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
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    CompanyRepository companyRepository;

    @GetMapping
    public ResponseEntity<List<Project>> getProjects() {
        List<Project> projects = new ArrayList<>();
        projects.addAll(projectRepository.findAll());
        if (projects.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        try {
            Project _project = projectRepository
                    .save(new Project(project.getName(), project.getCompany()));
            return new ResponseEntity<>(_project, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/technology/{id}")
    public ResponseEntity<List<Project>> findProjectsByTechnology(@PathVariable("id") Long technologyId) {
        List<Project> projects = new ArrayList<>();
        projects.addAll(projectRepository.findProjectsByTechnologyId(technologyId));
        if (projects.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }
    @GetMapping("/ids")
    public ResponseEntity<List<Long>> getProjectIds(){
        List<Long> projectIds = new ArrayList<>();
        projectRepository.getProjectIds().forEach(projectIds::add);
        if(projectIds.isEmpty()) {
            return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(projectIds,HttpStatus.OK);
    }

}
