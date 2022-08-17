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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* Implementation to Assign, remove and modify Trainees to Trainer
**/
public class HumanResourceDao {
    
    static Map<String, List<String>> assignedTraineesAndTrainer = new HashMap<String, List<String>>(); 

 
    public void assignTraineesToTrainer(String trainerId, List<String> trainee) {
        assignedTraineesAndTrainer.putIfAbsent(trainerId, trainee);
    }
   
    public Map<String, List<String>> retrieveAssignedTrainees() {
        return assignedTraineesAndTrainer;
    }

    public void updateTraineesToTrainer(String trainerId, List<String> traineesId) {
        assignedTraineesAndTrainer.replace(trainerId, traineesId);
    }

   
    public void deleteTraineesToTrainer(String trainerId) {
       assignedTraineesAndTrainer.remove(trainerId);
    } 
}
   
    
    