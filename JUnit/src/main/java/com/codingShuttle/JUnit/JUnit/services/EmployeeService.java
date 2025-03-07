package com.codingShuttle.JUnit.JUnit.services;

import com.codingShuttle.JUnit.JUnit.dto.EmployeeDTO;

public interface EmployeeService {

    EmployeeDTO getEmployeeById(Long id);
    EmployeeDTO createNewEmployee(EmployeeDTO employeeDto);
    EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDto);
    void deleteEmployee(Long id);
}
