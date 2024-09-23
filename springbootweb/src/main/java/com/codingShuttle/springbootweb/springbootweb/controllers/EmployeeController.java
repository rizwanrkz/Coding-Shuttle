package com.codingShuttle.springbootweb.springbootweb.controllers;

import com.codingShuttle.springbootweb.springbootweb.dto.EmpolyeeDTO;
import com.codingShuttle.springbootweb.springbootweb.entities.EmployeeEntity;
import com.codingShuttle.springbootweb.springbootweb.repositories.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/employees") //Parent path
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @GetMapping(path = "/getSecretMessage")
    public String getSecretMessage() {
        return "Secret message : rkz@18";
    }

    @GetMapping(path = "/{employeeId}")
    public EmployeeEntity getEmployeeById(@PathVariable(name = "employeeId") Long id){
        return employeeRepository.findById(id).orElse(null);
    }

    @GetMapping
    public List<EmployeeEntity> getAllEmployees(@RequestParam(required = false) Integer age){
        return employeeRepository.findAll();
    }

    /*@PostMapping
    public String createNewEmployee(){
        return "Walla Habibi New Employee is created";
    }*/

    @PostMapping
    public EmployeeEntity createNewEmployee(@RequestBody EmployeeEntity inputEmployee){
        return employeeRepository.save(inputEmployee);
    }


    @PutMapping String updateEmployeeById() {
        return  "Data is updated";
    }

}
