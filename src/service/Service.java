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
import com.ideas2it.model.Employee;
import com.ideas2it.model.EmployeeProject;
import com.ideas2it.dto.EmployeeProjectDto;
import com.ideas2it.dto.EmployeeDto;
import com.ideas2it.dto.ProjectDto;
import com.ideas2it.dao.ProjectDao;
import com.ideas2it.dao.RoleDao;
import com.ideas2it.model.Project;
import com.ideas2it.mapper.EmployeeMapper;
import com.ideas2it.mapper.EmployeeProjectMapper;
import com.ideas2it.mapper.ProjectMapper;
import com.ideas2it.exception.MyCustomException;

import java.util.List;
import java.util.ArrayList;
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
public class Service {

    EmployeeDao employeeDao = new EmployeeDao();
    ProjectDao projectDao = new ProjectDao();
    EmployeeProjectDao employeeProjectDao = new EmployeeProjectDao();
    EmployeeMapper employeeMapper = new EmployeeMapper();
    EmployeeProjectMapper employeeProjectMapper = new EmployeeProjectMapper();
    ProjectMapper projectMapper = new ProjectMapper();
    RoleDao roleDao = new RoleDao();
   
    /**
     * <p>
     * 
     * </p>
     * 
     * @parm 
     */     
    public int addEmployee(EmployeeDto employeeDto, String roleName) throws MyCustomException{
        int employeeId;
        int roleId;
        Employee employee = employeeMapper.fromDto(employeeDto);
        if (employeeDao.insertEmployee(employee) == 1) {
            employeeId = employeeDao.retrieveLastInsertedEmployeeId();
            roleId = roleDao.retrieveRoleIdByName(roleName);
            return roleDao.assignEmployeeRole(employeeId, roleId);
        } else {
            return 0;
        }            
    }

    public int updateEmployee(EmployeeDto employeeDto, String email, LocalDate dob) throws MyCustomException{
        Employee employee = employeeMapper.fromDto(employeeDto);
        return employeeDao.updateEmployee(employee, email, dob);          
    }    
 
    public int updateEmployeeDetail(String variable, String value, String email, LocalDate dob) throws MyCustomException{
        return employeeDao.updateEmployeeDetail(variable, value, email, dob);
    }

    public int deleteEmployeeById(int employeeId) throws MyCustomException{
        return employeeDao.deleteEmployeeById(employeeId);          
    }   

    public boolean checkIsEmployeeAvailable(String email, LocalDate dob, String roleName) throws MyCustomException{
        int roleId = roleDao.retrieveRoleIdByName(roleName);
        for (Employee employee: employeeDao.retrieveEmployeesByRoleId(roleId)) {
            if (employee.getEmail().equals(email) && employee.getDob().equals(dob)) {
                return true;
            }       
        }
        return false;
    }

    public List<EmployeeDto> getEmployeesDetails(String employeeRole) throws MyCustomException{
        int roleId = roleDao.retrieveRoleIdByName(employeeRole);
        List<Employee> employees = employeeDao.retrieveEmployeesByRoleId(roleId);
        List<EmployeeDto> employeeDtos = new ArrayList<EmployeeDto>();
        for (Employee employee: employees) {
            EmployeeDto employeeDto = employeeMapper.toDto(employee);
            employeeDtos.add(employeeDto);
        }  
        return employeeDtos;         
    }

    public EmployeeDto getEmployeeDetails(String email, LocalDate dob, String employeeRole) throws MyCustomException{
        int roleId = roleDao.retrieveRoleIdByName(employeeRole);
        Employee employee = employeeDao.retrieveEmployeeByEmailAndDob(email, dob, roleId);
        return employeeMapper.toDto(employee);  
    }
    
    public int addProject(ProjectDto projectDto) throws MyCustomException {
        int projectId;
        int roleId;
        Project project = projectMapper.fromDto(projectDto);
        return projectDao.insertProject(project);          
    }
 
    public int updateProject(ProjectDto projectDto, int projectId) throws MyCustomException {
        Project project = projectMapper.fromDto(projectDto);
        return projectDao.updateProject(project, projectId);          
    }

    public List<ProjectDto> getProjectsDetails() throws MyCustomException {
        List<Project> projects = projectDao.retrieveProjects();
        List<ProjectDto> projectDtos = new ArrayList<ProjectDto>();
        for (Project project: projects) {
            ProjectDto projectDto = projectMapper.toDto(project);
            projectDtos.add(projectDto);
        }  
        return projectDtos;         
    }

    public int updateProjectDetail(String variable, String value, int projectId) throws MyCustomException {
        return projectDao.updateProjectDetail(variable, value, projectId);
    }

    public int assignProjectsToEmployees(int projectId, List<EmployeeProjectDto> assignedEmployeesDto) throws MyCustomException {
        List<EmployeeProject> assignedEmployeeProjects = new ArrayList<EmployeeProject>();
        for (EmployeeProjectDto employeeProjectDto: assignedEmployeesDto) {
            EmployeeProject employeeProject = employeeProjectMapper.fromDto(employeeProjectDto);
            assignedEmployeeProjects.add(employeeProject);
        }  
        return employeeProjectDao.assignProjectsToEmployees(projectId, assignedEmployeeProjects);
    }  
   
    public List<EmployeeProjectDto> getAssignedProjectsToEmployees() throws MyCustomException {
        List<EmployeeProject> assignedEmployeesToProjects = employeeProjectDao.retrieveAssignedProjectsToEmployees();
        List<EmployeeProjectDto> assignedEmployeesToProjectsDto = new ArrayList<EmployeeProjectDto>();
        for (EmployeeProject employeeProject: assignedEmployeesToProjects) {
            EmployeeProjectDto employeeProjectDto = employeeProjectMapper.toDto(employeeProject);
            assignedEmployeesToProjectsDto.add(employeeProjectDto);
        }  
        return assignedEmployeesToProjectsDto;         
    }

    public int deleteAssignedEmployeeToProjectById(int employeeId, int projectId) throws MyCustomException {
        return employeeProjectDao.deleteAssignedEmployeeToProjectById(employeeId, projectId);  
    } 
     
}
