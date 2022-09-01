/*
 * Copyright (c) 2022, ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */
package com.ideas2it.model;

import java.time.LocalDate;  
 
public class Project {
    protected int id;
    protected String name;
    protected String clientName;
    protected String companyName;
    protected LocalDate startDate;
    protected String estimatedDuration;
    protected String description;
    protected String technologyUsed;
    protected String status;
   
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

    public String getDescription() {
        return description;
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