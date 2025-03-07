package com.codingShuttle.JUnit.JUnit.services.impl;

import com.codingShuttle.JUnit.JUnit.dto.EmployeeDTO;
import com.codingShuttle.JUnit.JUnit.entities.Employee;
import com.codingShuttle.JUnit.JUnit.exceptions.ResourceNotFoundException;
import com.codingShuttle.JUnit.JUnit.repositories.EmployeeRepository;
import com.codingShuttle.JUnit.JUnit.services.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestConfiguration.class)
@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Spy
    private ModelMapper modelMapper;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee mockEmployee;

    private EmployeeDTO mockEmployeeDto;

    @BeforeEach
    void setup() {
        mockEmployee = Employee.builder()
                .id(1L)
                .email("email.com")
                .name("vk")
                .salary(200L)
                .build();

        mockEmployeeDto = modelMapper.map(mockEmployee, EmployeeDTO.class);
    }

    @Test
    void testGetEmployeeById_WhenEmployeeIdIsPresent_ThenReturnEmployeeDto() {
        Long id = mockEmployee.getId();

        when(employeeRepository.findById(id)).thenReturn(Optional.of(mockEmployee));

        EmployeeDTO employeeDTO = employeeService.getEmployeeById(id);

        assertThat(employeeDTO.getId()).isEqualTo(id);
        assertThat(employeeDTO.getEmail()).isEqualTo(mockEmployee.getEmail());
        verify(employeeRepository).findById(id);

    }

    @Test
    void testGetEmployeeById_WhenEmployeeIdIsNotPresent_ThenThrowException() {
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> employeeService.getEmployeeById(1L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Employee not found with id: 1");

        verify(employeeRepository).findById(1L);

    }

    @Test
    void testCreateNewEmployee_WhenValidEmployee_ThenCreateNewEmployee() {
        when(employeeRepository.findByEmail(anyString())).thenReturn(List.of());
        when(employeeRepository.save(any(Employee.class))).thenReturn(mockEmployee);

        EmployeeDTO employeeDto = employeeService.createNewEmployee(mockEmployeeDto);

        ArgumentCaptor<Employee> employeeArgumentCaptor = ArgumentCaptor.forClass(Employee.class);

        assertThat(employeeDto).isNotNull();
        assertThat(employeeDto.getEmail()).isEqualTo(mockEmployeeDto.getEmail());
        verify(employeeRepository).save(any(Employee.class));

        Employee capturedEmployee = employeeArgumentCaptor.getValue();
        assertThat(capturedEmployee.getEmail()).isEqualTo(mockEmployee.getEmail());
    }

    @Test
    void testCreateNewEmployee_whenAttemptingToCreateEmployeeWithExistingEmail_thenThrowException() {
        when(employeeRepository.findByEmail(mockEmployee.getEmail())).thenReturn(List.of(mockEmployee));

        assertThatThrownBy(() -> employeeService.createNewEmployee(mockEmployeeDto))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Employee already exists with email " + mockEmployee.getEmail());

        verify(employeeRepository).findByEmail((mockEmployee.getEmail()));
        verify(employeeRepository, never()).save(any());
    }

    @Test
    void testUpdateEmployee_whenEmployeeDoesNotExists_thenThrowException() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> employeeService.updateEmployee(1L, mockEmployeeDto))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("employee not found with id: 1");

        verify(employeeRepository).findById(1L);
        verify(employeeRepository, never()).save(any());

    }

    @Test
    void testUpdateEmployee_whenAttemptingToUpdateEmail_thenThrowException() {
        when(employeeRepository.findById(mockEmployeeDto.getId())).thenReturn(Optional.of(mockEmployee));
        mockEmployeeDto.setName("Random");
        mockEmployeeDto.setEmail("random@gmail.com");

        assertThatThrownBy(() -> employeeService.updateEmployee(mockEmployeeDto.getId(), mockEmployeeDto))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("The email of the employee cannot be updated");

        verify(employeeRepository).findById(mockEmployeeDto.getId());
        verify(employeeRepository, never()).save(any());

    }

    @Test
    void testUpdateEmployee_whenValidEmployee_thenUpdateEmployee() {
        when(employeeRepository.findById(mockEmployeeDto.getId())).thenReturn(Optional.empty());
        mockEmployeeDto.setName("abd");
        mockEmployeeDto.setSalary(199L);

        Employee newEmployee = modelMapper.map(mockEmployeeDto,Employee.class);
        when(employeeRepository.save(any(Employee.class))).thenReturn(newEmployee);

        EmployeeDTO updatedEmployeeDto = employeeService.updateEmployee(mockEmployeeDto.getId(), mockEmployeeDto);

        assertThat(updatedEmployeeDto).isEqualTo(mockEmployeeDto);

        verify(employeeRepository).findById(1L);
        verify(employeeRepository).save(any());
    }

    @Test
    void testDeleteEmployee_whenEmployeeDoesNotExists_thenTrowException() {

        when(employeeRepository.existsById(1L)).thenReturn(false);

        assertThatThrownBy(() -> employeeService.deleteEmployee(1L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Employee not found with id: " + 1L);

        verify(employeeRepository, never()).deleteById(anyLong());
    }

    @Test
    void testDeleteEmployee_whenEmployeeIsValid_thenDeleteEmployee() {
        when(employeeRepository.existsById(1L)).thenReturn(true);

        assertThatCode(() -> employeeService.deleteEmployee(1l))
                .doesNotThrowAnyException();
        verify(employeeRepository).deleteById(1L);
    }
}