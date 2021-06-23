package com.example.tudortopa.animo_radar.animo_radar.controller;

import com.example.tudortopa.animo_radar.animo_radar.model.Company.Company;
import com.example.tudortopa.animo_radar.animo_radar.model.Technology.EState;
import com.example.tudortopa.animo_radar.animo_radar.model.Technology.ETechnologyCategory;
import com.example.tudortopa.animo_radar.animo_radar.model.Technology.ProjectTechnology;
import com.example.tudortopa.animo_radar.animo_radar.model.Technology.Technologies;
import com.example.tudortopa.animo_radar.animo_radar.repository.ProjectTechnolgyRepository;
import com.example.tudortopa.animo_radar.animo_radar.repository.TechnologyRepository;
import com.example.tudortopa.animo_radar.animo_radar.service.CreateTechnologyGraph;
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
@RequestMapping("/api/projectTechnologies")
public class ProjectTechnologyController {

    @Autowired
    ProjectTechnolgyRepository projectTechnolgyRepository;

    @Autowired
    CreateTechnologyGraph technologyGraph;

    @Autowired
    TechnologyRepository technologyRepository;

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
        technologies = projectTechnolgyRepository.findByTechnologyIdTechnologyId(technologyId).get();
        if (technologies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(technologies, HttpStatus.OK);
    }
    @GetMapping("/projectId/{id}")
    public ResponseEntity<List<ProjectTechnology>> getTechnologiesByProjectId(@PathVariable("id") Long projectId){
        List<ProjectTechnology> technologies = new ArrayList<>();
        technologies = projectTechnolgyRepository.findByTechnologyIdProjectId(projectId).get();
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
    @GetMapping("/common/company/{id}")
    public ResponseEntity<List<Object>> getMostUsedTechnologiesForCompany(@PathVariable("id") Long companyId)
    {
        List<Object> technologies = new ArrayList<>();
        technologies = projectTechnolgyRepository.getTechnologyCountForCompany(companyId);
        if (technologies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(technologies, HttpStatus.OK);
    }
    @GetMapping("/state/{state}")
    public  ResponseEntity<List<Object>> getTechnologiesByState(@PathVariable("state") EState state){
        List<Object> projectTechnologies = new ArrayList<>();
        projectTechnologies = projectTechnolgyRepository.getAllByState(state);
        if(projectTechnologies.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(projectTechnologies, HttpStatus.OK);
    }
    @GetMapping("/state/{state}/company/{company}")
    public ResponseEntity<List<Object>> getTechnologiesByStateAndCompany(@PathVariable("state") EState state,@PathVariable("company") Long companyId){
        List<Object> projectTechnologies = new ArrayList<>();
        projectTechnologies = projectTechnolgyRepository.getAllByStateAndCompany(state,companyId);
        if(projectTechnologies.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(projectTechnologies, HttpStatus.OK);
    }
    @GetMapping("/ALGORITHM")
    public ResponseEntity<String> getAlgorithmOutput(){
        String res = technologyGraph.addNodes();
        return  new ResponseEntity<>(res,HttpStatus.OK);
    }
    @GetMapping("/ALGORITHM/{category}")
    public ResponseEntity<List<ProjectTechnology>> getProjectTechnologiesByCategory(@PathVariable("category")ETechnologyCategory category){
        List<ProjectTechnology> projectTechnologies = new ArrayList<>();
        projectTechnolgyRepository.getProjectTechnologiesByTechnologyCategory(category).forEach(projectTechnologies::add);
        if(projectTechnologies.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(projectTechnologies, HttpStatus.OK);
    }
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Technologies>> getTechnologiesByProject(@PathVariable("projectId") long projectId){
        List<Technologies> technologies = new ArrayList<>();
        technologyRepository.getTechnologiesByProjectID(projectId).forEach(technologies::add);
        if(technologies.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(technologies, HttpStatus.OK);
    }

}
