/*
 * Copyright (c) 2022, ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */
package com.ideas2it.model;

import java.time.LocalDate;  
 
public class EmployeeProject {
    protected int id;
    protected int employeeId;
    protected int projectId;
    protected String status;
    protected LocalDate assignedDate;
    protected String assignedBy;
    protected String employeeRole;
    protected LocalDate relievedDate;

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
 
    public EmployeeProject(int employeeId, LocalDate assignedDate, String assignedBy, String status, String employeeRole, LocalDate relievedDate) { 
        this.employeeId = employeeId;
        this.projectId = projectId;
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