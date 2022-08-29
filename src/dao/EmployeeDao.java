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
import java.sql.Date;
import java.time.LocalDate;

/**
* Implementation to Insert,update and Access Trainers 
**/
public class EmployeeDao extends BaseDao {
     Date date = new Date(0);
     Connection connection = mysqlConnection();
     /**
     * <p>
     * This method is used to insert Trainee details
     * </p>
     * 
     */  
    public int insertEmployee(Employee employee) throws MyCustomException{
        PreparedStatement preparedStatemt;
        try {
            String query = " insert into employee(first_name, last_name, dob, gender,"
                + "date_of_joining, batch, designation, city, father_name, email, phone_number, status) "
                + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'active')";
            preparedStatemt = connection.prepareStatement(query);
            preparedStatemt.setString(1, employee.getFirstName());
            preparedStatemt.setString(2, employee.getLastName());
            preparedStatemt.setDate(3, date.valueOf(employee.getDob()));
            preparedStatemt.setString(4, employee.getGender());
            preparedStatemt.setDate(5, date.valueOf(employee.getDateOfJoining()));
            preparedStatemt.setString(6, employee.getBatch());
            preparedStatemt.setString(7, employee.getDesignation());
            preparedStatemt.setString(8, employee.getCity());
            preparedStatemt.setString(9, employee.getFatherName());
            preparedStatemt.setString(10, employee.getEmail());
            preparedStatemt.setString(11, employee.getPhoneNumber());
            return (preparedStatemt.executeUpdate());
        } catch(Exception exception) {
            exception.printStackTrace();  
            throw new MyCustomException(exception.getMessage());
        }     
    }

    public int retrieveLastInsertedEmployeeId() throws MyCustomException{
        int employeeId = 0;
        PreparedStatement preparedStatemt;
        try {
            String query = "SELECT * FROM employee ORDER BY id DESC LIMIT 1";
            preparedStatemt = connection.prepareStatement(query);
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

    public int updateEmployee(Employee employee, String email, LocalDate dob) throws MyCustomException{
        PreparedStatement preparedStatemt;
        try {
            String query = "update employee set last_modified_date = current_timestamp, first_name = ?, last_name = ?, dob = ?, gender = ?,"
                + "date_of_joining = ?, batch = ?, designation = ?, city = ?, father_name = ?, email = ?, phone_number = ?"
                + "where email= ? and dob = ? ";
            preparedStatemt = connection.prepareStatement(query);
            preparedStatemt.setString(1, employee.getFirstName());
            preparedStatemt.setString(2, employee.getLastName());
            preparedStatemt.setDate(4, date.valueOf(employee.getDob()));
            preparedStatemt.setString(5, employee.getGender());
            preparedStatemt.setDate(6, date.valueOf(employee.getDateOfJoining()));
            preparedStatemt.setString(7, employee.getBatch());
            preparedStatemt.setString(8, employee.getDesignation());
            preparedStatemt.setString(9, employee.getCity());
            preparedStatemt.setString(10, employee.getFatherName());
            preparedStatemt.setString(11, employee.getEmail());
            preparedStatemt.setString(12, employee.getPhoneNumber());
            preparedStatemt.setString(13, email);
            preparedStatemt.setDate(14, date.valueOf(dob));
            return (preparedStatemt.executeUpdate());
        } catch(Exception exception) {
            exception.printStackTrace();  
            throw new MyCustomException(exception.getMessage());
        }     
    }
 
    public int updateEmployeeDetail(String fieldName, String value, String email, LocalDate dob) throws MyCustomException {
        PreparedStatement preparedStatemt;
        try {
            String query = "update employee set last_modified_date = current_timestamp, " + fieldName + " = ? where email= ? and dob = ? ";
            preparedStatemt = connection.prepareStatement(query);
            preparedStatemt.setString(1, value);
            preparedStatemt.setString(2, email);
            preparedStatemt.setDate(3, date.valueOf(dob));
            return (preparedStatemt.executeUpdate());
        } catch(Exception exception) {
            exception.printStackTrace();  
            throw new MyCustomException(exception.getMessage());
        }     
    }

    public List<Employee> retrieveEmployeesByRoleId(int roleId) throws MyCustomException{
       PreparedStatement preparedStatemt;
       List<Employee> employees = new ArrayList<Employee>();
       try {
            String sql = " select employee.* from employee, employee_roles where employee.id = employee_roles.employee_id"
                + " and employee.status = 'active' and employee_roles.role_id=" + roleId;
            connection = mysqlConnection();
            preparedStatemt = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatemt.executeQuery();
            while(resultSet.next()) {
                Employee employee = new Employee(resultSet.getInt("id"), resultSet.getString("first_name"), resultSet.getString("last_Name"),
                    resultSet.getDate("dob").toLocalDate(), resultSet.getString("gender"), resultSet.getDate("date_of_joining").toLocalDate(),
                    resultSet.getString("batch"), resultSet.getString("designation"), resultSet.getString("city"), resultSet.getString("father_name"),
                    resultSet.getString("email"), resultSet.getString("phone_number"), resultSet.getString("status"));
                employees.add(employee);
            }
            return employees;
        } catch(Exception exception) {
            exception.printStackTrace();  
            throw new MyCustomException(exception.getMessage());
        }     
    }

    public List<Employee> retrieveEmployees() throws MyCustomException{
       PreparedStatement preparedStatemt;
       List<Employee> employees = new ArrayList<Employee>();
       try {
            String sql = " select * from employee where delete_Status = 1 ";
            preparedStatemt = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatemt.executeQuery();
            while(resultSet.next()) {
                Employee employee = new Employee(resultSet.getInt("id"), resultSet.getString("first_name"), resultSet.getString("last_Name"),
                    resultSet.getDate("dob").toLocalDate(), resultSet.getString("gender"), resultSet.getDate("date_of_joining").toLocalDate(),
                    resultSet.getString("batch"), resultSet.getString("designation"), resultSet.getString("city"), resultSet.getString("father_name"),
                    resultSet.getString("email"), resultSet.getString("phone_number"), resultSet.getString("status"));
                employees.add(employee);
            }
            return employees;
        } catch(Exception exception) {
            exception.printStackTrace();  
            throw new MyCustomException(exception.getMessage());
        }     
    }

    public Employee retrieveEmployeeByEmailAndDob(String email, LocalDate dob, int roleId) throws MyCustomException{
       PreparedStatement preparedStatemt;
       try {
            Employee employee = new Employee();
            String sql = " select employee.* from employee, employee_roles where employee.id = employee_roles.employee_id"
                + " and employee.status = 'active' and employee.email = ? and employee.dob = ? and employee_roles.role_id=" + roleId;
            connection = mysqlConnection();
            preparedStatemt = connection.prepareStatement(sql);
            preparedStatemt.setString(1, email);
            preparedStatemt.setDate(2, date.valueOf(dob));
            ResultSet resultSet = preparedStatemt.executeQuery();
            while(resultSet.next()) {
                employee = new Employee(resultSet.getInt("id"), resultSet.getString("first_name"), resultSet.getString("last_Name"),
                    resultSet.getDate("dob").toLocalDate(), resultSet.getString("gender"), resultSet.getDate("date_of_joining").toLocalDate(),
                    resultSet.getString("batch"), resultSet.getString("designation"), resultSet.getString("city"), resultSet.getString("father_name"),
                    resultSet.getString("email"), resultSet.getString("phone_number"), resultSet.getString("status"));
            }
            return employee;
        } catch(Exception exception) {
            exception.printStackTrace();  
            throw new MyCustomException(exception.getMessage());
        }     
    }


    public int deleteEmployeeById(int employeeId) throws MyCustomException {
        PreparedStatement preparedStatemt;
        try {
            String query = "update employee set status = 'inactive' where id =" + employeeId;
            preparedStatemt = connection.prepareStatement(query);
            return preparedStatemt.executeUpdate();
        } catch(Exception exception) {
            exception.printStackTrace();  
            throw new MyCustomException(exception.getMessage());
        }             
    }
}
   
    
    