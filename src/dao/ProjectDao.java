/*
 * Copyright (c) 2022, ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */
package com.ideas2it.dao;

import com.ideas2it.dao.BaseDao;
import com.ideas2it.exception.CustomException;
import com.ideas2it.model.Project;
import com.ideas2it.utils.Constant;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;  
import org.hibernate.Transaction; 

/**
 * The {@code ProjectDao} class contains the method for performing CRUD operaion in database.
 *
 *
 * @author  Gunaseelan K
 * since 1.0
 * jls Advanced logics+
 */
public class ProjectDao {

    /**
     * <p>
     * This method is used to insert Project details into database
     * </p>
     * 
     *  @parm project is a model which has project details
     */ 
    public int insertProject(Project project) throws CustomException {
        SessionFactory sessionFactory = BaseDao.databaseConnection();
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

    /**
     * <p>
     * This method is used to retrieve all Project details from database
     * </p>
     *
     */
    public List<Project> retrieveProjects() throws CustomException {
        SessionFactory sessionFactory = BaseDao.databaseConnection();
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

    /**
     * <p>
     * This method is used to retrieve Project details from database by id
     * </p>
     *
     * @parm projectId is id of the project
     */ 
    public Project retrieveProjectById(int projectId) throws CustomException {
        SessionFactory sessionFactory = BaseDao.databaseConnection();
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

    /**
     * <p>
     * This method is used to update Project details in database
     * </p>
     *
     *  @parm project is a model which has projectdetails
     */ 
    public String updateProject(Project project) throws CustomException {  
        SessionFactory sessionFactory = BaseDao.databaseConnection();
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

    public String deleteEmployeesAssignedToProject(Project project) throws CustomException {
        SessionFactory sessionFactory = BaseDao.databaseConnection();
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
