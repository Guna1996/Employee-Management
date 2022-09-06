/*
 * Copyright (c) 2022, ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */
package com.ideas2it.dao;

import com.ideas2it.model.Employee;
import com.ideas2it.model.EmployeeProject;
import com.ideas2it.utils.Constant;
import com.ideas2it.dao.BaseDao;
import com.ideas2it.exception.CustomException;

import java.sql.Connection;
import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.time.LocalDate;

/**
* Implementation to Insert,update and Access Trainers 
**/
public class EmployeeProjectDao extends BaseDao {
     Date date = new Date(0);
     Connection connection = mysqlConnection();
     /**
     * <p>
     * This method is used to insert Trainee details
     * </p>
     * 
     */  
    public int assignProjectsToEmployees(int projectId, List<EmployeeProject> assignedEmployeeProjects) throws CustomException{
        PreparedStatement preparedStatemt;
        int isExecuted = 0;
        
        
        try {
            for(EmployeeProject employeeProject: assignedEmployeeProjects) {
                String query = " insert into employee_projects(employee_id, project_id, assigned_date, assigned_by, employee_role, status, relieved_date) "
                    + " values (?, ?, ?, ?, ?, ?, ?)";
                preparedStatemt = connection.prepareStatement(query);
                preparedStatemt.setInt(1, employeeProject.getEmployeeId());
                preparedStatemt.setInt(2, projectId);
                preparedStatemt.setDate(3, date.valueOf(employeeProject.getAssignedDate()));
                preparedStatemt.setString(4, employeeProject.getAssignedBy());
                preparedStatemt.setString(5, employeeProject.getEmployeeRole());
                preparedStatemt.setString(6, employeeProject.getStatus());
                preparedStatemt.setDate(7, date.valueOf(employeeProject.getRelievedDate()));
                isExecuted = preparedStatemt.executeUpdate();
            }
            return isExecuted;
        } catch(Exception exception) {
            exception.printStackTrace();  
            throw new CustomException(exception.getMessage());
        }     
    }

    public List<EmployeeProject> retrieveAssignedProjectsToEmployees() throws CustomException{
       PreparedStatement preparedStatemt;
       List<EmployeeProject> assignedEmployeesProjects = new ArrayList<EmployeeProject>();
       try {
            String sql = " select * from employee_projects where status = 'active' ";
            preparedStatemt = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatemt.executeQuery();
            while(resultSet.next()) {
                EmployeeProject employeeProject = new EmployeeProject();
                employeeProject.setId(resultSet.getInt("id"));
                employeeProject.setEmployeeId(resultSet.getInt("employee_id"));
                employeeProject.setProjectId(resultSet.getInt("project_id"));
                employeeProject.setAssignedDate(resultSet.getDate("assigned_date").toLocalDate());
                employeeProject.setAssignedBy(resultSet.getString("assigned_by"));
                employeeProject.setEmployeeRole(resultSet.getString("employee_role"));
                employeeProject.setStatus(resultSet.getString("status"));
                employeeProject.setRelievedDate(resultSet.getDate("relieved_date").toLocalDate());
                assignedEmployeesProjects.add(employeeProject);
            }
            return assignedEmployeesProjects;
        } catch(Exception exception) {
            exception.printStackTrace();  
            throw new CustomException(exception.getMessage());
        }     
    }

    public int deleteAssignedEmployeeToProjectById(int employeeId, int projectId) throws CustomException {
        PreparedStatement preparedStatemt;
        try {
            String query = "update employee_projects set status = 'inactive' where employee_id = ? and project_id = ?";
            preparedStatemt = connection.prepareStatement(query);
            preparedStatemt.setInt(1, employeeId);
            preparedStatemt.setInt(2, projectId);
            return preparedStatemt.executeUpdate();
        } catch(Exception exception) {
            exception.printStackTrace();  
            throw new CustomException(exception.getMessage());
        }             
    }
}
   
    
    