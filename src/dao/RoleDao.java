/*
 * Copyright (c) 2022, ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */
package com.ideas2it.dao;

import com.ideas2it.dao.BaseDao;
import com.ideas2it.exception.MyCustomException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class RoleDao extends DatabaseConnection {
    int roleId =0 ;
    PreparedStatement preparedStatemt;
     
    public boolean assignEmployeeRole(int employeeId, String roleType) throws MyCustomException{
        try {
            Connection connection = mysqlConnection();
            String sql = "SELECT id FROM role where name = '"+ roleType +"'";
            preparedStatemt = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatemt.executeQuery();
            while(resultSet.next()) {
            roleId = resultSet.getInt("id");
            }
            String query = " insert into employee_roles(employee_id, role_id) values (?, ?)";
            preparedStatemt = connection.prepareStatement(query);
            preparedStatemt.setInt (1, employeeId);
            preparedStatemt.setInt (2, roleId);
            return preparedStatemt.execute();
        } catch(Exception exception) {
            exception.printStackTrace();  
            throw new MyCustomException(exception.getMessage());
        }                              
    } 
}
   
    
    