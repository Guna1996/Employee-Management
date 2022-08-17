/*
 * Copyright (c) 2022, ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */
package com.ideas2it.dao;

import com.ideas2it.model.Trainee;

import java.util.ArrayList;
import java.util.List;
 
/**
* Implementation to Insert,update and Access Trainees
**/
public class TraineeDao {
    List<Trainee> trainees = new ArrayList<Trainee>();
   
    /**
     * <p>
     * This method is used to insert Trainee details
     * </p>
     * 
     */  
    public boolean insertTrainee(Trainee trainee) {
        return trainees.add(trainee);
    }
 
    /**
     * <p>
     * This method is used to Retrieve Trainee details
     * </p>
     * 
     */ 
    public List<Trainee> retrieveTrainee() {
        return trainees;
    }

    /**
     * <p>
     * This method is used to Update Trainee details by Index
     * </p>
     * 
     */
    public boolean updateTrainee(int index,Trainee updatedTrainee) {
        trainees.set(index, updatedTrainee);
        return true;
    }

    /**
     * <p>
     * This method is used to Delete Trainee details
     * </p>
     * 
     */
    public boolean deleteTrainee(Trainee trainee) {
        return trainees.remove(trainee);
    }
}
   
    
    