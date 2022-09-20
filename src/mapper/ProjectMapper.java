/*
 * Copyright (c) 2022, ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */
package com.ideas2it.mapper;

import com.ideas2it.dto.ProjectDto;
import com.ideas2it.model.Project;

/**
 * The {@code ProjectMapper} has methods to convert project dto to project model and vice versa.
 * 
 *
 * @author  Gunaseelan K
 * since 1.0
 * jls Advanced logics+
 */   
public class ProjectMapper {
    
    /**
     * <p>
     * This method is used to convert Project dto to Project model
     * </p>
     *
     */ 
    public Project fromDto(ProjectDto projectDto) {
        Project project = new Project(projectDto.getName(), projectDto.getClientName(), projectDto.getCompanyName(), projectDto.getStartDate(),
            projectDto.getEstimatedDuration(), projectDto.getDescription(), projectDto.getTechnologyUsed(), projectDto.getStatus());
        return project;
    }

    /**
     * <p>
     * This method is used to convert Project dto to Project model with Id
     * </p>
     *
     */ 
    public Project fromDtoId(ProjectDto projectDto) {
        Project project = new Project(projectDto.getId(), projectDto.getName(), projectDto.getClientName(), projectDto.getCompanyName(), projectDto.getStartDate(),
            projectDto.getEstimatedDuration(), projectDto.getDescription(), projectDto.getTechnologyUsed(), projectDto.getStatus());
        return project;
    }

    /**
     * <p>
     * This method is used to convert Project model to Project dto 
     * </p>
     *
     */ 
    public ProjectDto toDto(Project project) {
        ProjectDto projectDto = new ProjectDto(project.getId(), project.getName(), project.getClientName(), project.getCompanyName(), project.getStartDate(),
            project.getEstimatedDuration(), project.getDescription(), project.getTechnologyUsed(), project.getStatus());
        return projectDto;
    }
}