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

import java.sql.*;


/**
* Implementation to Insert,update and Access Trainers 
**/
public class EmployeeDao {
   
     /**
     * <p>
     * This method is used to insert Trainee details
     * </p>
     * 
     */  
    public boolean insertEmployee(Employee employee) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(Constant.DATABASE_URL,Constant.DATABASE_USER_NAME,Constant.DATABASE_PASSWORD);  //test1 is dbname,   root is dbusername and dbpassword
            Statement statement = connection.createStatement();
            // statement.execute("create table employee( first_name varchar(30), last_name varchar(30), employee_id varchar(30), dob varchar(30), gender varchar(30),dateOfJoining varchar(30), batch varchar(30),"
             //   +"designation varchar(30), fatherName varchar(30), email varchar(30), phoneNumber varchar(30))");
            String query = " insert into employee(first_name, last_name, employee_id, dob, gender,dateOfJoining, batch, designation, fatherName, email, phoneNumber) "
                + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, employee.getFirstName());
            preparedStmt.setString (2, employee.getLastName());
            preparedStmt.setString (3, employee.getEmployeeId());
            preparedStmt.setString (4, employee.getDob());
            preparedStmt.setString (5, employee.getGender());
            preparedStmt.setString (6, employee.getDateOfJoining());
            preparedStmt.setString (7, employee.getBatch());
            preparedStmt.setString (8, employee.getDesignation());
            preparedStmt.setString (9, employee.getFatherName());
            preparedStmt.setString (10, employee.getEmail());
            preparedStmt.setString (11, employee.getPhoneNumber());
            preparedStmt.execute();
            System.out.println("Table inserted");
            return true;
                
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
 
}
   
    
    