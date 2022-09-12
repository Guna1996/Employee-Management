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

public class BaseDao {
    static SessionFactory sessionFactory = null;

    public SessionFactory databaseConnection() throws CustomException {
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