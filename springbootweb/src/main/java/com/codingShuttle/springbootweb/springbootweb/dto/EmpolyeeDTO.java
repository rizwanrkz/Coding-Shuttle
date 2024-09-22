package com.codingShuttle.springbootweb.springbootweb.dto;

import java.time.LocalDate;

public class EmpolyeeDTO {

    private Long id;
    private String name;
    private String email;
    private Integer age;
    private LocalDate dateOfJoining;
    private Boolean isAcative;

    public EmpolyeeDTO(){

    }

    public EmpolyeeDTO(Long id, String name, String email, Integer age, LocalDate dateOfJoining, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.dateOfJoining = dateOfJoining;
        this.isAcative = isActive;
    }


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

    public LocalDate getDateOfJoining(){
        return dateOfJoining;
    }

    public void setDateOfJoining(LocalDate dateOfJoining){
        this.dateOfJoining = dateOfJoining;
    }

    public  boolean getIsActive(){
        return isAcative;
    }

    public void setIsActive(Boolean isAcative){
        this.isAcative = isAcative;
    }


}
