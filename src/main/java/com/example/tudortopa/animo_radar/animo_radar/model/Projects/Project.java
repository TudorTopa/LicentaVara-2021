package com.example.tudortopa.animo_radar.animo_radar.model.Projects;


import com.example.tudortopa.animo_radar.animo_radar.model.Company.Company;
import com.example.tudortopa.animo_radar.animo_radar.model.Employee.EmployeeProjects;
import com.example.tudortopa.animo_radar.animo_radar.model.Technology.ProjectTechnology;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long projectId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "companyId", nullable = false)
    private Company company;

    @Column(name = "name")
    private String name;

    @Column(name = "startDate" )
    @Temporal( value = TemporalType.TIMESTAMP )
    @Generated( value = GenerationTime.INSERT )
    @ColumnDefault( value = "CURRENT_TIMESTAMP" )
    private Date startDate;

    @JsonIgnore
    @OneToMany(mappedBy = "technologyId.projectId", cascade = CascadeType.ALL,
            fetch= FetchType.EAGER)
    private List<ProjectTechnology> projectTechnology;

    @JsonIgnore
    @OneToMany(mappedBy = "project")
    List<EmployeeProjects> employeeProjects;

    public Project(String name, Company company) {
        this.name = name;
        this.company = company;
    }

    public Project() {
    }

    public List<ProjectTechnology> getTechnology() {
        return projectTechnology;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }
}
