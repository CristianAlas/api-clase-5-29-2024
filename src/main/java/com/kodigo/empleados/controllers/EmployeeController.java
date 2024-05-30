package com.kodigo.empleados.controllers;


import com.kodigo.empleados.dao.EmployeeDao;
import com.kodigo.empleados.entities.EmployeeEntity;
import com.kodigo.empleados.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService service;


    @GetMapping("/employee")
    public List<EmployeeEntity> listEmploee() {
        return service.listEmployees();
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeEntity> searchEmployeeById(@PathVariable Integer id) {
        Optional<EmployeeEntity> employee = service.searchEmployeeById(id);
        return employee
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{department}")
    public ResponseEntity<List<EmployeeEntity>> findEmployeesByDepartment(@PathVariable String department) {
        List<EmployeeEntity> employees = service.findEmployeesByDepartment(department);
        if (employees.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(employees, HttpStatus.OK);
        }
    }

    @PostMapping("/employee")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeEntity addEmployee(@RequestBody EmployeeEntity employee) {
        return service.save(employee);
    }

    @PutMapping("/employee/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeEntity updateEmployee(@PathVariable Integer id, @RequestBody EmployeeEntity employee) {
        EmployeeEntity employeeEntity = service.searchEmployeeById(id).get();

        employeeEntity.setName(employee.getName());
        employeeEntity.setSurname(employee.getSurname());
        employeeEntity.setAddress(employee.getAddress());
        employeeEntity.setSalary(employee.getSalary());
        employeeEntity.setDepartment(employee.getDepartment());

        return service.save(employeeEntity);
    }

    @DeleteMapping("/employee/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable Integer id) {
        service.remove(id);
    }
}
