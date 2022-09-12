/*
 * Copyright (c) 2022, ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */
package com.ideas2it.model;

import com.ideas2it.model.Role; 

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;  
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id; 
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table; 
import javax.persistence.Transient;

import org.hibernate.annotations.ColumnDefault;  
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.UpdateTimestamp;
 
@Entity
@Table(name = "employee")
public class Employee {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
  
    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "gender")
    private String gender;

    @Column(name = "date_of_joining")
    private LocalDate dateOfJoining;

    @Column(name = "batch")
    private String batch;

    @Column(name = "designation")
    private String designation;

    @Column(name = "city")
    private String city;

    @Column(name = "father_name")
    private String fatherName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "status")
    private String status;
 
    @CreationTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_date")
    private Timestamp createdAt;

    @UpdateTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated_date")
    private Timestamp updatedAt;
   
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "employee_roles",
        joinColumns = { @JoinColumn(name = "employee_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "role_id")}
    )
    private List<Role> roles = new ArrayList<Role>();

    @OneToMany(mappedBy = "employee")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<EmployeeProject> projectsAssignedToEmployee;

    @Transient
    private String roleName; 

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
  
    public String getRoleName() {
        return roleName;
    }
    
     public List<Role> getRole() {
        return roles;
    }

      public List<EmployeeProject> getProjectsAssignedToEmployee() {
        return projectsAssignedToEmployee;
    }
    
    public void setProjectsAssignedToEmployee(List<EmployeeProject> projectsAssignedToEmployee) {
        this.projectsAssignedToEmployee = projectsAssignedToEmployee;
    }
    
    public void setRole(List<Role> roles) {
        this.roles = roles;
    }

    public void setId(int id) {
        this.id = id;
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

     public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}