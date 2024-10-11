package com.codingShuttle.springbootweb.springbootweb.services;

import com.codingShuttle.springbootweb.springbootweb.dto.EmployeeDTO;
import com.codingShuttle.springbootweb.springbootweb.entities.EmployeeEntity;
import com.codingShuttle.springbootweb.springbootweb.exceptions.ResourceNotFoundException;
import com.codingShuttle.springbootweb.springbootweb.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private  final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<EmployeeDTO> getEmployeeById(Long id) {
        /*EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
        return modelMapper.map(employeeEntity, EmployeeDTO.class);*/

        return employeeRepository.findById(id).map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class));
    }


    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO createNewEmployee(EmployeeDTO inputEmployee) {
        EmployeeEntity toSaveEntity = modelMapper.map(inputEmployee, EmployeeEntity.class);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(toSaveEntity);
        return  modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployeeById(EmployeeDTO employeeDTO, Long employeeId) {
        isExistsByEmployeeId(employeeId);
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }

    public boolean deleteEmployeeById(Long employeeId) {
        isExistsByEmployeeId(employeeId);
        employeeRepository.deleteById(employeeId);
        return true;
    }

    public EmployeeDTO patchEmployeeById(Map<String, Object> updates, Long employeeId) {
        isExistsByEmployeeId(employeeId);
         EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
         updates.forEach((field, value) -> {
             Field fieldToBeUpdated = ReflectionUtils.findRequiredField(EmployeeEntity.class, field);
             fieldToBeUpdated.setAccessible(true);
             ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value);
         });
         return modelMapper.map(employeeRepository.save(employeeEntity), EmployeeDTO.class);
    }

    public void isExistsByEmployeeId(Long employeeId){
        boolean exists = employeeRepository.existsById(employeeId);
        if(!exists) throw new ResourceNotFoundException("Employee not found with id: "+employeeId);
    }
}
