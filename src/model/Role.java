/*
 * Copyright (c) 2022, ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */
package com.ideas2it.model;

import java.util.ArrayList;
import com.ideas2it.model.Employee;
import java.util.List; 
import javax.persistence.*;
import java.time.LocalDate;  
import javax.persistence.Entity;  
import javax.persistence.Id;  
import javax.persistence.Table; 
import javax.persistence.Column;
import javax.persistence.GeneratedValue; 
 
@Entity
@Table(name = "role")
public class Role {

    @Id 
    @GeneratedValue 
    @Column(name="id")
    protected int id;

    @Column(name = "name", unique = true)
    protected String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
    private List<Employee> employees;
   
    public Role() { 
        
    }

    public Role(String name) { 
        this.name = name;
    }
 
    public Role(int id, String name) { 
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployee() {
        return employees;
    }
    
    public void setEmployee(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return name + id;
    }
}