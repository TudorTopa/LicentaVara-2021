package com.example.tudortopa.animo_radar.animo_radar.controller;


import com.example.tudortopa.animo_radar.animo_radar.model.Employee.ELevel;
import com.example.tudortopa.animo_radar.animo_radar.model.Employee.Employee;
import com.example.tudortopa.animo_radar.animo_radar.model.Employee.EmployeeTechnologies;
import com.example.tudortopa.animo_radar.animo_radar.model.Employee.EmployeeTechnologyKey;
import com.example.tudortopa.animo_radar.animo_radar.model.Technology.TechnologyId;
import com.example.tudortopa.animo_radar.animo_radar.repository.EmployeeRepository;
import com.example.tudortopa.animo_radar.animo_radar.repository.EmployeeTechnologiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/employeesTechnology")
public class EmployeeTechnnologyController {
    @Autowired
    EmployeeTechnologiesRepository employeeTechnologiesRepository;
    @Autowired
    EmployeeRepository employeeRepository;


    @GetMapping
    public ResponseEntity<List<EmployeeTechnologies>> getAll() {
        List<EmployeeTechnologies> employeeTechnologies = new ArrayList<>();
        employeeTechnologiesRepository.findAll().forEach(employeeTechnologies::add);
        if (employeeTechnologies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employeeTechnologies, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EmployeeTechnologies> add(@RequestBody EmployeeTechnologies employeeTechnologies) {
        try {
            EmployeeTechnologies employeeTechnologies1 = employeeTechnologiesRepository.save(new EmployeeTechnologies(
                    employeeTechnologies.getEmployeeTechnologyKey(),
                    employeeTechnologies.getSkillLevel()));
            return new ResponseEntity<>(employeeTechnologies1, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/employeesForTechnology/{id}")
    public ResponseEntity<List<Employee>> getEmployeesByTechnology(@PathVariable("id") Long technologyId) {
        List<EmployeeTechnologies> employeeTechnologies = new ArrayList<>();
        List<Employee> employees = new ArrayList<>();
        employeeTechnologiesRepository.findEmployeeTechnologiesByEmployeeTechnologyKeyTechnologyId(technologyId).forEach(employeeTechnologies::add);
        if (employeeTechnologies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        for (int i = 0; i < employeeTechnologies.size(); i++) {
            Employee employee = employeeRepository.findEmployeeByEmployeeId(employeeTechnologies.get(i).getEmployeeTechnologyKey().getEmployeeId());
            if (employee != null) {
                employees.add(employee);
            }
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/employeesByTechnology/{id}")
    public ResponseEntity<List<Object>> getEmployeesForTechnology(@PathVariable("id") Long technologyId) {
        List<Object> employees = new ArrayList<>();
        employeeTechnologiesRepository.getEmployeeTechnologiesByTechnologyKey(technologyId).forEach(employees::add);
        if (employees.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/skillLevel/employee/{employeeId}/technology/{technologyId}")
    public ResponseEntity<String> getEmployeeSkillLevelForTechnology(@PathVariable("employeeId") Long employeeId, @PathVariable("technologyId") Long technologyId) {
        ELevel skillLevel = employeeTechnologiesRepository.getSkillLevelByEmployeeTechnologyKey(new EmployeeTechnologyKey(employeeId, technologyId));
        if (skillLevel.name().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(skillLevel.name(), HttpStatus.OK);
    }

    @GetMapping("/technologiesByEmployee/{id}")
    public ResponseEntity<List<Object>> getTechnologiesForEmployee(@PathVariable("id") Long employeeId) {
        List<Object> technologies = new ArrayList<>();
        employeeTechnologiesRepository.getEmployeeTechnologiesForEmployee(employeeId).forEach(technologies::add);
        if (technologies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(technologies, HttpStatus.OK);
    }

    @GetMapping("/technologiesByEmployeeAndCompany/technology/{technologyId}/company/{companyId}")
    public ResponseEntity<List<Object>> getTechnologiesForEmployeeAndCompany(@PathVariable("technologyId") Long technologyId, @PathVariable("companyId") Long companyId) {
        List<Object> technologies = new ArrayList<>();
        employeeTechnologiesRepository.getEmployeeTechnologiesForEmployeeAndCompany(technologyId,companyId).forEach(technologies::add);
        if (technologies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(technologies, HttpStatus.OK);
    }

    @GetMapping("/employees/company/{companyId}")
    public  ResponseEntity<List<EmployeeTechnologies>> getAllEmployeesDetailsByCompany (@PathVariable("companyId") Long companyId){
        List<EmployeeTechnologies> employees = new ArrayList<>();
        employeeTechnologiesRepository.getEmployeeTechnologiesByCompany(companyId).forEach(employees::add);
        if (employees.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);

    }
}
