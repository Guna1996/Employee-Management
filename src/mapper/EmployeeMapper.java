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
    
    public Employee employeeDtoTOEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee(employeeDto.getFirstName(), employeeDto.getLastName(), employeeDto.getStaffNumber(), employeeDto.getDob(), employeeDto.getGender(),
            employeeDto.getDateOfJoining(), employeeDto.getBatch(), employeeDto.getDesignation(), employeeDto.getCity(), employeeDto.getFatherName(), employeeDto.getEmail(), employeeDto.getPhoneNumber());
        return employee;
    }
}