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
 * The {@code } class contains the method for trainees, trainers and Human Resource Services. access 
 * and controls the methods of the classes, such as {@code TraineeDao} {@code } {@code}, Accessed by  
 * creating an instance of that classes.
 * Implementation to perform Trainer and Trainee related Services
 *
 *
 * @author  Gunaseelan K
 * since 1.0
 * jls Advanced logics+
 */
public class ProjectService { 

    ProjectDao projectDao = new ProjectDao();
    ProjectMapper projectMapper = new ProjectMapper();

    public int addProject(ProjectDto projectDto) throws CustomException {
        Project project = projectMapper.fromDto(projectDto);
        return projectDao.insertProject(project);          
    }
 
    public String updateProject(ProjectDto projectDto) throws CustomException {
        Project project = projectMapper.fromDtoId(projectDto);
        return projectDao.updateProject(project);          
    }

    public List<ProjectDto> getProjectsDetails() throws CustomException {
        List<Project> projects = projectDao.retrieveProjects();
        List<ProjectDto> projectDtos = new ArrayList<ProjectDto>();
        for (Project project: projects) {
            ProjectDto projectDto = projectMapper.toDto(project);
            projectDtos.add(projectDto);
        }  
        return projectDtos;         
    }

    public ProjectDto getProjectsDetailsById(int projectId) throws CustomException {
        Project project = projectDao.retrieveProjectById(projectId);
        ProjectDto projectDto = projectMapper.toDto(project);
        return projectDto;         
    }

    public Project getProjectToViewAssignedEmployees(int projectId) throws CustomException {
        return projectDao.retrieveProjectById(projectId);        
    }

    public boolean isProjectAvailable(int projectId) throws CustomException {
        Project project = projectDao.retrieveProjectById(projectId);
        if (project != null) {
            return true;
        } else {
            return false;
        }
    }

    public String deleteEmployeesAssignedToProjectByProjectId(int projectId) throws CustomException {
        List<EmployeeProject> employeesAssignedToProject = new ArrayList<EmployeeProject>();
        Project project = projectDao.retrieveProjectById(projectId);
        project.setEmployeesAssignedToProject(employeesAssignedToProject);
        return projectDao.deleteEmployeesAssignedToProject(project);
    }

    public String deleteProject(int projectId) throws CustomException{
        Project project = projectDao.retrieveProjectById(projectId);
        project.setStatus(Constants.INACTIVE);  
        return projectDao.deleteProject(project); 
    }  

    public int updateProjectDetail(String variable, String value, int projectId) throws CustomException {
        return 0;
    }
}