package com.example.tudortopa.animo_radar.animo_radar.model.Employee;


import com.example.tudortopa.animo_radar.animo_radar.model.Company.Company;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "Employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long employeeId;
    @NonNull
    @Column
    private String firstName;
    @NotNull
    @Column
    private String lastName;
    @NonNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(nullable = false)
    private Company company;

    @Column(columnDefinition = "DATE DEFAULT CURRENT_DATE")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date availableFrom;

    @NonNull
    @Column(columnDefinition = "boolean default true")
    private boolean isAvailable = true ;

    @Enumerated(EnumType.STRING)
    @Column(length = 20,columnDefinition="VARCHAR(20) default 'JUNIOR'")
    private ELevel employeeLevel;

    @Enumerated(EnumType.STRING)
    @Column(length = 20,columnDefinition="VARCHAR(20) default 'DEVELOPER'")
    private EPosition employeePosition;

    @OneToMany(mappedBy = "employee")
    List<EmployeeProjects> employeeProjects;


    @OneToMany(mappedBy = "employeeTechnologyKey.employeeId")
    List<EmployeeTechnologies> employeeTechnologies ;


    public Employee(@NonNull String firstName, @NotNull String lastName, @NonNull Company company, ELevel employeeLevel, EPosition employeePosition) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.employeeLevel = employeeLevel;
        this.employeePosition = employeePosition;
    }
    public Employee(){

    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    @NonNull
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @NonNull
    public Company getCompany() {
        return company;
    }

    public ELevel getEmployeeLevel() {
        return employeeLevel;
    }

    public EPosition getEmployeePosition() {
        return employeePosition;
    }


}
