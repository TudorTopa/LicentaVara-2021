package com.example.tudortopa.animo_radar.animo_radar.model.Employee;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EmployeeTechnologyKey implements Serializable {

    @Column(name = "employee_id")
    private Long employeeId;
    @Column(name =  "technology_id")
    private Long technologyId;

    public  EmployeeTechnologyKey(Long employeeId,Long technologyId){
        this.employeeId = employeeId;
        this.technologyId = technologyId;
    }
    public EmployeeTechnologyKey(){

    }


    public Long getEmployeeId() {
        return employeeId;
    }

    public Long getTechnologyId() {
        return technologyId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmployeeId(),getTechnologyId());
    }
}
