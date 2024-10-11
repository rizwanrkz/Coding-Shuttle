package com.codingShuttle.springbootweb.springbootweb.dto;

import com.codingShuttle.springbootweb.springbootweb.annotations.EmployeeRoleValidation;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private Long id;

    @NotNull(message = "Required Field in Employee: name")
    @NotBlank(message = "Name of the employee cannot be null")
    @Size(min = 3, max = 10, message = "Number of characters in name should be in range: [3, 10]")
    private String name;

    @NotBlank(message = " Email of the employee cannot be blank")
    @Email(message = "Email should be valid email")
    private String email;

    @NotNull(message  = "Age of the employee cannot be blank")
    @Max(value = 80, message = "Age of the employee cannot be greater than 80")
    @Min(value = 18, message = "Age of the Employee cannot be less that 18")
    private Integer age;

    @NotBlank(message = "Role of the employee cannot be blank")
    //@Pattern(regexp = "^(ADMIN|USER)$", message = "Role of the employee can either be USER or ADMIN")
    @EmployeeRoleValidation
    private String role; //ADMIN, USER

    @NotNull(message = "Date Of Joining of emoyee cannot be null")
    @PastOrPresent
    private LocalDate dateOfJoining;

    @NotNull(message = "Salary of the employee cannot be null")
    @Positive(message = "Salary of the employee should be positive")
    @Digits(integer = 6, fraction = 2)
    @DecimalMax(value = "100000.99")
    @DecimalMin(value = "100.50")
    private Double salary;

    @AssertTrue(message = "employee should be active")
    private Boolean isActive;

    /*public EmployeeDTO(){

    }

    public EmployeeDTO(Long id, String name, String email, Integer age, LocalDate dateOfJoining, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.dateOfJoining = dateOfJoining;
        this.isActive = isActive;
    }*/


    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public Integer getAge() {return age;}

    public void setAge(Integer age){
        this.age = age;
    }

    public LocalDate getDateOfJoining(){
        return dateOfJoining;
    }

    public void setDateOfJoining(LocalDate dateOfJoining){
        this.dateOfJoining = dateOfJoining;
    }

    public  boolean getIsActive(){
        return isActive;
    }

    public void setIsActive(Boolean isActive){
        this.isActive = isActive;
    }


}
