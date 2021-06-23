package com.example.tudortopa.animo_radar.animo_radar.model.Employee;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
class EmployeeProjectKey implements Serializable {

    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "project_id")
    private Long projectId;

    public Long getEmployeeId() {
        return employeeId;
    }

    public Long getProjectId() {
        return projectId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(getEmployeeId(), getProjectId());

    }
}
