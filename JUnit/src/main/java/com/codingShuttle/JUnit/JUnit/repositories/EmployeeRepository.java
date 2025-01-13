package com.codingShuttle.JUnit.JUnit.repositories;

import com.codingShuttle.JUnit.JUnit.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByEmail(String email);
}
