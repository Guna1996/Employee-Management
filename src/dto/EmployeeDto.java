/*
 * Copyright (c) 2022, ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */
package com.ideas2it.dto;

import java.time.LocalDate;   

public class EmployeeDto {
    protected int id;
    protected String firstName;
    protected String lastName;
    protected String staffNumber;
    protected LocalDate dob;
    protected String gender;
    protected LocalDate dateOfJoining;
    protected String batch;
    protected String designation;
    protected String city;
    protected String fatherName;
    protected String email;
    protected String phoneNumber;

    public EmployeeDto() {
    }
   
    public EmployeeDto(String firstName, String lastName, String staffNumber, LocalDate dob, String gender, LocalDate dateOfJoining, String batch, 
        String designation, String city, String fatherName, String email,  String phoneNumber) { 
        this.firstName = firstName;
        this.lastName = lastName;
        this.staffNumber = staffNumber;
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

    public EmployeeDto(int id, String firstName, String lastName, String staffNumber, LocalDate dob, String gender, LocalDate dateOfJoining, String batch, 
        String designation, String city, String fatherName, String email,  String phoneNumber) { 
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.staffNumber = staffNumber;
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

    public String getStaffNumber() {
        return staffNumber;
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

    public void setStaffNumber(String staffNumber) {
        this.staffNumber = staffNumber;
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

    @Override
    public String toString() {
        String output = String.format("%5s %17s %8s %8s %15s %8s %15s %5s %15s %8s %15s %20s %13s\n", id, firstName, lastName, staffNumber, dob, gender, dateOfJoining, batch, 
        designation, city, fatherName, email, phoneNumber);    
        return output;   
    }
}  