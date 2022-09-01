/*
 * Copyright (c) 2022, ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */
package com.ideas2it.mapper;

import com.ideas2it.model.EmployeeProject;
import com.ideas2it.dto.EmployeeProjectDto;
 
public class EmployeeProjectMapper {
    
    public EmployeeProject fromDto(EmployeeProjectDto employeeProjectDto) {
        EmployeeProject employeeProject = new EmployeeProject(employeeProjectDto.getEmployeeId(), employeeProjectDto.getAssignedDate(),
            employeeProjectDto.getAssignedBy(), employeeProjectDto.getStatus(), employeeProjectDto.getEmployeeRole(), employeeProjectDto.getRelievedDate());
        return employeeProject;
    }

    public EmployeeProjectDto toDto(EmployeeProject employeeProject) {
        EmployeeProjectDto employeeProjectDto = new EmployeeProjectDto(employeeProject.getId(),employeeProject.getProjectId(), employeeProject.getEmployeeId(), employeeProject.getAssignedDate(),
            employeeProject.getAssignedBy(), employeeProject.getStatus(), employeeProject.getEmployeeRole(), employeeProject.getRelievedDate());
        return employeeProjectDto;
    }
}