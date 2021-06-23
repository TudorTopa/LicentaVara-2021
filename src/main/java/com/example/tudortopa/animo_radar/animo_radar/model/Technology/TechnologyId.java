package com.example.tudortopa.animo_radar.animo_radar.model.Technology;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TechnologyId implements Serializable {
    @Column
    private long projectId;
    @Column
    private long technologyId;

    public TechnologyId(){

    }

    public TechnologyId(long projectId, long technologyId) {
        this.projectId = projectId;
        this.technologyId = technologyId;
    }

    public long getProjectId() {
        return projectId;
    }

    public long getTechnologyId() {
        return technologyId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof TechnologyId)) return false;
        TechnologyId technologyId = (TechnologyId) obj;
        return Objects.equals(getProjectId(),technologyId.getProjectId()) &&
                Objects.equals(getTechnologyId(),technologyId.getTechnologyId());
     }

    @Override
    public int hashCode() {
        return Objects.hash(getTechnologyId(),getProjectId());
    }

    @Override
    public String toString() {
        return "TechnologyId{" +
                "projectId=" + projectId +
                ", technologyId=" + technologyId +
                '}';
    }
}
