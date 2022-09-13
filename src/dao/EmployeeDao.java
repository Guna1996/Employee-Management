/*
 * Copyright (c) 2022, ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */
package com.ideas2it.dao;

import com.ideas2it.dao.BaseDao;
import com.ideas2it.exception.CustomException;
import com.ideas2it.model.Employee;
import com.ideas2it.model.Role;
import com.ideas2it.utils.Constant;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.boot.Metadata;  
import org.hibernate.boot.MetadataSources;  
import org.hibernate.boot.registry.StandardServiceRegistry;  
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;    
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Session;  
import org.hibernate.SessionFactory;    
import org.hibernate.Transaction;  
  
/**
 * The {@code EmployeeDao} class contains the method for performing CRUD operaion in database.
 *
 *
 * @author  Gunaseelan K
 * since 1.0
 * jls Advanced logics+
 */
public class EmployeeDao {

     /**
     * <p>
     * This method is used to insert Employee details into database
     * </p>
     * 
     * @parm employee is a model which has employee details
     */  
    public int insertEmployee(Employee employee) throws CustomException {
        SessionFactory sessionFactory = BaseDao.databaseConnection();
        int employeeId = 0;
        Session session = null;
        try {
            session = sessionFactory.openSession();  
            Transaction transaction = session.beginTransaction();     
            employeeId = (Integer)session.save(employee);  
            transaction.commit();
            return employeeId;        
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new CustomException("Error occured while Inserting employee", exception);
        } finally {
            if (session != null) {
                session.close();  
            }    
        }
    } 

    /**
     * <p>
     * This method is used to retrieve all Employee details from database
     * </p>
     *
     */
    public List<Employee> retrieveEmployees() throws CustomException {
        SessionFactory sessionFactory = BaseDao.databaseConnection();
        Session session = null;  
        List<Employee> employees = new ArrayList<Employee>();
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            employees = session.createQuery("FROM Employee where status = :status").setString("status", "active").list();
            transaction.commit();
            return employees;
        } catch (Exception exception) {
            exception.printStackTrace(); 
            throw new CustomException("Error occured while Retrieving all employees" ,exception);
        } finally {
            if (session != null) {
                session.close();  
            }   
        }    
    }

    /**
     * <p>
     * This method is used to retrieve Employee details from database by id
     * </p>
     *
     * @parm employeeId is id of the employee 
     */ 
    public Employee retrieveEmployeeById(int employeeId) throws CustomException {
        SessionFactory sessionFactory = BaseDao.databaseConnection();
        Session session = null;      
        try {  
            String status = "active";
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction(); 
            Employee employee = (Employee) session.createQuery("FROM Employee where status = :status and id = :id").setString("status", "active").setInteger("id", employeeId).uniqueResult();   
            transaction.commit();
            return employee;
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new CustomException("Error occured while Retrieving employee by Id", exception);
        } finally {
            if (session != null) {
                session.close();  
            }     
        }     
    }

    /**
     * <p>
     * This method is used to update Employee details in database
     * </p>
     *
     * @parm employee is a model which has employee details
     */ 
    public String updateEmployee(Employee employee) throws CustomException {
        SessionFactory sessionFactory = BaseDao.databaseConnection();  
        Session session = null;   
        try {  
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.update(employee); 
            transaction.commit();   
            return "Updated Successfully";
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new CustomException("Error occured while Updating employee", exception);
        } finally {
            if (session != null) {
                session.close();  
            }     
        }   
    }
}
   
    
    