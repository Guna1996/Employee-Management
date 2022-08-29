/*
 * Copyright (c) 2022, ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */
package com.ideas2it.dto;

import java.time.LocalDate;  
 
public class ProjectDto {
    protected int id;
    protected String name;
    protected String clientName;
    protected String companyName;
    protected LocalDate startDate;
    protected String estimatedDuration;
    protected String estimatedBudget;
    protected String technologyUsed;
    protected String status;
   
    public ProjectDto() {
    }
    
    public ProjectDto(String name, String clientName, String companyName, LocalDate startDate, String estimatedDuration, String estimatedBudget, 
        String technologyUsed, String status) { 
        this.name = name;
        this.clientName = clientName;
        this.companyName = companyName;
        this.startDate = startDate;
        this.estimatedDuration = estimatedDuration;
        this.estimatedBudget = estimatedBudget;
        this.technologyUsed = technologyUsed;
        this.status = status;
    }
 
    public ProjectDto(int id, String name, String clientName, String companyName, LocalDate startDate, String estimatedDuration, String estimatedBudget, 
        String technologyUsed, String status) { 
        this.id = id;
        this.name = name;
        this.clientName = clientName;
        this.companyName = companyName;
        this.startDate = startDate;
        this.estimatedDuration = estimatedDuration;
        this.estimatedBudget = estimatedBudget;
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

    public String getEstimatedBudget() {
        return estimatedBudget;
    }
 
    public String getTechnologyUsed() {
        return technologyUsed;
    }
  
    public String getStatus() {
        return status;
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

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEstimatedDuration(String estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    public void setEstimatedBudget(String estimatedBudget) {
        this.estimatedBudget = estimatedBudget;
    }
 
    public void setTechnologyUsed(String technologyUsed) {
        this.technologyUsed = technologyUsed;
    }
 
    public void setStatus(String status) {
        this.status = status;
    }

   
}