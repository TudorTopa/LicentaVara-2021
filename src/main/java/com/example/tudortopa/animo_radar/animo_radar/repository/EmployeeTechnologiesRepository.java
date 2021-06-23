package com.example.tudortopa.animo_radar.animo_radar.repository;

import com.example.tudortopa.animo_radar.animo_radar.model.Employee.ELevel;
import com.example.tudortopa.animo_radar.animo_radar.model.Employee.EmployeeTechnologies;
import com.example.tudortopa.animo_radar.animo_radar.model.Employee.EmployeeTechnologyKey;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeTechnologiesRepository extends JpaRepository<EmployeeTechnologies, EmployeeTechnologyKey> {
    @Override
    List<EmployeeTechnologies> findAll();


    List<EmployeeTechnologies> findEmployeeTechnologiesByEmployeeTechnologyKeyTechnologyId(long technologyId);

    @Query("SELECT et,e FROM EmployeeTechnologies et JOIN Employee e " +
            "ON et.employeeTechnologyKey.employeeId = e.employeeId " +
            "WHERE et.employeeTechnologyKey.technologyId = ?1")
    List<Object> getEmployeeTechnologiesByTechnologyKey(long technologyId);

    @Query("SELECT et.skillLevel FROM EmployeeTechnologies et " +
            "WHERE et.employeeTechnologyKey = ?1")
    ELevel getSkillLevelByEmployeeTechnologyKey(EmployeeTechnologyKey employeeTechnologyKey);

    @Query("SELECT et,t.technologyName as technologyName FROM EmployeeTechnologies et JOIN Technologies t ON et.employeeTechnologyKey.technologyId = t.technologyId WHERE" +
            " et.employeeTechnologyKey.employeeId = ?1")
    List<Object> getEmployeeTechnologiesForEmployee(Long id);

    @Query("SELECT et,t.technologyName,e.firstName,e.lastName as technologyName FROM EmployeeTechnologies et " +
            "JOIN Technologies t ON et.employeeTechnologyKey.technologyId = t.technologyId " +
            "JOIN Employee e ON e.employeeId = et.employeeTechnologyKey.employeeId" +
            " WHERE et.employeeTechnologyKey.technologyId = ?1 AND e.company.companyId = ?2 ")
    List<Object> getEmployeeTechnologiesForEmployeeAndCompany(Long technologyId,Long companyId);

    @Query("SELECT et from EmployeeTechnologies et JOIN Employee e ON " +
            "et.employeeTechnologyKey.employeeId = e.employeeId WHERE e.company.companyId = ?1")
    List<EmployeeTechnologies> getEmployeeTechnologiesByCompany(Long companyId);

}
