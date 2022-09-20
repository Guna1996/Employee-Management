/*
 * Copyright (c) 2022, ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */
package com.ideas2it.model;

import java.sql.Timestamp;
import java.time.LocalDate;  

import javax.persistence.Column;
import javax.persistence.Entity;  
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;  
import javax.persistence.Table; 
import javax.persistence.Transient;

import org.hibernate.annotations.ColumnDefault; 
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * The {@code EmployeeProject} has data members and its getter setters, where annotations is used to create table using hibernate.
 * 
 *
 * @author  Gunaseelan K
 * since 1.0
 * jls Advanced logics+
 */  
@Entity
@Table(name = "employee_project")
public class EmployeeProject {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false, columnDefinition="INT NOT NULL AUTO_INCREMENT")
    private int id;

    @Transient
    private int employeeId;

    @Transient
    private int projectId;

    @Column(name = "status")
    private String status;

    @Column(name = "assigned_date")
    private LocalDate assignedDate;

    @Column(name = "assigned_by")
    private String assignedBy;

    @Column(name = "employee_role")
    private String employeeRole;

    @Column(name = "relieved_date")
    private LocalDate relievedDate;

    @CreationTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_date")
    private Timestamp createdAt;

    @UpdateTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated_date")
    private Timestamp updatedAt;
  
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public EmployeeProject() {
    }
   
    public EmployeeProject(int id, int employeeId, int projectId, LocalDate assignedDate, String assignedBy, String status, String employeeRole, LocalDate relievedDate) { 
        this.id = id;
        this.employeeId = employeeId;
        this.projectId = projectId;
        this.assignedDate = assignedDate;
        this.assignedBy = assignedBy;
        this.status = status;
        this.employeeRole = employeeRole;
        this.relievedDate = relievedDate;
        
    }
 
    public EmployeeProject(LocalDate assignedDate, String assignedBy, String status, String employeeRole, LocalDate relievedDate, Employee employee, Project project) { 
        this.employeeId = employeeId;
        this.projectId = projectId;
        this.assignedDate = assignedDate;
        this.assignedBy = assignedBy;
        this.status = status;
        this.employeeRole = employeeRole;
        this.relievedDate = relievedDate;
        this.employee = employee;
        this.project = project;
    }

    public int getId() {
        return id;
    }
    
    public int getEmployeeId() {
        return employeeId;
    }

    public int getProjectId() {
        return projectId;
    }

    public LocalDate getAssignedDate() {
        return assignedDate;
    }
    
    public String getAssignedBy() {
        return assignedBy;
    }    

    public String getStatus() {
        return status;
    }
  
    public String getEmployeeRole() {
        return employeeRole;
    }

    public LocalDate getRelievedDate() {
        return relievedDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }


    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public void setAssignedDate(LocalDate assignedDate) {
        this.assignedDate = assignedDate;
    }

    public void setAssignedBy(String assignedBy) {
        this.assignedBy = assignedBy;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setEmployeeRole(String employeeRole) {
        this.employeeRole = employeeRole;
    }
 
    public void setRelievedDate(LocalDate relievedDate) {
        this.relievedDate = relievedDate;
    } 
}