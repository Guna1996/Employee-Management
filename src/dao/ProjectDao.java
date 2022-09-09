/*
 * Copyright (c) 2022, ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */
package com.ideas2it.dao;

import com.ideas2it.model.Project;
import com.ideas2it.utils.Constant;
import com.ideas2it.dao.BaseDao;
import com.ideas2it.exception.CustomException;

import java.sql.Connection;
import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.time.LocalDate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;  
import org.hibernate.Transaction; 

/**
* 
**/
public class ProjectDao extends BaseDao {
     Date date = new Date(0);
     
     /**
     * <p>
     * 
     * </p>
     * 
     */  
    public int insertProject(Project project) throws CustomException {
        SessionFactory sessionFactory = databaseConnection();
        int projectId = 0;
        Session session = null;
        try {
            session = sessionFactory.openSession();  
            Transaction transaction = session.beginTransaction();     
            projectId = (Integer)session.save(project);  
            transaction.commit();
            return projectId;        
        } catch(Exception exception) {
            exception.printStackTrace();
            throw new CustomException("Error occured while Inserting project", exception);
        } finally {
            if (session != null) {
                session.close();  
            }    
        }
    } 

    public List<Project> retrieveProjects() throws CustomException {
        SessionFactory sessionFactory = databaseConnection();
        Session session = null;  
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            List<Project> projects  = projects = session.createQuery("FROM Project where status = :status").setString("status", "active").list();
            transaction.commit();
            return projects;
        } catch (Exception exception) {
            exception.printStackTrace(); 
            throw new CustomException("Error occured while Retrieving all projects" ,exception);
        } finally {
            if (session != null) {
                session.close();  
            }   
        }    
    }

    public Project retrieveProjectById(int projectId) throws CustomException {
        SessionFactory sessionFactory = databaseConnection();
        Session session = null;      
        try {  
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction(); 
            Project project = (Project) session.createQuery("FROM Project where status = :status and id = :id").setString("status", "active").setInteger("id", projectId).uniqueResult();   
            transaction.commit();
            return project;
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new CustomException("Error occured while Retrieving project by Id", exception);
        } finally {
            if (session != null) {
                session.close();  
            }     
        }     
    }

    public String updateProject(Project project) throws CustomException {  
        Session session = null;   
        try {  
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.update(project); 
            transaction.commit();   
            return "Updated Successfully";
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new CustomException("Error occured while Updating project", exception);
        } finally {
            if (session != null) {
                session.close();  
            }     
        }   
    }

    public String deleteProject(Project project) throws CustomException {
        Session session = null;   
        try {  
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction(); 
            session.update(project); 
            transaction.commit(); 
            return "Deleted Successfully";
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new CustomException("Error occured while Deleting project by Id", exception);
        } finally {
            if (session != null) {
                session.close();  
            }       
        }    
    }
}
