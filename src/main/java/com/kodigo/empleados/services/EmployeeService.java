package com.kodigo.empleados.services;

import com.kodigo.empleados.entities.EmployeeEntity;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<EmployeeEntity> listEmployees();
    Optional<EmployeeEntity> searchEmployeeById(Integer id);
    List<EmployeeEntity> findEmployeesByDepartment(String department);
    EmployeeEntity save(EmployeeEntity employee);
    void remove(Integer id);

}
