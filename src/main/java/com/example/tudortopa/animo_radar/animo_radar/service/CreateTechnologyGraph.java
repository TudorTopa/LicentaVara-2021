package com.example.tudortopa.animo_radar.animo_radar.service;

import com.example.tudortopa.animo_radar.animo_radar.controller.ProjectTechnologyController;
import com.example.tudortopa.animo_radar.animo_radar.model.Projects.Project;
import com.example.tudortopa.animo_radar.animo_radar.model.Technology.ETechnologyCategory;
import com.example.tudortopa.animo_radar.animo_radar.model.Technology.ProjectTechnology;
import com.example.tudortopa.animo_radar.animo_radar.model.Technology.Technologies;
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
    private TechnologyRepository technologyRepository;

    private List<ETechnologyCategory> categoryOrder = new ArrayList<>();

    Graph graph;

    List<Project> projectList = new ArrayList<>();

    List<Long> projectIds = new ArrayList<>();

    List<Technologies> technologies = new ArrayList<>();

    public void getTechnologies() {
        technologyRepository.findAll().forEach(technologies::add);
    }

    public String createGraph() {
        graph = new Graph();
        addNodes();
        setCategoryOrder();
        createEdges();
        return graph.toString();
    }

    public void addNodes() {
        this.technologies.clear();
        getTechnologies();
        for (int i = 0; i < technologies.size(); i++) {
            graph.addVertex(technologies.get(i).getTechnologyName());
        }
    }

    public boolean checkAreInSameCategory(long technology1, long technology2) {
        String technologyCategory1 = technologyRepository.getTechnolyCategoryByTechnolgyId(technology1);
        String technologyCategory2 = technologyRepository.getTechnolyCategoryByTechnolgyId(technology2);
        if (technologyCategory1.equals(technologyCategory2)) {
            return false;
        }
        return true;
    }

    //Add all the projects for category
    public void getCategoryProjectTechnologies(ETechnologyCategory eTechnologyCategory) {
        //clear projects from the last category
        this.projectIds.clear();
        this.projectList.clear();

        projectList = projectTechnolgyRepository.getProjectsByTechnologyCategory(eTechnologyCategory);
        for (int i = 0; i < projectList.size(); i++) {
            Long currentProject = projectList.get(i).getProjectId();
            if (!projectIds.contains(currentProject)) {
                projectIds.add(currentProject);
            }
        }
    }

    public boolean checkCategoryOrder(ETechnologyCategory currentCatergory, ETechnologyCategory targetCategory) {
        if (categoryOrder.indexOf(currentCatergory) < categoryOrder.indexOf(targetCategory)) {
            return true;
        }
        return false;
    }

    public boolean checkCurrentTechnologyCategory(Technologies technology, ETechnologyCategory eTechnologyCategory) {
        ETechnologyCategory currentCatergory = technology.getTechnologyCategory();
        if (currentCatergory.equals(eTechnologyCategory)) {
            return true;
        }
        return false;
    }

    // Add edges for project
    public void addEdgesForProject(Long projectId, ETechnologyCategory category) {
        List<Technologies> currentTechnologies = new ArrayList<>();
        currentTechnologies = technologyRepository.getTechnologiesByProjectID(projectId);
        for (int j = 0; j < currentTechnologies.size(); j++) {
            if (checkCurrentTechnologyCategory(currentTechnologies.get(j), category)) {
                for (int x = 0; x < currentTechnologies.size(); x++) {
                    if ((!checkCurrentTechnologyCategory(currentTechnologies.get(x), category))
                            && checkCategoryOrder(currentTechnologies.get(j).getTechnologyCategory(),
                            currentTechnologies.get(x).getTechnologyCategory())) {
                        graph.addEdge(currentTechnologies.get(j).getTechnologyName(), currentTechnologies.get(x).getTechnologyName());
                    }
                }
            }
        }
    }

    public void createEdges() {
        for (int i = 0; i < categoryOrder.size(); i++) {
            ETechnologyCategory currentCategory = categoryOrder.get(i);
            getCategoryProjectTechnologies(currentCategory);
            for (int j = 0; j < projectIds.size(); j++) {
                addEdgesForProject(projectIds.get(j), currentCategory);
            }
        }
    }


    public void setCategoryOrder() {
        categoryOrder.clear();
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
