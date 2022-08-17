/*
 * Copyright (c) 2022, ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */
package com.ideas2it.dao;

import com.ideas2it.model.Trainer;

import java.util.ArrayList;
import java.util.List;

/**
* Implementation to Insert,update and Access Trainers 
**/
public class TrainerDao {
    List<Trainer> trainers = new ArrayList<Trainer>();
   
     /**
     * <p>
     * This method is used to insert Trainee details
     * </p>
     * 
     */  
    public boolean insertTrainer(Trainer trainer) {
        return trainers.add(trainer);
    }
 
    /**
     * <p>
     * This method is used to Retrieve Trainer details
     * </p>
     * 
     */ 
    public List<Trainer> retrieveTrainer() {
        return trainers;
    }

    /**
     * <p>
     * This method is used to Update Trainer details by index
     * </p>
     * 
     */
    public boolean updateTrainer(int index,Trainer updatedTrainer) {
        trainers.set(index, updatedTrainer);
        return true;
    }

    /**
     * <p>
     * This method is used to Delete Trainer details
     * </p>
     * 
     */
    public boolean deleteTrainer(Trainer trainer) {
       return trainers.remove(trainer);
    }
}
   
    
    