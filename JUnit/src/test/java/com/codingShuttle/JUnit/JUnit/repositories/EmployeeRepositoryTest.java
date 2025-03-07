package com.codingShuttle.JUnit.JUnit.repositories;

import com.codingShuttle.JUnit.JUnit.entities.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

/*@SpringBootTest*/
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeRepositoryTest {

    @Autowired
    private  EmployeeRepository employeeRepository;

    private Employee employee;

    @BeforeEach
    void setup() {
        employee = Employee.builder()
                .id(1L)
                .name("virat")
                .email("vk@gmail.com")
                .salary(1000L)
                .build();
    }

    @Test
    void testFindByEmail_whenEmailIsValid__thenReturnEmployee() {
        employeeRepository.save(employee);

        List<Employee> employeeList = employeeRepository.findByEmail((employee.getEmail()));

        assertThat(employeeList).isNotNull();
        assertThat(employeeList).isNotEmpty();
        assertThat(employeeList.get(0).getEmail()).isEqualTo(employee.getEmail());
    }

    @Test
    void testFindByEmail_whenEmailIsNotFound__thenReturnEmptyEmployeeList() {

        String email = "abc@gmail.com";

        List<Employee> employeeList = employeeRepository.findByEmail(email);

        assertThat(employeeList).isNotNull();
        assertThat(employeeList).isEmpty();
    }
}