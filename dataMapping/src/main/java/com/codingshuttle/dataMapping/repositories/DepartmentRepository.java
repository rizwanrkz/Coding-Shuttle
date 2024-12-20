package com.codingshuttle.dataMapping.repositories;

import com.codingshuttle.dataMapping.entities.DepartmentEntity;
import com.codingshuttle.dataMapping.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {

    DepartmentEntity findByManager(EmployeeEntity employeeEntity);
}
