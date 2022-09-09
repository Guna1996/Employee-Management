/*
 * Copyright (c) 2022, ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */
package com.ideas2it.service;


import com.ideas2it.dao.EmployeeDao;
import com.ideas2it.dao.EmployeeProjectDao;
import com.ideas2it.dao.ProjectDao;
import com.ideas2it.dao.RoleDao;
import com.ideas2it.dto.EmployeeDto;
import com.ideas2it.dto.EmployeeProjectDto;
import com.ideas2it.dto.ProjectDto;
import com.ideas2it.exception.CustomException;
import com.ideas2it.mapper.EmployeeMapper;
import com.ideas2it.mapper.EmployeeProjectMapper;
import com.ideas2it.mapper.ProjectMapper;
import com.ideas2it.model.Employee;
import com.ideas2it.model.EmployeeProject;
import com.ideas2it.model.Project;
import com.ideas2it.model.Role;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;


/**
 * The {@code Service} class contains the method for trainees, trainers and Human Resource Services. access 
 * and controls the methods of the classes, such as {@code TraineeDao} {@code TrainerDao} {@code HumanResourceDao}, Accessed by  
 * creating an instance of that classes.
 * Implementation to perform Trainer and Trainee related Services
 *
 *
 * @author  Gunaseelan K
 * since 1.0
 * jls Advanced logics+
 */
public class EmployeeProjectService {

    EmployeeDao employeeDao = new EmployeeDao();
    EmployeeProjectDao employeeProjectDao = new EmployeeProjectDao();
    EmployeeProjectMapper employeeProjectMapper = new EmployeeProjectMapper();
   
    /**
     * <p>
     * 
     * </p>
     * 
     * @parm 
     */     

    public boolean assignProjectToEmployees(List<EmployeeProjectDto> assignedEmployeesToProjectDto) throws CustomException {
        List<EmployeeProject> assignedEmployeesToProject = new ArrayList<EmployeeProject>();
        for (EmployeeProjectDto employeeProjectDto: assignedEmployeesToProjectDto) {
            EmployeeProject employeeProject = employeeProjectMapper.fromDto(employeeProjectDto);
            assignedEmployeesToProject.add(employeeProject);
        }  
        return employeeProjectDao.assignProjectToEmployees(assignedEmployeesToProject);
    }  
}