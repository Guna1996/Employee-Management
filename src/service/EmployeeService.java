/*
 * Copyright (c) 2022, ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */
package com.ideas2it.service;


import com.ideas2it.dao.EmployeeDao;
import com.ideas2it.service.RoleService;
import com.ideas2it.dto.EmployeeDto;
import com.ideas2it.exception.CustomException;
import com.ideas2it.mapper.EmployeeMapper;
import com.ideas2it.model.Employee;
import com.ideas2it.model.Role;
import com.ideas2it.utils.Constants;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code EmployeeService} class contains the methods to perform services for Employees. access 
 * and controls the methods of the classes, such as {@code EmployeeDao} {@code RoleService} {@code Employeemapper}, Accessed by  
 * creating an instance of that classes.
 *
 *
 * @author  Gunaseelan K
 * since 1.0
 * jls Advanced logics+
 */
public class EmployeeService {

    EmployeeDao employeeDao = new EmployeeDao();
    EmployeeMapper employeeMapper = new EmployeeMapper();
    RoleService roleService = new RoleService();
    
    /**
     * <p>
     * This method is used to add Employee details
     * </p>
     * 
     * @parm employeeDto is data transfer object which has employee details
     *
     */
    public int addEmployee(EmployeeDto employeeDto, String roleName) throws CustomException{
        List<Role> roles = new ArrayList<Role>();
        Employee employee = employeeMapper.fromDto(employeeDto);
        Role role = roleService.getRoleByName(roleName);
        roles.add(role);
        employee.setRole(roles);
        return employeeDao.insertEmployee(employee); 
    }
   
    /**
     * <p>
     * This method is used to get all Employee details
     * </p>
     *
     */
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

    /**
     * <p>
     * This method is used to get either trainee, trainer or project manager details
     * </p>
     *
     * * @parm roleName is Role of the employee 
     */
    public List<EmployeeDto> getEmployeesDetailsByRoleName(String roleName) throws CustomException{
        Role role = roleService.getRoleByName(roleName);
        List<Employee> employees = role.getEmployee();
        List<EmployeeDto> employeeDtos = new ArrayList<EmployeeDto>();
        for (Employee employee: employees) {
            employee.setRoleName(role.getName()); 
            EmployeeDto employeeDto = employeeMapper.toDto(employee);
            employeeDtos.add(employeeDto);
        }  
        return employeeDtos;         
    }
    
    /**
     * <p>
     * This method is used to get Employee details by ID
     * </p>
     *
     * * @parm employeeId is id of the employee
     */
    public EmployeeDto getEmployeeDetailsById(int employeeId) throws CustomException {
        Employee employee = employeeDao.retrieveEmployeeById(employeeId); 
        employee.setRoleName(employee.getRole().get(0).getName());     
        EmployeeDto employeeDto = employeeMapper.toDto(employee);
            
        return employeeDto;         
    }

    /**
     * <p>
     * This method is used to get Employee details by ID
     * </p>
     *
     * * @parm employeeId is id of the employee
     */
    public boolean isEmployeeAvailable(int employeeId) throws CustomException {
        Employee employee = employeeDao.retrieveEmployeeById(employeeId);
        return (employee != null);
    }

    /**
     * <p>
     * This method is used to get Employee to view assigned projects
     * </p>
     *
     * @parm employeeId is id of the employee
     */
    public Employee getEmployeeToViewAssignedProjects(int employeeId) throws CustomException {
        return employeeDao.retrieveEmployeeById(employeeId);        
    }

    /**
     * <p>
     * This method is used to update Employee details
     * </p>
     *
     * @parm employeeDto is data transfer object which has employee details
     * @parm employRole is Role of the employee 
     */
    public String updateEmployee(EmployeeDto employeeDto, String employeeRole) throws CustomException {
        Employee employee = employeeMapper.fromDtoId(employeeDto); 
        Employee employeeDb = employeeDao.retrieveEmployeeById(employee.getId());      
        Role role = roleService.getRoleByName(employeeRole);
        List<Role> roles = employeeDb.getRole();
        roles.add(role);
        employee.setRole(roles);
        return employeeDao.updateEmployee(employee); 
    }

    /**
     * <p>
     * This method is used to delete employee details by id
     * </p>
     *
     * * @parm employeeId is id of the employee
     */
    public String deleteEmployee(int employeeId) throws CustomException{
        List<Role> roles = new ArrayList<Role>();
        Employee employee = employeeDao.retrieveEmployeeById(employeeId);   
        employee.setStatus(Constants.INACTIVE); 
        employee.setRole(roles);
        return employeeDao.updateEmployee(employee); 
    }
}
