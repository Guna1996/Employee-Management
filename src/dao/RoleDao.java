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

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction; 

/**
 * The {@code RoleDao} class contains the method for performing CRUD operaion in database.
 *
 *
 * @author  Gunaseelan K
 * since 1.0
 * jls Advanced logics+
 */
public class RoleDao {

    /**
     * <p>
     * This method is used to get role object by role name
     * </p>
     *
     * @parm name is Role of the employee 
     */
    public Role retrieveRoleByName(String name) throws CustomException {
        Session session = null;
        try {
            SessionFactory sessionFactory = BaseDao.databaseConnection();
            session = sessionFactory.openSession(); 
            Transaction transaction = session.beginTransaction();
            List<Role> roles = session.createQuery("FROM Role where name = :name").setString("name", name).list();
            transaction.commit();
            return roles.get(0);
        } catch (Exception exception) {
            throw new CustomException("Error occured while retrieving role by name", exception);
        } finally {
            if (session != null) {
                session.close();  
            }    
        }
    }
}
   
    
    