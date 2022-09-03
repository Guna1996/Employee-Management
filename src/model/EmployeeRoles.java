/*
 * Copyright (c) 2022, ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */
package com.ideas2it.model;

import java.time.LocalDate;  
import javax.persistence.Entity;  
import javax.persistence.Id;  
import javax.persistence.Table; 
import javax.persistence.Column;
import javax.persistence.GeneratedValue; 
import javax.persistence.*;
 
@Entity
@Table(name = "employee_roles")
public class EmployeeRoles {
    @Id 
    @GeneratedValue
    @Column(name="id", nullable = false, columnDefinition="INT NOT NULL AUTO_INCREMENT")
    protected int id;

    @Column(name="employee_id", nullable = false, columnDefinition="INT NOT NULL FOREIGN KEY(employee_id) REFERENCES employee(id)")
    protected int employee_id;

    @Column(name="role_id", nullable = false, columnDefinition="INT NOT NULL FOREIGN KEY(role_id) REFERENCES role(id)")
    protected int role_id;

}