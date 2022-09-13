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
 * The {@code EmployeeProjectDao} class contains the method for performing CRUD operaion in database.
 *
 *
 * @author  Gunaseelan K
 * since 1.0
 * jls Advanced logics+
 */
public class EmployeeProjectDao { 
    /**
     * <p>
     * This method is used to assign project to employees
     * 
     */  
    public String assignProjectToEmployees(List<EmployeeProject> assignedEmployeesToProject) throws CustomException{
        SessionFactory sessionFactory = BaseDao.databaseConnection();
        Session session = null;
        try {
            session = sessionFactory.openSession();  
            Transaction transaction = session.beginTransaction();
            for (EmployeeProject employeeProject: assignedEmployeesToProject) {     
                session.save(employeeProject);  
            }
            transaction.commit();
            return "Assigned Successfully";     
        } catch(Exception exception) {
            exception.printStackTrace();
            throw new CustomException("Error occured while Inserting employee", exception);
        } finally {
            if (session != null) {
                session.close();  
            }    
        }
    } 

    public List<EmployeeProject> retrieveAssignedProjectsToEmployees() throws CustomException {
        SessionFactory sessionFactory = BaseDao.databaseConnection();
        Session session = null;  
        List<EmployeeProject> employeesProject = new ArrayList<EmployeeProject>();
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            employeesProject = session.createQuery("FROM EmployeeProject where status = :status").setString("status", "active").list();
            transaction.commit();
            return employeesProject;
        } catch (Exception exception) {
            exception.printStackTrace(); 
            throw new CustomException("Error occured while Retrieving all assigned employees to project" ,exception);
        } finally {
            if (session != null) {
                session.close();  
            }   
        }    
    }
}
   
    
    