package com.codingShuttle.springbootweb.springbootweb.controllers;

import com.codingShuttle.springbootweb.springbootweb.dto.EmpolyeeDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@RestController
@RequestMapping("/employees") //Parent path
public class EmployeeController {

    @GetMapping(path = "/getSecretMessage")
    public String getSecretMessage() {
        return "Secret message : rkz@18";
    }

    @GetMapping(path = "/{employeeId}")
    public EmpolyeeDTO getEmployeeById(@PathVariable(name = "employeeId") Long id){
        return new EmpolyeeDTO(id,"Rizwan", "rizwan@gmail.com", 25, LocalDate.of(2024,9,21), true);
    }

    @GetMapping
    public  String getAllEmployees(@RequestParam(required = false) Integer age){
        return "The Name of Employee is Rizwan whose age is " + age;
    }

    /*@PostMapping
    public String createNewEmployee(){
        return "Walla Habibi New Employee is created";
    }*/

    @PostMapping
    public EmpolyeeDTO createNewEmployee(@RequestBody EmpolyeeDTO inputEmployee){
        inputEmployee.setId(100l);
        return inputEmployee;
    }


    @PutMapping String updateEmployeeById() {
        return  "Data is updated";
    }

}
