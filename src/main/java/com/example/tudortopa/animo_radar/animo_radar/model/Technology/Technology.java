package com.example.tudortopa.animo_radar.animo_radar.model.Technology;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "Technologies")
public class Technology {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long technologyId;

    @Column(name = "technologyName", unique = true, nullable = false)
    private String technologyName;

    @Enumerated(EnumType.STRING)
    @Column(length = 64,columnDefinition="VARCHAR(20) default 'HOLD'")
    private ETechnologyCategory technologyCategory;

    @OneToMany(mappedBy = "technologyId.technologyId", cascade = CascadeType.ALL,
            fetch= FetchType.EAGER)
    private List<ProjectTechnology> projectTechnology;

    public long getTechnologyId() {
        return technologyId;
    }

    public String getTechnologyName() {
        return technologyName;
    }

    public ETechnologyCategory getTechnologyCategory() {
        return technologyCategory;
    }

    public List<ProjectTechnology> getProjectTechnology() {
        return projectTechnology;
    }

    public Technology( String technologyName, ETechnologyCategory technologyCategory) {
        this.technologyName = technologyName;
        this.technologyCategory = technologyCategory;
    }
    public Technology(){

    }
}
