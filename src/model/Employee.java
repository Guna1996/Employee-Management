/*
 * Copyright (c) 2022, ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */
package com.ideas2it.model;

import java.time.LocalDate;  
 
public class Employee {
    protected int id;
    protected String firstName;
    protected String lastName;
    protected LocalDate dob;
    protected String gender;
    protected LocalDate dateOfJoining;
    protected String batch;
    protected String designation;
    protected String city;
    protected String fatherName;
    protected String email;
    protected String phoneNumber;
    protected String status;
   
    public Employee() {
    }
    
    public Employee(String firstName, String lastName, LocalDate dob, String gender, LocalDate dateOfJoining, String batch, 
        String designation, String city, String fatherName, String email,  String phoneNumber, String status) { 
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.gender = gender;
        this.dateOfJoining = dateOfJoining;
        this.batch = batch;
        this.designation = designation;
        this.city = city;
        this.fatherName = fatherName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.status = status;
    }
 
    public Employee(int id, String firstName, String lastName, LocalDate dob, String gender, LocalDate dateOfJoining, String batch, 
        String designation, String city, String fatherName, String email,  String phoneNumber, String status) { 
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.dob = dob;
        this.gender = gender;
        this.dateOfJoining = dateOfJoining;
        this.batch = batch;
        this.designation = designation;
        this.city = city;
        this.fatherName = fatherName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
    }

     public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }


    public String getBatch() {
        return batch;
    }
 
    public String getDesignation() {
        return designation;
    }

    public String getCity() {
        return city;
    }


    public String getFatherName() {
        return fatherName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }
    
    public void setBatch(String batch) {
        this.batch = batch;
    }
 
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    
}