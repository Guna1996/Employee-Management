/*
 * Copyright (c) 2022, ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */
package com.ideas2it.service;


import com.ideas2it.dao.EmployeeDao;
import com.ideas2it.dao.EmployeeProjectDao;
import com.ideas2it.dao.ProjectDao;
import com.ideas2it.dao.RoleDao;
import com.ideas2it.dto.EmployeeDto;
import com.ideas2it.dto.EmployeeProjectDto;
import com.ideas2it.dto.ProjectDto;
import com.ideas2it.exception.CustomException;
import com.ideas2it.mapper.EmployeeMapper;
import com.ideas2it.mapper.EmployeeProjectMapper;
import com.ideas2it.mapper.ProjectMapper;
import com.ideas2it.model.Employee;
import com.ideas2it.model.EmployeeProject;
import com.ideas2it.model.Project;
import com.ideas2it.model.Role;
import com.ideas2it.utils.Constants;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
public class EmployeeService {

    EmployeeDao employeeDao = new EmployeeDao();
    EmployeeProjectDao employeeProjectDao = new EmployeeProjectDao();
    EmployeeMapper employeeMapper = new EmployeeMapper();
    EmployeeProjectMapper employeeProjectMapper = new EmployeeProjectMapper();
    RoleDao roleDao = new RoleDao();
    
    public int addEmployee(EmployeeDto employeeDto, String roleName) throws CustomException{
        List<Role> roles = new ArrayList<Role>();
        Employee employee = employeeMapper.fromDto(employeeDto);
        Role role = roleDao.retrieveRoleByName(roleName);
        roles.add(role);
        employee.setRole(roles);
        return employeeDao.insertEmployee(employee); 
    }
   
    public List<EmployeeDto> getEmployeesDetails() throws CustomException{
        List<Employee> employees = employeeDao.retrieveEmployees();
        List<EmployeeDto> employeeDtos = new ArrayList<EmployeeDto>();
        for (Employee employee: employees) {
            employee.setRoleName(employee.getRole().get(0).getName());
            EmployeeDto employeeDto = employeeMapper.toDto(employee);
            employeeDtos.add(employeeDto);
        }  
        return employeeDtos;         
    }

    public List<EmployeeDto> getEmployeesDetailsByRoleName(String roleName) throws CustomException{
        Role role = roleDao.retrieveRoleByName(roleName);
        List<Employee> employees = role.getEmployee();
        List<EmployeeDto> employeeDtos = new ArrayList<EmployeeDto>();
        for (Employee employee: employees) {
            employee.setRoleName(role.getName()); 
            EmployeeDto employeeDto = employeeMapper.toDto(employee);
            employeeDtos.add(employeeDto);
        }  
        return employeeDtos;         
    }
    
    public EmployeeDto getEmployeeDetailsById(int employeeId) throws CustomException {
        Employee employee = employeeDao.retrieveEmployeeById(employeeId); 
        employee.setRoleName(employee.getRole().get(0).getName());     
        EmployeeDto employeeDto = employeeMapper.toDto(employee);
            
        return employeeDto;         
    }

    public boolean isEmployeeAvailable(int employeeId) throws CustomException {
        Employee employee = employeeDao.retrieveEmployeeById(employeeId);
        return (employee != null);
    }

    public Employee getEmployeeToViewAssignedProjects(int employeeId) throws CustomException {
        return employeeDao.retrieveEmployeeById(employeeId);        
    }

    public String updateEmployee(EmployeeDto employeeDto, String employeeRole) throws CustomException {
        Employee employee = employeeMapper.fromDtoId(employeeDto); 
        Employee employeeDb = employeeDao.retrieveEmployeeById(employee.getId());      
        Role role = roleDao.retrieveRoleByName(employeeRole);
        List<Role> roles = employeeDb.getRole();
        roles.add(role);
        employee.setRole(roles);
        return employeeDao.updateEmployee(employee); 
    }

    public String deleteEmployee(int employeeId) throws CustomException{
        List<Role> roles = new ArrayList<Role>();
        Employee employee = employeeDao.retrieveEmployeeById(employeeId);   
        employee.setStatus(Constants.INACTIVE); 
        employee.setRole(roles);
        return employeeDao.deleteEmployee(employee); 
    }
}
