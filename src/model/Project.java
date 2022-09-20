/*
 * Copyright (c) 2022, ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */
package com.ideas2it.model;

import com.ideas2it.model.Employee;  

import java.sql.Timestamp;
import java.time.LocalDate;  
import java.util.ArrayList; 
import java.util.List;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;  
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;  
import javax.persistence.OneToMany;
import javax.persistence.Table; 

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp; 

/**
 * The {@code Project} has data members and its getter setters, where annotations is used to create table using hibernate.
 * 
 *
 * @author  Gunaseelan K
 * since 1.0
 * jls Advanced logics+
 */ 
@Entity
@Table(name = "project")
public class Project {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @Column(name = "name")
    private String name;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "start_name")
    private LocalDate startDate;

    @Column(name = "estimated_duration")
    private String estimatedDuration;

    @Column(name = "description")
    private String description;

    @Column(name = "technology_used")
    private String technologyUsed;

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
    
    @OneToMany(mappedBy = "project", fetch = FetchType.EAGER)
    private List<EmployeeProject> employeesAssignedToProject;

    public Project() {
    }

    public Project(String name, String clientName, String companyName, LocalDate startDate, String estimatedDuration, String description, 
        String technologyUsed, String status) { 
        this.name = name;
        this.clientName = clientName;
        this.companyName = companyName;
        this.startDate = startDate;
        this.estimatedDuration = estimatedDuration;
        this.description = description;
        this.technologyUsed = technologyUsed;
        this.status = status;
    }
 
    public Project(int id, String name, String clientName, String companyName, LocalDate startDate, String estimatedDuration, String description, 
        String technologyUsed, String status) { 
        this.id = id;
        this.name = name;
        this.clientName = clientName;
        this.companyName = companyName;
        this.startDate = startDate;
        this.estimatedDuration = estimatedDuration;
        this.description = description;
        this.technologyUsed = technologyUsed;
        this.status = status;
    }

    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public String getClientName() {
        return clientName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public String getEstimatedDuration() {
        return estimatedDuration;
    }

    public List<EmployeeProject> getEmployeesAssignedToProject() {
        return employeesAssignedToProject;
    }

    public String getDescription() {
        return description;
    }
 
    public String getTechnologyUsed() {
        return technologyUsed;
    }
  
    public String getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }    

    public void setName(String name) {
        this.name = name;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setEmployeesAssignedToProject(List<EmployeeProject> employeesAssignedToProject) {
        this.employeesAssignedToProject = employeesAssignedToProject;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEstimatedDuration(String estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    public void setDescription(String description) {
        this.description = description;
    }
 
    public void setTechnologyUsed(String technologyUsed) {
        this.technologyUsed = technologyUsed;
    }
 
    public void setStatus(String status) {
        this.status = status;
    }

   
}