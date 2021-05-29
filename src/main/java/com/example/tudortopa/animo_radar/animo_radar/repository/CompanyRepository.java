package com.example.tudortopa.animo_radar.animo_radar.repository;

import com.example.tudortopa.animo_radar.animo_radar.model.Company.Company;
import com.example.tudortopa.animo_radar.animo_radar.model.Projects.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface CompanyRepository extends JpaRepository<Company,Long> {

    Company findCompanyByCompanyId(long companyId);

    @Query("SELECT p FROM Project p WHERE p.company.companyId = ?1 ")
    Collection<Project> findCompanyProjects(long companyId);
}
