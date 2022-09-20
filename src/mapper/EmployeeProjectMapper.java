/*
 * Copyright (c) 2022, ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */
package com.ideas2it.mapper;

import com.ideas2it.model.EmployeeProject;
import com.ideas2it.model.Employee;
import com.ideas2it.model.Project;
import com.ideas2it.dto.EmployeeProjectDto;
import com.ideas2it.mapper.EmployeeMapper;
import com.ideas2it.mapper.ProjectMapper;
 
/**
 * The {@code EmployeeProjectMapper} has methods to convert Employeeproject dto to Employeeproject model and vice versa.
 * 
 *
 * @author  Gunaseelan K
 * since 1.0
 * jls Advanced logics+
 */ 
public class EmployeeProjectMapper {
    EmployeeMapper employeeMapper = new EmployeeMapper();
    ProjectMapper projectMapper = new ProjectMapper(); 

    /**
     * <p>
     * This method is used to convert Employeeproject dto to Employeeproject model
     * </p>
     *
     */ 
    public EmployeeProject fromDto(EmployeeProjectDto employeeProjectDto) {
        Employee employee = employeeMapper.fromDtoId(employeeProjectDto.getEmployeeDto());
        Project project = projectMapper.fromDtoId(employeeProjectDto.getProjectDto());
        EmployeeProject employeeProject = new EmployeeProject(employeeProjectDto.getAssignedDate(),
            employeeProjectDto.getAssignedBy(), employeeProjectDto.getStatus(), employeeProjectDto.getEmployeeRole(), employeeProjectDto.getRelievedDate() ,employee ,project);
        return employeeProject;
    }

    /**
     * <p>
     * This method is used to convert Employeeproject model to Employeeproject dto
     * </p>
     *
     */ 
    public EmployeeProjectDto toDto(EmployeeProject employeeProject) {
        EmployeeProjectDto employeeProjectDto = new EmployeeProjectDto(employeeProject.getId(),employeeProject.getProjectId(), employeeProject.getEmployeeId(), employeeProject.getAssignedDate(),
            employeeProject.getAssignedBy(), employeeProject.getStatus(), employeeProject.getEmployeeRole(), employeeProject.getRelievedDate());
        return employeeProjectDto;
    }
}