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
import com.ideas2it.utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;


/**
 * The {@code } class contains the method for Project Services. access 
 * and controls the methods of the classes, such as {@code ProjectDao} {@code ProjectMapper }, Accessed by  
 * creating an instance of that classes.
 *
 *
 * @author  Gunaseelan K
 * since 1.0
 * jls Advanced logics+
 */
public class ProjectService { 

    ProjectDao projectDao = new ProjectDao();
    ProjectMapper projectMapper = new ProjectMapper();

    /**
     * <p>
     * This method is used to add Project details
     * </p>
     * 
     * @parm projectDto is data transfer object which has project details
     *
     */
    public int addProject(ProjectDto projectDto) throws CustomException {
        Project project = projectMapper.fromDto(projectDto);
        return projectDao.insertProject(project);          
    }
 
    /**
     * <p>
     * This method is used to update Project details
     * </p>
     *
     * @parm projectDto is data transfer object which has project details with id
     */
    public String updateProject(ProjectDto projectDto) throws CustomException {
        Project project = projectMapper.fromDtoId(projectDto);
        return projectDao.updateProject(project);          
    }

    /**
     * <p>
     * This method is used to get all Project details
     * </p>
     *
     */
    public List<ProjectDto> getProjectsDetails() throws CustomException {
        List<Project> projects = projectDao.retrieveProjects();
        List<ProjectDto> projectDtos = new ArrayList<ProjectDto>();
        for (Project project: projects) {
            ProjectDto projectDto = projectMapper.toDto(project);
            projectDtos.add(projectDto);
        }  
        return projectDtos;         
    }

    /**
     * <p>
     * This method is used to get Project details by id
     * </p>
     *
     * @parm projectId is id of the project
     */
    public ProjectDto getProjectsDetailsById(int projectId) throws CustomException {
        Project project = projectDao.retrieveProjectById(projectId);
        ProjectDto projectDto = projectMapper.toDto(project);
        return projectDto;         
    }

    /**
     * <p>
     * This method is used to get project to view assigned Employees
     * </p>
     *
     * @parm projectId is id of the project
     */
    public Project getProjectToViewAssignedEmployees(int projectId) throws CustomException {
        return projectDao.retrieveProjectById(projectId);        
    }

     /**
     * <p>
     * This method is used to get Project details by ID
     * </p>
     *
     * @parm projectId is id of the project
     */
    public boolean isProjectAvailable(int projectId) throws CustomException {
        Project project = projectDao.retrieveProjectById(projectId);
        if (project != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * <p>
     * This method is used to delete employees assigned to project by id
     * </p>
     *
     * @parm projectId is id of the project
     */
    public String deleteEmployeesAssignedToProjectByProjectId(int projectId) throws CustomException {
        List<EmployeeProject> employeesAssignedToProject = new ArrayList<EmployeeProject>();
        Project project = projectDao.retrieveProjectById(projectId);
        project.setEmployeesAssignedToProject(employeesAssignedToProject);
        return projectDao.deleteEmployeesAssignedToProject(project);
    }

    /**
     * <p>
     * This method is used to delete project details by id
     * </p>
     *
     * @parm projectId is id of the project
     */
    public String deleteProject(int projectId) throws CustomException{
        Project project = projectDao.retrieveProjectById(projectId);
        project.setStatus(Constants.INACTIVE);  
        return projectDao.updateProject(project); 
    }  

    public int updateProjectDetail(String variable, String value, int projectId) throws CustomException {
        return 0;
    }
}