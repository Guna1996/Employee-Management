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
    public boolean insertEmployee(Employee employee, List<Role> roles) throws CustomException{
        employee.setRole(roles);
        try {
            Session session = sessionFactory.openSession();  
            Transaction transaction = session.beginTransaction();     
            session.save(employee);  
            transaction.commit();        
            session.close();
            return true; 
        } catch(Exception exception) {
            exception.printStackTrace();
            throw new CustomException(exception.getMessage());
        }   
    } 

    public List<Employee> retrieveEmployeesByRoleName(Role role) throws CustomException{
        Session session = sessionFactory.openSession();

         Transaction transaction = null;  
        List<Employee> employeesByRole = new ArrayList<Employee>();
        List<Employee> employees = new ArrayList<Employee>();
        try {
            transaction = session.beginTransaction();
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
  
    public boolean updateEmployee(Employee employeeUpdated, int employeeId, String roleName) throws CustomException{
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();  
        try {  
            Employee employee = (Employee)session.get(Employee.class, employeeId); 
            employee.setFirstName(employeeUpdated.getFirstName());
            employee.setLastName(employeeUpdated.getLastName());            
            employee.setStatus(employeeUpdated.getStatus());
            employee.setDob(employeeUpdated.getDob());
            employee.setGender(employeeUpdated.getGender());
            employee.setDateOfJoining(employeeUpdated.getDateOfJoining());
            employee.setBatch(employeeUpdated.getBatch());
            employee.setDesignation(employeeUpdated.getDesignation());
            employee.setCity(employeeUpdated.getCity());
            employee.setFatherName(employeeUpdated.getFatherName());
            employee.setEmail(employeeUpdated.getEmail());
            employee.setPhoneNumber(employeeUpdated.getPhoneNumber());
            Role role = new Role();
            role.setName(roleName);
            (employeeUpdated.getRole()).add(role);
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
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();  
        try {  
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
   
    
    