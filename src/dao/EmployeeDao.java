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
import com.ideas2it.exception.MyCustomException;

import java.sql.Connection;
import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.time.LocalDate;

import org.hibernate.Session;  
import org.hibernate.cfg.Configuration;    
import org.hibernate.SessionFactory;    
import org.hibernate.Transaction;  
import org.hibernate.boot.Metadata;  
import org.hibernate.boot.MetadataSources;  
import org.hibernate.boot.registry.StandardServiceRegistry;  
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;  
  

/**
* Implementation to Insert,update and Access Trainers 
**/
public class EmployeeDao extends BaseDao {
     Date date = new Date(0);
    static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
     /**
     * <p>
     * This method is used to insert Trainee details
     * </p>
     * 
     */  
    public int insertEmployee(Employee employee, String roleName) throws MyCustomException{
        Role role = new Role();
        role.setName(roleName);
        (employee.getRole()).add(role);
        try {
            Session session = sessionFactory.openSession();  
            Transaction transaction = session.beginTransaction();     
            session.save(employee);  
            transaction.commit();  
            System.out.println("successfully saved");    
            sessionFactory.close();  
            session.close();
            return 1; 
        } catch(Exception exception) {
            exception.printStackTrace();
            throw new MyCustomException(exception.getMessage());
        }   
    } 

    public List<Employee> retrieveEmployeesByRoleId(int roleId) throws MyCustomException{
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<Employee> employees = new ArrayList<Employee>();
        try {
            transaction = session.beginTransaction();
            employees = session.createQuery("FROM Employee").list();
            for (Employee employee: employees) { 
                List<Role> roles = employee.getRole();
                for(Role role: roles) {
                    System.out.println(role.getName());
                }
            }
            transaction.commit();
            return employees;
        } catch (Exception exception) {
            exception.printStackTrace(); 
            throw new MyCustomException(exception.getMessage());
        } finally {
            session.close(); 
        }       
    }
}
   
    
    