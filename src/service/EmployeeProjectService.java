/*
 * Copyright (c) 2022, ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */
package com.ideas2it.service;

import com.ideas2it.dao.EmployeeProjectDao;
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
import com.ideas2it.service.EmployeeService;
import com.ideas2it.service.ProjectService;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;


/**
 * The {@code EmployeeProjectService} class contains the method for assigning employees to project. access 
 * and controls the methods of the classes, such as {@code EmployeeService} {@code ProjectService} {@code EmployeeProjectDao} {@code EmployeeProjectMApper}, Accessed by  
 * creating an instance of that classes.
 *
 *
 * @author  Gunaseelan K
 * since 1.0
 * jls Advanced logics+
 */
public class EmployeeProjectService {

    EmployeeService employeeService = new EmployeeService();
    ProjectMapper projectMapper = new ProjectMapper();
    ProjectService projectService = new ProjectService();
    EmployeeProjectDao employeeProjectDao = new EmployeeProjectDao();
    EmployeeProjectMapper employeeProjectMapper = new EmployeeProjectMapper();
    EmployeeMapper employeeMapper = new EmployeeMapper();
   
    /**
     * <p>
     * This method is used to assign employees to project
     * </p>
     * 
     * @parm assignedEmployeesToProjectDto is data transfer object which has employee details and project id
     *
     */
    public String assignProjectToEmployees(List<EmployeeProjectDto> assignedEmployeesToProjectDto) throws CustomException {
        List<EmployeeProject> assignedEmployeesToProject = new ArrayList<EmployeeProject>();
        for (EmployeeProjectDto employeeProjectDto: assignedEmployeesToProjectDto) {
            EmployeeDto employeeDto = employeeService.getEmployeeDetailsById(employeeProjectDto.getEmployeeId()); 
            employeeProjectDto.setEmployeeDto(employeeDto);
            ProjectDto projectDto = projectService.getProjectsDetailsById(employeeProjectDto.getProjectId());
            employeeProjectDto.setProjectDto(projectDto);
            EmployeeProject employeeProject = employeeProjectMapper.fromDto(employeeProjectDto);
            assignedEmployeesToProject.add(employeeProject);
        }  
        return employeeProjectDao.assignProjectToEmployees(assignedEmployeesToProject);
    }

    /**
     * <p>
     * This method is used to get assigned Project to Employees
     * </p>
     *
     */
    public List<EmployeeProjectDto> getassignedProjectToEmployees() throws CustomException{
        List<EmployeeProject> employeesProjects = employeeProjectDao.retrieveAssignedProjectsToEmployees();
        List<EmployeeProjectDto> employeesProjectsDto = new ArrayList<EmployeeProjectDto>();
        for (EmployeeProject employeeProject: employeesProjects) {
            employeeProject.setEmployeeId(employeeProject.getEmployee().getId());
            employeeProject.setProjectId(employeeProject.getProject().getId());
            EmployeeProjectDto employeeProjectDto = employeeProjectMapper.toDto(employeeProject);
            employeesProjectsDto.add(employeeProjectDto);
        }  
        return employeesProjectsDto;         
    }
  
    /**
     * <p>
     * This method is used to get assigned employees to a project by project id
     * </p>
     *
     * @parm projectId is id of the project
     */
    public List<EmployeeDto> getEmployeesDetailsByProjectId(int projectId) throws CustomException {
        List<EmployeeDto> employeeDtos = new ArrayList<EmployeeDto>();
        Project project = projectService.getProjectById(projectId);
        List<EmployeeProject> employeesAssignedToProject = project.getEmployeesAssignedToProject();
        for (EmployeeProject employeeProject: employeesAssignedToProject) {
            Employee employee = employeeProject.getEmployee();
            employee.setRoleName(employee.getRole().get(0).getName());
            EmployeeDto employeeDto = employeeMapper.toDto(employee);
            employeeDtos.add(employeeDto);
        }  
        return employeeDtos;
    }

    /**
     * <p>
     * This method is used to get assigned projects to a employee by empployee id
     * </p>
     *
     * @parm employeeId is id of the employee
     */
    public List<ProjectDto> getProjectsDetailsByEmployeeId(int employeeId) throws CustomException {
        List<ProjectDto> projectDtos = new ArrayList<ProjectDto>();
        Employee employee = employeeService.getEmployeeById(employeeId);
        List<EmployeeProject> projectsAssignedToEmployee = employee.getProjectsAssignedToEmployee();
        for (EmployeeProject employeeProject: projectsAssignedToEmployee) {
            Project project = employeeProject.getProject();
            ProjectDto projectDto = projectMapper.toDto(project);
            projectDtos.add(projectDto);
        }  
        return projectDtos;
    }                
}