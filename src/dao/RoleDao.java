/*
 * Copyright (c) 2022, ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */
package com.ideas2it.dao;

import com.ideas2it.dao.BaseDao;
import com.ideas2it.model.Role;
import com.ideas2it.model.Employee;
import com.ideas2it.exception.CustomException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import org.hibernate.Transaction; 

public class RoleDao extends BaseDao {

    public Role retrieveRoleByName(String name) throws CustomException{
        try {
            Session session = sessionFactory.openSession(); 
            Criteria criteria = session.createCriteria(Role.class);
            criteria.add(Restrictions.eq("name", name));
            List<Role> roles = criteria.list();
            return roles.get(0);
        } catch(Exception exception) {
            exception.printStackTrace();  
            throw new CustomException(exception.getMessage());
        }     
    }
 
    public List<Employee> retrieveEmployeesByRoleName(String roleName) throws CustomException{
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
}
   
    
    