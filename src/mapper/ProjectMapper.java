/*
 * Copyright (c) 2022, ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */
package com.ideas2it.mapper;

import com.ideas2it.model.Project;
import com.ideas2it.dto.ProjectDto;
 
public class ProjectMapper {
    
    public Project fromDto(ProjectDto projectDto) {
        Project project = new Project(projectDto.getName(), projectDto.getClientName(), projectDto.getCompanyName(), projectDto.getStartDate(),
            projectDto.getEstimatedDuration(), projectDto.getDescription(), projectDto.getTechnologyUsed(), projectDto.getStatus());
        return project;
    }

    public ProjectDto toDto(Project project) {
        ProjectDto projectDto = new ProjectDto(project.getId(),project.getName(), project.getClientName(), project.getCompanyName(), project.getStartDate(),
            project.getEstimatedDuration(), project.getDescription(), project.getTechnologyUsed(), project.getStatus());
        return projectDto;
    }
}