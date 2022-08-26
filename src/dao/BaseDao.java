/*
 * Copyright (c) 2022, ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */
package com.ideas2it.dao;

import com.ideas2it.utils.Constant;
import com.ideas2it.exception.MyCustomException;

import java.sql.Connection;
import java.sql.DriverManager;

public class BaseDao {
    public Connection mysqlConnection() {
        try {
            Connection connection = DriverManager.getConnection(Constant.DATABASE_URL, Constant.DATABASE_USER_NAME, Constant.DATABASE_PASSWORD);
            return connection;
        } catch(Exception exception) {
            exception.printStackTrace();  
            return null;
        }     
    }
}