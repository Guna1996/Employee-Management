/*
 * Copyright (c) 2022, ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */
package com.ideas2it.mapper;

import com.ideas2it.model.Employee;
import com.ideas2it.dto.EmployeeDto;
 
public class EmployeeMapper {
    
    public Employee fromDto(EmployeeDto employeeDto) {
        Employee employee = new Employee(employeeDto.getFirstName(), employeeDto.getLastName(), employeeDto.getStaffNumber(), employeeDto.getDob(), employeeDto.getGender(),
            employeeDto.getDateOfJoining(), employeeDto.getBatch(), employeeDto.getDesignation(), employeeDto.getCity(), employeeDto.getFatherName(), employeeDto.getEmail(), employeeDto.getPhoneNumber());
        return employee;
    }

    public EmployeeDto toDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto(employee.getFirstName(), employee.getLastName(), employee.getStaffNumber(), employee.getDob(), employee.getGender(),
            employee.getDateOfJoining(), employee.getBatch(), employee.getDesignation(), employee.getCity(), employee.getFatherName(), employee.getEmail(), employee.getPhoneNumber());
        return employeeDto;
    }
}