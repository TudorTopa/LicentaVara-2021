package com.example.tudortopa.animo_radar.animo_radar.service;

import com.example.tudortopa.animo_radar.animo_radar.controller.ProjectTechnologyController;
import com.example.tudortopa.animo_radar.animo_radar.model.Projects.Project;
import com.example.tudortopa.animo_radar.animo_radar.model.Technology.ETechnologyCategory;
import com.example.tudortopa.animo_radar.animo_radar.model.Technology.ProjectTechnology;
import com.example.tudortopa.animo_radar.animo_radar.repository.ProjectRepository;
import com.example.tudortopa.animo_radar.animo_radar.repository.ProjectTechnolgyRepository;
import com.example.tudortopa.animo_radar.animo_radar.repository.TechnologyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CreateTechnologyGraph {
    @Autowired
    private ProjectTechnolgyRepository projectTechnolgyRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TechnologyRepository technologyRepository;

    private List<ETechnologyCategory> categoryOrder = new ArrayList<>();

    Graph graph = new Graph();


    List<ProjectTechnology> technolgyList = new ArrayList<>();

    List<Project> projectList = new ArrayList<>();

    public List<ProjectTechnology> getTechnologyList(long projectId) {
        return projectTechnolgyRepository.getProjectTechnologiesByTechnologyIdProjectId(projectId);
    }

    public void getProjects() {
        projectRepository.findAll().forEach(projectList::add);
    }

    public String addNodes() {

        String currentTechnology = "";
        String nextTechnology = "";
        Long currentTechnologyId = 0l;
        Long nextTechnologyId = 0l;

        getProjects();
        for (int i = 0; i < projectList.size(); i++) {
            technolgyList.clear();
            technolgyList = getTechnologyList(projectList.get(i).getProjectId());

            for (int j = 0; j < technolgyList.size(); j++) {
                currentTechnology = technolgyList.get(j).getName();
                currentTechnologyId = technolgyList.get(j).getTechnologyId().getTechnologyId();
                if (!currentTechnology.isEmpty()) {
                    graph.addVertex(currentTechnology);
                }

            }
        addEdges();
        }
        return graph.toString();
    }

    public void addEdges(){
        String currentTechnology = "";
        String nextTechnology = "";
        Long currentTechnologyId = 0l;
        Long nextTechnologyId = 0l;
        for (int i2 = 0 ; i2 < technolgyList.size()-1; i2++) {
            currentTechnology = technolgyList.get(i2).getName();
            currentTechnologyId = technolgyList.get(i2).getTechnologyId().getTechnologyId();
            for (int j2 = i2 + 1; j2 < technolgyList.size(); j2++) {
                nextTechnology = technolgyList.get(j2).getName();
                nextTechnologyId = technolgyList.get(j2).getTechnologyId().getTechnologyId();
                if (checkAreInSameCategory(currentTechnologyId,nextTechnologyId)){
                    graph.addEdge(currentTechnology,nextTechnology);
                }
            }
        }
    }

    public boolean checkAreInSameCategory(long technology1,long technology2){
        String technologyCategory1 = technologyRepository.getTechnolyCategoryByTechnolgyId(technology1);
        String technologyCategory2 = technologyRepository.getTechnolyCategoryByTechnolgyId(technology2);
        if (technologyCategory1.equals(technologyCategory2)){
            return false;
        }
        return true;
    }

    //returns all the projectTechnologies where that project has a technology with the selected category
    //ProjectId is not unique
    public void getCategoryProjects(ETechnologyCategory eTechnologyCategory){
        technolgyList = projectTechnolgyRepository.getProjectTechnologiesByTechnologyCategory(eTechnologyCategory);
    }

    //returns all the technologies for the project
    public void getProjectTechnologies(Long projectId){

    }


    public void setCategoryOrder(){
        this.categoryOrder.add(ETechnologyCategory.LANGUAGE);
        this.categoryOrder.add(ETechnologyCategory.BACK_END);
        this.categoryOrder.add(ETechnologyCategory.FRONT_END);
        this.categoryOrder.add(ETechnologyCategory.DATABASE);
        this.categoryOrder.add(ETechnologyCategory.INTEGRATION);
        this.categoryOrder.add(ETechnologyCategory.AI);
        this.categoryOrder.add(ETechnologyCategory.VERSION_CONTROL);
        this.categoryOrder.add(ETechnologyCategory.COMPUTER_NETWORKS);
        this.categoryOrder.add(ETechnologyCategory.FRONT_END);
        this.categoryOrder.add(ETechnologyCategory.LANGUAGE);
        this.categoryOrder.add(ETechnologyCategory.BACK_END);
        this.categoryOrder.add(ETechnologyCategory.FRONT_END);
    }


}
