package com.example.tudortopa.animo_radar.animo_radar.model.Technology;


import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "ProjectTechnologies")
public class ProjectTechnology {

    @EmbeddedId
    private TechnologyId technologyId;

    @Column(nullable = false, unique = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(length = 20,columnDefinition="VARCHAR(20) default 'HOLD'")
    private EState state;


    public TechnologyId getTechnologyId() {
        return technologyId;
    }

    public String getName() {
        return name;
    }

    public EState getState() {
        return state;
    }

    public ProjectTechnology(TechnologyId technologyId, String name, EState state) {
        this.technologyId = technologyId;
        this.name = name;
        this.state = state;
    }
    public ProjectTechnology(){

    }
}
