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
 
public class EmployeeProjectMapper {
    EmployeeMapper employeeMapper = new EmployeeMapper();
    ProjectMapper projectMapper = new ProjectMapper();  
    public EmployeeProject fromDto(EmployeeProjectDto employeeProjectDto) {
        Employee employee = employeeMapper.fromDtoId(employeeProjectDto.getEmployeeDto());
        Project project = projectMapper.fromDtoId(employeeProjectDto.getProjectDto());
        EmployeeProject employeeProject = new EmployeeProject(employeeProjectDto.getAssignedDate(),
            employeeProjectDto.getAssignedBy(), employeeProjectDto.getStatus(), employeeProjectDto.getEmployeeRole(), employeeProjectDto.getRelievedDate() ,employee ,project);
        return employeeProject;
    }

    public EmployeeProjectDto toDto(EmployeeProject employeeProject) {
        EmployeeProjectDto employeeProjectDto = new EmployeeProjectDto(employeeProject.getId(),employeeProject.getProjectId(), employeeProject.getEmployeeId(), employeeProject.getAssignedDate(),
            employeeProject.getAssignedBy(), employeeProject.getStatus(), employeeProject.getEmployeeRole(), employeeProject.getRelievedDate());
        return employeeProjectDto;
    }
}