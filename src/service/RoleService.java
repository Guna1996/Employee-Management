/*
 * Copyright (c) 2022, ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */
package com.ideas2it.service;

import com.ideas2it.dao.RoleDao;
import com.ideas2it.exception.CustomException;
import com.ideas2it.model.Role;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code RoleService} class contains the methods to perform services for Employee Role. access 
 * and controls the methods of the classes, such as {@code RoleDao} , Accessed by  
 * creating an instance of that classes.
 *
 *
 * @author  Gunaseelan K
 * since 1.0
 * jls Advanced logics+
 */
public class RoleService {

    RoleDao roleDao = new RoleDao();
    
    /**
     * <p>
     * This method is used to get role object by role name
     * </p>
     * 
     * @parm name is role name of employee
     *
     */
    public Role getRoleByName(String name) throws CustomException {
        return roleDao.retrieveRoleByName(name);
    }
}
