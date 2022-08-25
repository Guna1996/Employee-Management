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
import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
* Implementation to Insert,update and Access Trainers 
**/
public class EmployeeDao extends DatabaseConnection {
     Connection connection;
     /**
     * <p>
     * This method is used to insert Trainee details
     * </p>
     * 
     */  
    public boolean insertEmployee(Employee employee) throws MyCustomException{
        PreparedStatement preparedStatemt;
        try {
            String query = " insert into employee(first_name, last_name, staff_number, dob, gender,dateOfJoining, batch, designation, city, fatherName, email, phoneNumber) "
                + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            connection = mysqlConnection();
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
            System.out.println(preparedStatemt.execute());
            return preparedStatemt.execute();

        } catch(Exception exception) {
            exception.printStackTrace();  
            throw new MyCustomException(exception.getMessage());
        }     
    }

    public int retrieveLastInsertedEmployeeId() throws MyCustomException{
        int employeeId = 0;
        PreparedStatement preparedStatemt;
        try {
            String sql = "SELECT * FROM employee ORDER BY id DESC LIMIT 1";
            connection = mysqlConnection();
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

    public boolean updateEmployee(Employee employee, String email, String dob) throws MyCustomException{
        PreparedStatement preparedStatemt;
        try {
            String query = "update employee set first_name = ?, last_name = ?, staff_number = ?, dob = ?, gender = ?,"
                + "dateOfJoining = ?, batch = ?, designation = ?, city = ?, fatherName = ?, email = ?, phoneNumber = ?"
                + "where email=? and dob = ? ";
            connection = mysqlConnection();
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
            preparedStatemt.setString (13, email);
            preparedStatemt.setString (14, dob);
            System.out.println(preparedStatemt.execute());
            return preparedStatemt.execute();
        } catch(Exception exception) {
            exception.printStackTrace();  
            throw new MyCustomException(exception.getMessage());
        }     
    }
 
    public List<Employee> retrieveEmployeesByRoleId(int roleId) throws MyCustomException{
       PreparedStatement preparedStatemt;
       List<Employee> employees = new ArrayList<Employee>();
       try {
            String sql = " select employee.first_name, employee.last_name, employee.staff_number, employee.dob, employee.gender," 
                + " employee.dateOfJoining, employee.batch, employee.designation, employee.city, employee.fatherName, employee.email,"
                + " employee.phoneNumber  from employee, employee_roles where employee.id = employee_roles.employee_id and employee_roles.role_id=" + roleId;
            connection = mysqlConnection();
            preparedStatemt = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatemt.executeQuery();
            while(resultSet.next()) {
                Employee employee = new Employee(resultSet.getString("first_name"), resultSet.getString("last_Name"),
                    resultSet.getString("staff_number"), resultSet.getString("dob"), resultSet.getString("gender"), resultSet.getString("dateOfJoining"),
                    resultSet.getString("batch"), resultSet.getString("designation"), resultSet.getString("city"), resultSet.getString("fatherName"),
                    resultSet.getString("email"), resultSet.getString("phoneNumber"));
                employees.add(employee);
            }
            return employees;
        } catch(Exception exception) {
            exception.printStackTrace();  
            throw new MyCustomException(exception.getMessage());
        }     
    }
}
   
    
    