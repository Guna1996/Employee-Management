/*
 * Copyright (c) 2022, ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */
package com.ideas2it.dao;

import com.ideas2it.model.Employee;
import com.ideas2it.model.Role;
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

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Session;  
import org.hibernate.cfg.Configuration;    
import org.hibernate.SessionFactory;    
import org.hibernate.Transaction;  
import org.hibernate.boot.Metadata;  
import org.hibernate.boot.MetadataSources;  
import org.hibernate.boot.registry.StandardServiceRegistry;  
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;  
  

/**
* Implementation to Insert,update and Access employee 
**/
public class EmployeeDao extends BaseDao {
     Date date = new Date(0);
     /**
     * <p>
     * This method is used to insert Trainee details
     * </p>
     * 
     */  
    public boolean insertEmployee(Employee employee) throws CustomException{
        Session session = null;
        try {
            session = sessionFactory.openSession();  
            Transaction transaction = session.beginTransaction();     
            session.save(employee);  
            transaction.commit();        
            return true; 
        } catch(Exception exception) {
            exception.printStackTrace();
            throw new CustomException(exception.getMessage());
        } finally {
            session.close();  
        }
    } 

    public List<Employee> retrieveEmployees() throws CustomException{
        Session session = null;  
        List<Employee> employees = new ArrayList<Employee>();
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            employees = session.createQuery("FROM Employee where status = 'active'").list();
            transaction.commit();
            return employees;
        } catch (Exception exception) {
            exception.printStackTrace(); 
            throw new CustomException(exception.getMessage());
        } finally {
            session.close();  
        }    
    }

    public Employee retrieveEmployeeById(int employeeId) throws CustomException{
        Session session = null;      
        try {  
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction(); 
            Employee employee = (Employee)session.get(Employee.class, employeeId);   
            transaction.commit();
            return employee;
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new CustomException(exception.getMessage());
        } finally {
            session.close();  
        }     
    }
  
    public boolean updateEmployee(Employee employee) throws CustomException{  
        Session session = null;   
        try {  
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.update(employee); 
            transaction.commit();   
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new CustomException(exception.getMessage());
        } finally {
            session.close();  
        }   
    }

    public int deleteEmployeeById(int employeeId) throws CustomException{
        Session session = null;   
        try {  
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction(); 
            Employee employee = (Employee)session.get(Employee.class, employeeId);   
            employee.setStatus("inactive");
            session.update(employee); 
            transaction.commit(); 
            return 1;
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new CustomException(exception.getMessage());
        } finally {
            session.close();  
        }    
    }
}
   
    
    