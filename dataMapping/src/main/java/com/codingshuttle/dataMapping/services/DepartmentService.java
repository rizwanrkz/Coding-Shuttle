package com.codingshuttle.dataMapping.services;

import com.codingshuttle.dataMapping.entities.DepartmentEntity;
import com.codingshuttle.dataMapping.entities.EmployeeEntity;
import com.codingshuttle.dataMapping.repositories.DepartmentRepository;
import com.codingshuttle.dataMapping.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public DepartmentService(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    public DepartmentEntity createNewDepartment(DepartmentEntity departmentEntity) {
        return departmentRepository.save(departmentEntity);
    }

    public DepartmentEntity getDepartmentById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    public DepartmentEntity assignManagerToDepartment(Long departmentId, Long employeeId) {
        Optional<DepartmentEntity> departmentEntity = departmentRepository.findById(departmentId);
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);

        return departmentEntity.flatMap(department ->
                employeeEntity.map(employee -> {
                    department.setManager(employee);
                    return  departmentRepository.save(department);
                })).orElse(null);
    }

    public DepartmentEntity getAssignManagerToDepartment(Long employeeId) {
      //  Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);
      //  return employeeEntity.map(employee -> employee.getManagedDepartment()).orElse(null);

        EmployeeEntity employeeEntity = EmployeeEntity.builder().id(employeeId).build();
        return departmentRepository.findByManager(employeeEntity);

    }
}
