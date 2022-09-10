/*
 * Copyright (c) 2022, ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */
package com.ideas2it.dto;

import com.ideas2it.dto.EmployeeDto;
import com.ideas2it.dto.ProjectDto;

import java.time.LocalDate; 
  
public class EmployeeProjectDto {
    protected int id;
    protected int employeeId;
    protected int projectId;
    protected String status;
    protected LocalDate assignedDate;
    protected String assignedBy;
    protected String employeeRole;
    protected LocalDate relievedDate;
    protected EmployeeDto employeeDto;
    protected ProjectDto projectDto;
   
    public EmployeeProjectDto(int id, int projectId, int employeeId, LocalDate assignedDate, String assignedBy, String status, String employeeRole, LocalDate relievedDate) { 
        this.id = id;
        this.employeeId = employeeId;
        this.projectId = projectId;
        this.assignedDate = assignedDate;
        this.assignedBy = assignedBy;
        this.status = status;
        this.employeeRole = employeeRole;
        this.relievedDate = relievedDate;
    }

    public EmployeeProjectDto() {
    }
 
    public EmployeeProjectDto(LocalDate assignedDate, String assignedBy, String status, String employeeRole, LocalDate relievedDate) { 
        this.assignedDate = assignedDate;
        this.assignedBy = assignedBy;
        this.status = status;
        this.employeeRole = employeeRole;
        this.relievedDate = relievedDate;
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

    public EmployeeDto getEmployeeDto() {
        return employeeDto;
    }

    public ProjectDto getProjectDto() {
        return projectDto;
    }

    public void setProjectDto(ProjectDto projectDto) {
        this.projectDto = projectDto;
    }


    public void setEmployeeDto(EmployeeDto employeeDto) {
        this.employeeDto = employeeDto;
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
    
    @Override
    public String toString() {
        String output = String.format("%20s %20s %20s %20s %20s %20s %20s %20s\n", id, employeeId, projectId, assignedDate, assignedBy, status, employeeRole, relievedDate);    
        return output;   
    }
}