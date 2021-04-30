package com.example.tudortopa.animo_radar.animo_radar.model.Projects;


import com.example.tudortopa.animo_radar.animo_radar.model.Company.Company;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long projectId;

    @ManyToOne
    @JoinColumn(name="companyId", nullable=false)
    private Company company;
}
