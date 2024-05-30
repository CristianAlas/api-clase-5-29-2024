package com.kodigo.empleados.dao;

import com.kodigo.empleados.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeDao extends CrudRepository<EmployeeEntity, Integer> {

    @Query("SELECT e FROM EmployeeEntity e WHERE e.id = :id")
    Optional<EmployeeEntity> findEmployeeById(@Param("id") Integer id);

    @Query("SELECT e FROM EmployeeEntity e WHERE e.department = :department")
    List<EmployeeEntity> findEmployeesByDepartment(@Param("department") String department);
}
