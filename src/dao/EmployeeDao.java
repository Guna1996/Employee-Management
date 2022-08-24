/*
 * Copyright (c) 2022, ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */
package com.ideas2it.dao;

import com.ideas2it.model.Employee;
import com.ideas2it.utils.Constant;
import com.ideas2it.dao.BaseDao;
import com.ideas2it.exception.MyCustomException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
* Implementation to Insert,update and Access Trainers 
**/
public class EmployeeDao extends DatabaseConnection {
     int employeeId = 0;
     PreparedStatement preparedStatemt;
     /**
     * <p>
     * This method is used to insert Trainee details
     * </p>
     * 
     */  
    public int insertEmployee(Employee employee) throws MyCustomException{
        try {
            Connection connection = mysqlConnection();
            String query = " insert into employee(first_name, last_name, staff_number, dob, gender,dateOfJoining, batch, designation, city, fatherName, email, phoneNumber) "
                + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatemt = connection.prepareStatement(query);
            preparedStatemt.setString (1, employee.getFirstName());
            preparedStatemt.setString (2, employee.getLastName());
            preparedStatemt.setString (3, employee.getStaffNumber());
            preparedStatemt.setString (4, employee.getDob());
            preparedStatemt.setString (5, employee.getGender());
            preparedStatemt.setString (6, employee.getDateOfJoining());
            preparedStatemt.setString (7, employee.getBatch());
            preparedStatemt.setString (8, employee.getDesignation());
            preparedStatemt.setString (9, employee.getCity());
            preparedStatemt.setString (10, employee.getFatherName());
            preparedStatemt.setString (11, employee.getEmail());
            preparedStatemt.setString (12, employee.getPhoneNumber());
            preparedStatemt.execute();
            String sql = "SELECT * FROM employee ORDER BY id DESC LIMIT 1";
            preparedStatemt = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatemt.executeQuery();
            while(resultSet.next()) {
            employeeId = resultSet.getInt("id");
            } 
            return employeeId;
        } catch(Exception exception) {
            exception.printStackTrace();  
            throw new MyCustomException(exception.getMessage());
        }                              
    } 
}
   
    
    