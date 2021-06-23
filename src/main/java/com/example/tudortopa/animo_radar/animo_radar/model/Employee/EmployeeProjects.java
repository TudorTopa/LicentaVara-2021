package com.example.tudortopa.animo_radar.animo_radar.model.Employee;


import com.example.tudortopa.animo_radar.animo_radar.model.Projects.Project;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "EmployeesProjects")
public class EmployeeProjects {

    @EmbeddedId
    EmployeeProjectKey employeeProjectId;

    @ManyToOne
    @MapsId("employeeId")
    @JoinColumn(name = "employee_id")
    Employee employee;

    @ManyToOne
    @MapsId("projectId")
    @JoinColumn(name = "project_id")
    Project project;

    @NotNull
    @Column(name = "startDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @Column(name = "finishDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date finishDate;

    @Column(name = "isFinished")
    private Boolean isFinished;


}
