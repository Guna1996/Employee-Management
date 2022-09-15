/*
 * Copyright (c) 2022, ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */
package com.ideas2it.dao;


import com.ideas2it.exception.CustomException;
import com.ideas2it.utils.Constant;

import java.sql.Connection;
import java.sql.DriverManager;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory; 

/**
 * The {@code BaseDao} class contains the method for database connection .
 *
 *
 * @author  Gunaseelan K
 * since 1.0
 * jls Advanced logics+
 */
public class BaseDao {
    private static SessionFactory sessionFactory = null;

    /**
     * <p>
     * This method is empty constructor. its declared as private to avoid creating object 
     * </p>
     * 
     */
    private BaseDao() {
    }
    
    /**
     * <p>
     * This method performs hibernate configuration. It creates table as well as perform database conncetion
     * </p>
     * 
     */
    public static SessionFactory databaseConnection() throws CustomException {
        try {
            if (sessionFactory == null) {
                sessionFactory = new Configuration().configure().buildSessionFactory();
            }
        } catch (Exception exception) {
            exception.printStackTrace(); 
            throw new CustomException("Error occured while Cofiguring database" ,exception);
        } 
        return sessionFactory;
    }
}