package com.kodigo.empleados.services.impl;

import com.kodigo.empleados.dao.EmployeeDao;
import com.kodigo.empleados.entities.EmployeeEntity;
import com.kodigo.empleados.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeEntity> listEmployees() {
        return (List<EmployeeEntity>) employeeDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EmployeeEntity> searchEmployeeById(Integer id) {
        return employeeDao.findEmployeeById(id);
    }

    @Override
    public List<EmployeeEntity> findEmployeesByDepartment(String department) {
        return employeeDao.findEmployeesByDepartment(department);
    }

    @Override
    @Transactional
    public EmployeeEntity save(EmployeeEntity employee) {
        return employeeDao.save(employee);
    }

    @Override
    @Transactional
    public void remove(Integer id) {
        employeeDao.deleteById(id);
    }
}
