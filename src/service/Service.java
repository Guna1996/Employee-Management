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

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

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
    public int addEmployee(EmployeeDto employeeDto, String roleName) throws MyCustomException{
        int employeeId;
        int roleId;
        Employee employee = employeeMapper.fromDto(employeeDto);
        if (employeeDao.insertEmployee(employee) == 1) {
            employeeId = employeeDao.retrieveLastInsertedEmployeeId();
            roleId = roleDao.retrieveRoleIdByName(roleName);
            return roleDao.assignEmployeeRole(employeeId, roleId);
        } else {
            return 0;
        }            
    }

    public int updateEmployee(EmployeeDto employeeDto, String email, LocalDate dob) throws MyCustomException{
        Employee employee = employeeMapper.fromDto(employeeDto);
        return employeeDao.updateEmployee(employee, email, dob);          
    }    
 
    public int updateEmployeeDetail(String variable, String value, String email, LocalDate dob) throws MyCustomException{
        return employeeDao.updateEmployeeDetail(variable, value, email, dob);
    }

    public int deleteEmployeeById(int employeeId) throws MyCustomException{
        return employeeDao.deleteEmployeeById(employeeId);          
    }   

    public boolean checkIsEmployeeAvailable(String email, LocalDate dob, String roleName) throws MyCustomException{
        int roleId = roleDao.retrieveRoleIdByName(roleName);
        for (Employee employee: employeeDao.retrieveEmployeesByRoleId(roleId)) {
            if (employee.getEmail().equals(email) && employee.getDob().equals(dob)) {
                return true;
            }       
        }
        return false;
    }

    public List<EmployeeDto> getEmployeesDetails(String employeeRole) throws MyCustomException{
        int roleId = roleDao.retrieveRoleIdByName(employeeRole);
        List<Employee> employees = employeeDao.retrieveEmployeesByRoleId(roleId);
        List<EmployeeDto> employeeDtos = new ArrayList<EmployeeDto>();
        for (Employee employee: employees) {
            EmployeeDto employeeDto = employeeMapper.toDto(employee);
            employeeDtos.add(employeeDto);
        }  
        return employeeDtos;         
    }

    public EmployeeDto getEmployeeDetails(String email, LocalDate dob, String employeeRole) throws MyCustomException{
        int roleId = roleDao.retrieveRoleIdByName(employeeRole);
        Employee employee = employeeDao.retrieveEmployeeByEmailAndDob(email, dob, roleId);
        return employeeMapper.toDto(employee);  
    }
    
    
}
