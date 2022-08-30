/*
 * Copyright (c) 2022, ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */
package com.ideas2it.dao;

import com.ideas2it.model.Project;
import com.ideas2it.utils.Constant;
import com.ideas2it.dao.BaseDao;
import com.ideas2it.exception.MyCustomException;

import java.sql.Connection;
import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.time.LocalDate;

/**
* 
**/
public class ProjectDao extends BaseDao {
     Date date = new Date(0);
     Connection connection = mysqlConnection();
     /**
     * <p>
     * 
     * </p>
     * 
     */  
    public int insertProject(Project project) throws MyCustomException{
        PreparedStatement preparedStatemt;
        try {
            String query = " insert into project(name, client_name, company_name, start_date,"
                + "estimated_duration, description, technology_used, status) "
                + " values (?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatemt = connection.prepareStatement(query);
            preparedStatemt.setString(1, project.getName());
            preparedStatemt.setString(2, project.getClientName());
            preparedStatemt.setString(3, project.getCompanyName());
            preparedStatemt.setDate(4, date.valueOf(project.getStartDate()));
            preparedStatemt.setString(5, project.getEstimatedDuration());
            preparedStatemt.setString(6, project.getDescription());
            preparedStatemt.setString(7, project.getTechnologyUsed());
            preparedStatemt.setString(8, project.getStatus());
            return (preparedStatemt.executeUpdate());
        } catch(Exception exception) {
            exception.printStackTrace();  
            throw new MyCustomException(exception.getMessage());
        }     
    }

    public int updateProject(Project project, int projectId) throws MyCustomException{
        PreparedStatement preparedStatemt;
        try {
            String query = "update project set last_modified_date = current_timestamp, name = ?, client_name = ?, company_name = ?, start_date = ?,"
                + "estimated_duration = ?, description = ?, technology_used = ?, status = ?"
                + "where id = ? ";
            preparedStatemt = connection.prepareStatement(query);
            preparedStatemt.setString(1, project.getName());
            preparedStatemt.setString(2, project.getClientName());
            preparedStatemt.setString(3, project.getCompanyName());
            preparedStatemt.setDate(4, date.valueOf(project.getStartDate()));
            preparedStatemt.setString(5, project.getEstimatedDuration());
            preparedStatemt.setString(6, project.getDescription());
            preparedStatemt.setString(7, project.getTechnologyUsed());
            preparedStatemt.setString(8, project.getStatus());
            preparedStatemt.setInt(9, projectId);
            return (preparedStatemt.executeUpdate());
        } catch(Exception exception) {
            exception.printStackTrace();  
            throw new MyCustomException(exception.getMessage());
        }     
    } 

    public List<Project> retrieveProjects() throws MyCustomException{
       PreparedStatement preparedStatemt;
       List<Project> projects = new ArrayList<Project>();
       try {
            String sql = "select * from project where status = 'active'";
            preparedStatemt = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatemt.executeQuery();
            while(resultSet.next()) {
                Project project = new Project(resultSet.getInt("id"), resultSet.getString("name"),
                    resultSet.getString("client_name"),resultSet.getString("company_name"),
                    resultSet.getDate("start_date").toLocalDate(), resultSet.getString("estimated_duration"),
                    resultSet.getString("description"), resultSet.getString("technology_used"), resultSet.getString("status"));
                projects.add(project);
            }
            return projects;
        } catch(Exception exception) {
            exception.printStackTrace();  
            throw new MyCustomException(exception.getMessage());
        }     
    } 

     public int updateProjectDetail(String fieldName, String value, int projectId) throws MyCustomException {
        PreparedStatement preparedStatemt;
        try {
            String query = "update project set modified_date = current_timestamp, " + fieldName + " = ? where id = ? ";
            preparedStatemt = connection.prepareStatement(query);
            preparedStatemt.setString(1, value);
            preparedStatemt.setInt(2, projectId);
            return (preparedStatemt.executeUpdate());
        } catch(Exception exception) {
            exception.printStackTrace();  
            throw new MyCustomException(exception.getMessage());
        }     
    } 
}
