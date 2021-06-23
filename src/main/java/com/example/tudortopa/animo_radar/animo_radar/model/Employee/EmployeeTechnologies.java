package com.example.tudortopa.animo_radar.animo_radar.model.Employee;

import com.example.tudortopa.animo_radar.animo_radar.model.Technology.Technologies;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "EmployeeTechnologies")
public class EmployeeTechnologies {
    @EmbeddedId
    EmployeeTechnologyKey employeeTechnologyKey;

    @Column(name = "skillLevel")
    ELevel skillLevel;

    public void setEmployeeTechnologyKey(EmployeeTechnologyKey employeeTechnologyKey) {
        this.employeeTechnologyKey = employeeTechnologyKey;
    }

    public void setSkillLevel(ELevel skillLevel) {
        this.skillLevel = skillLevel;
    }

    public EmployeeTechnologyKey getEmployeeTechnologyKey() {
        return employeeTechnologyKey;
    }

    public ELevel getSkillLevel() {
        return skillLevel;
    }

    public EmployeeTechnologies(EmployeeTechnologyKey employeeTechnologyKey, ELevel skillLevel) {
        this.employeeTechnologyKey = employeeTechnologyKey;
        this.skillLevel = skillLevel;
    }
    public EmployeeTechnologies(){

    }
}
