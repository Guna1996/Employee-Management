/*
 * Copyright (c) 2022, ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */
package com.ideas2it.dao;

import com.ideas2it.utils.Constant;
import com.ideas2it.exception.CustomException;

import org.hibernate.SessionFactory; 
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;

public class BaseDao {
    static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public SessionFactory databaseConnection() throws CustomException{
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