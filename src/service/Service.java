/*
 * Copyright (c) 2022, ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */
package com.ideas2it.service;

import com.ideas2it.dao.EmployeeDao;
import com.ideas2it.model.Employee;
import com.ideas2it.dto.EmployeeDto;
import com.ideas2it.dao.RoleDao;
import com.ideas2it.mapper.EmployeeMapper;
import com.ideas2it.exception.MyCustomException;

/**
 * The {@code Service} class contains the method for trainees, trainers and Human Resource Services. access 
 * and controls the methods of the classes, such as {@code TraineeDao} {@code TrainerDao} {@code HumanResourceDao}, Accessed by  
 * creating an instance of that classes.
 * Implementation to perform Trainer and Trainee related Services
 *
 *
 * @author  Gunaseelan K
 * since 1.0
 * jls Advanced logics+
 */
public class Service {

    EmployeeDao employeeDao = new EmployeeDao();
    EmployeeMapper employeeMapper = new EmployeeMapper();
    RoleDao roleDao = new RoleDao();
   
    /**
     * <p>
     * This method is used to add Trainee details
     * </p>
     * 
     * @parm firstName is first name of the trainee
     * @parm lastName is last name of the trainee
     * @parm employeeId is employee id of the trainee
     * @parm dob is date of birth of the trainee
     * @parm gender is gender of the trainee
     * @parm traineeBatch is batch of trainee
     * @parm skills is skills of trainee
     * @parm fatherName is fathername of trainee
     * @parm email is email id of trainee
     * @parm phoneNumber is contact number of trainee
     */     
    public boolean addEmployee(EmployeeDto employeeDto, String role) throws MyCustomException{
        int employeeId;
        Employee employee = employeeMapper.employeeDtoTOEmployee(employeeDto);
        try {
            employeeId = employeeDao.insertEmployee(employee);
            return roleDao.assignEmployeeRole(employeeId, role);
        } catch(MyCustomException exception) {  
            throw new MyCustomException(exception.getMessage());
        }  
             
    }
 
}
