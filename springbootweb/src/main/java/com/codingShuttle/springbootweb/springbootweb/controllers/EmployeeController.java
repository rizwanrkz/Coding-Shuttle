package com.codingShuttle.springbootweb.springbootweb.controllers;

import com.codingShuttle.springbootweb.springbootweb.dto.EmployeeDTO;
import com.codingShuttle.springbootweb.springbootweb.entities.EmployeeEntity;
import com.codingShuttle.springbootweb.springbootweb.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/employees") //Parent path
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {

        this.employeeService = employeeService;
    }


    @GetMapping(path = "/getSecretMessage")
    public String getSecretMessage() {
        return "Secret message : rkz@18";
    }

    @GetMapping(path = "/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable(name = "employeeId") Long id){
        return employeeService.getEmployeeById(id);
    }

    @GetMapping
    public List<EmployeeDTO> getAllEmployees(@RequestParam(required = false) Integer age){
        return employeeService.getAllEmployees();
    }

    /*@PostMapping
    public String createNewEmployee(){
        return "Walla Habibi New Employee is created";
    }*/

    @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee){
        return employeeService.createNewEmployee(inputEmployee);
    }


    @PutMapping String updateEmployeeById() {
        return  "Data is updated";
    }

}
