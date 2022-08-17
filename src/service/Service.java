/*
 * Copyright (c) 2022, ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */
package com.ideas2it.service;

import com.ideas2it.dao.HumanResourceDao;
import com.ideas2it.dao.TraineeDao;
import com.ideas2it.dao.TrainerDao;
import com.ideas2it.model.Trainee;
import com.ideas2it.model.Trainer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The {@code Service} class contains the method for trainees, trainers and Human Resource Services. access 
 * and controls the methods of the classes, such as {@code TraineeDao} {@code TrainerDao} {@code HumanResourceDao}, Accessed by  
 * creating an instance of that classes.
 * Implementation to perform Trainer and Trainee related Services
 *
 *
 * @author  Gunaseelan K
 * since 1.0
 * jls Advanced logics+
 */
public class Service {

    TraineeDao traineeDao = new TraineeDao();
    TrainerDao trainerDao = new TrainerDao(); 
    HumanResourceDao humanResourceDao = new HumanResourceDao();
   
    /**
     * <p>
     * This method is used to add Trainee details
     * </p>
     * 
     * @parm firstName is first name of the trainee
     * @parm lastName is last name of the trainee
     * @parm employeeId is employee id of the trainee
     * @parm dob is date of birth of the trainee
     * @parm gender is gender of the trainee
     * @parm traineeBatch is batch of trainee
     * @parm skills is skills of trainee
     * @parm fatherName is fathername of trainee
     * @parm email is email id of trainee
     * @parm phoneNumber is contact number of trainee
     */     
    public void addTrainee(String firstName, String lastName, String employeeId, String dob, int age, String gender,
        String traineeBatch, String skills, String fatherName, String email, String phoneNumber) {
        Trainee trainee = new Trainee(firstName, lastName, employeeId, dob, age, gender, traineeBatch, skills, fatherName, email, phoneNumber); 
        traineeDao.insertTrainee(trainee);
    }
    
    /**
     * <p>
     * This method is used to get a specific Trainee details by Email and DOB
     * </p>
     * 
     * @parm email Email id of trainee
     * @parm dob Date of birth of the trainee
     */ 
    public Trainee getTraineeDetail(String email, String dob) {
        List<Trainee> trainees = traineeDao.retrieveTrainee();
        for (Trainee trainee: trainees) {
            if (email.equals(trainee.getEmail()) && dob.equals(trainee.getDob())) {
               return trainee;
            } 
        }
        return null;
    }
 
    /**
     * <p>
     * This method is used to get all Trainers details
     * </p>
     * 
     */  
    public List<Trainee> getTraineesDetail() {
        return traineeDao.retrieveTrainee();
    }

    /**
     * <p>
     * This method is used to update Trainee details
     * </p>
     * 
     * @parm firstName first name of the trainee
     * @parm lastName last name of the trainee
     * @parm employeeId employee id of the trainee
     * @parm dob date of birth of the trainee
     * @parm gender gender of the trainee
     * @parm traineeBatch batch of trainee
     * @parm skills skills of trainee
     * @parm fatherName fathername of trainee
     * @parm email email id of trainee
     * @parm phoneNumber contact number of trainee
     * @parm verifyemail verify Email id of trainee
     * @parm verifyDob verify dob of trainee
     */
    public boolean updateTrainee(String firstName, String lastName, String employeeId, String dob, int age, String gender, 
        String traineeBatch, String skills, String fatherName, String email, String phoneNumber, String verifyEmail, String verifyDob) {
        Trainee updatedTrainee = new Trainee(firstName, lastName, employeeId, dob, age, gender, traineeBatch, skills, fatherName, email, phoneNumber); 
        int index = 0;
        List<Trainee> trainees = traineeDao.retrieveTrainee();
        for (Trainee trainee : trainees) {
            if (verifyEmail.equals(trainee.getEmail()) && verifyDob.equals(trainee.getDob())) {
                return traineeDao.updateTrainee(index, updatedTrainee);  
            }
            index++ ;
        }
        return false;  
    } 

    
    /**
     * <p>
     * This method is used to update specific Trainee details by email and DOB
     * </p>
     * 
     * @parm task  selects the set method of data which the Trainee decided to modify
     * @parm newData The newly entered data
     * @parm verifyEmail email to select the index to modify
     * @parm verifyDob dob to select the index to modify
     */
    public void updateSpecificDataOfTrainee(int task, String newData, int age, String checkEmail, String checkDob) {
        List<Trainee> trainees = traineeDao.retrieveTrainee();
        for (Trainee trainee: trainees) {
            if (checkEmail.equals(trainee.getEmail()) && checkDob.equals(trainee.getDob())) {
                switch(task) {
                    case 1:
                        trainee.setFirstName(newData); 
                        break;
                    case 2:
                        trainee.setLastName(newData); 
                        break;
                    case 3:
                        trainee.setEmployeeId(newData); 
                        break;
                    case 4:
                        trainee.setDob(newData);
                        trainee.setAge(age);
                        break;
                    case 5:
                        trainee.setGender(newData); 
                        break;
                    case 6:
                        trainee.setTraineeBatch(newData); 
                        break;
                    case 7:
                        trainee.setSkills(newData); 
                        break;
                    case 8:
                        trainee.setFatherName(newData);
                        break;
                    case 9:
                        trainee.setEmail(newData); 
                        break;
                    case 10:
                        trainee.setPhoneNumber(newData);
                        break;
                    default:
                        break;
                }
            }    
        }
    } 
  
    /**
     * <p>
     * This method is used to delete Trainee detail using employeeId
     * </p>
     * 
     * @parm employeeId this is used to delete based on emploeeId
     */
    public boolean deleteTrainee(String deleteData) {
        List<Trainee> trainees = traineeDao.retrieveTrainee();
        int index = 0;
        for (Trainee trainee: trainees) {
            if (deleteData.equals(trainee.getEmployeeId())) {
                return traineeDao.deleteTrainee(trainee);
            }
            index++;
        }
        return false;
    }
 
    /**
     * <p>
     * This method is used to verify wheather trainee is available using email and dob
     * </p>
     * 
     * @parm email email is used to verify wheather trainee is available
     * @parm dob this is used to verify wheather trainee is available
     */
    public boolean isTraineeAvailable(String mail, String dob) {
        List<Trainee> trainees = traineeDao.retrieveTrainee();
        for (Trainee trainee: trainees) {
            if (mail.equals(trainee.getEmail()) && dob.equals(trainee.getDob())) {
                return true; 
            }
        }
        return false;
    }   

    /**
     * <p>
     * This method is used to add Trainer details
     * </p>
     * 
     * @parm firstName First name of the trainer
     * @parm lastName Last name of the trainer
     * @parm employeeId Eployee id of the trainer
     * @parm dob Date of birth of the trainer
     * @parm gender Gender of the trainer
     * @parm expertiseIn Skills of trainer
     * @parm Experience Experience of trainer
     * @parm fatherName Fathername of trainer
     * @parm email Email id of trainer
     * @parm phoneNumber Contact number of trainer
     */  
    public void addTrainer(String firstName, String lastName, String employeeId, String dob, int age, String gender, 
        String expertiseIn, String experience, String fatherName, String email, String phoneNumber) { 
        Trainer trainer = new Trainer(firstName, lastName, employeeId, dob, age, gender, expertiseIn, experience, fatherName, email, phoneNumber);
        trainerDao.insertTrainer(trainer);
    }

    /**
     * <p>
     * This method is used to get a specific Trainer details by email and DOB
     * </p>
     * 
     * @parm email Email id of trainer
     * @parm dob Date of birth of the trainer
     */  
    public Trainer getTrainerDetail(String email, String dob) {
        List<Trainer> trainers = trainerDao.retrieveTrainer();
        for (Trainer trainer: trainers) {
            if (email.equals(trainer.getEmail()) && dob.equals(trainer.getDob())) {
                return trainer;
            } 
        }
        return null;
    }

    /**
     * <p>
     * This method is used to get all Trainers details
     * </p>
     * 
     */  
    public List<Trainer> getTrainersDetail() {
        return trainerDao.retrieveTrainer(); 
    }
          
    /**
     * <p>
     * This method is used to update Trainer details
     * </p>
     * 
     * @parm firstName First name of the trainer
     * @parm lastName Last name of the trainer
     * @parm employeeId Eployee id of the trainer
     * @parm dob Date of birth of the trainer
     * @parm gender Gender of the trainer
     * @parm expertiseIn Skills of trainer
     * @parm Experience Experience of trainer
     * @parm fatherName Fathername of trainer
     * @parm email Email id of trainer
     * @parm phoneNumber Contact number of trainer
     * @parm verifyEmail verify Email id of trainer
     * @parm verifyDob verify Email id of trainer
     */
    public boolean updateTrainer(String firstName, String lastName, String employeeId, String dob, int age, String gender, 
        String expertiseIn, String experience, String fatherName, String email, String phoneNumber, String verifyEmail, String verifyDob) {
        int index=0;
        List<Trainer> trainerList = trainerDao.retrieveTrainer();
        Trainer updatedTrainer = new Trainer(firstName, lastName, employeeId, dob, age, gender, expertiseIn, experience, fatherName, email, phoneNumber);
        for (Trainer trainer : trainerList) {
            if (verifyEmail.equals(trainer.getEmail()) && verifyDob.equals(trainer.getDob())) {
                return trainerDao.updateTrainer(index, updatedTrainer);    
            }
            index++ ;
        } 
        return false;  
    } 
                              
    /**
     * <p>
     * This method is used to update specific Trainer details by email and DOB
     * </p>
     * 
     * @parm task  selects the set method of data which the Trainer decided to modify
     * @parm newData The newly entered data
     * @parm verifyEmail email to select the index to modify
     * @parm verifyDob dob to select the index to modify
     */
    public void updateSpecificDataOfTrainer(int task, String newData, int age, String verifyEmail, String verifyDob) {
        List<Trainer> trainers = trainerDao.retrieveTrainer();
        for (Trainer trainer: trainers) {
            if (verifyEmail.equals(trainer.getEmail()) && verifyDob.equals(trainer.getDob())) {
                switch(task) {
                    case 1:
                        trainer.setFirstName(newData); 
                        break;
                    case 2:
                        trainer.setLastName(newData); 
                        break;
                    case 3:
                        trainer.setEmployeeId(newData); 
                        break;
                    case 4:
                        trainer.setDob(newData);
                        trainer.setAge(age);
                        break;
                    case 5:
                        trainer.setGender(newData); 
                        break;
                    case 6:
                        trainer.setExpertiseIn(newData); 
                        break;
                    case 7:
                        trainer.setExperience(newData); 
                        break;
                    case 8:
                        trainer.setFatherName(newData);
                        break;
                    case 9:
                        trainer.setEmail(newData); 
                        break;
                    case 10:
                        trainer.setPhoneNumber(newData);
                        break;
                    default:
                        break;
                }
            }    
        }
    }    

    /**
    * <p>
    * This method is used to delete Trainer detail using employeeId
    * </p>
    * 
    * @parm employeeId this is used to delete based on emploeeId
    */
    public boolean deleteTrainer(String employeeId) { 
        int index =0;
        List<Trainer> trainers = trainerDao.retrieveTrainer();
        for (Trainer trainer: trainers) {
            if (employeeId.equals(trainer.getEmployeeId())) {
                return trainerDao.deleteTrainer(trainer);
                         
            }
            index++;
        }
        return false;
    }

    /**
     * <p>
     * This method is used to verify wheather trainer is available using email and dob
     * </p>
     * 
     * @parm email email is used to verify wheather trainer is available
     * @parm dob this is used to verify wheather trainer is available
     */
    public boolean isTrainerAvailable(String mail, String dob) {
        List<Trainer> trainers = trainerDao.retrieveTrainer();
        for (Trainer trainer: trainers) {
            if (mail.equals(trainer.getEmail()) && dob.equals(trainer.getDob())) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * <p>
     * This method is used to Assign trainees to trainer using Map
     * </p>
     * 
     * @parm trainerId is used as key and added in map
     * @parm trainerIds is used as value and added in map
     */
    public boolean assignTraineesToTrainer(String trainerId, List<String> traineesId) {
        boolean isAvailable = false;
        boolean isNewTeam = false;
        int loop = 0;
        List<String> assignedTrainee = new ArrayList<String>();
        List<Trainer> trainers = trainerDao.retrieveTrainer();
        List<Trainee> trainees = traineeDao.retrieveTrainee();
        
        for (Trainer trainer: trainers) {
            if (trainerId.equals(trainer.getEmployeeId())) {
                isNewTeam = true;
                break;
            }
        }

        for (Trainee trainee: trainees) {
            if (traineesId.contains(trainee.getEmployeeId())) {
                loop++;
                if (isNewTeam) {
                    assignedTrainee = new ArrayList<String>();      
                    assignedTrainee.add(trainee.getEmployeeId());
                    isAvailable = true;
                } else {
                    assignedTrainee.add(trainee.getEmployeeId());
                }
                isNewTeam = false;
            } 
        }
        
        if (loop == traineesId.size()) {
            humanResourceDao.assignTraineesToTrainer(trainerId, assignedTrainee); 
            return true;
        } else {
            return false;
        }
    } 

    /**
     * <p>
     * This method is used to retieve the assigned trainees to trainer
     * </p>
     */
    public Map<String, List<String>> getAssignedTraineesToTrainer() {
        return humanResourceDao.retrieveAssignedTrainees();
        
    } 

    /**
     * <p>
     * This method is used to delete the assigned trainees to trainer based on the given traine id
     * </p>
     */
    public void deleteTraineesToTrainer(String trainerId) {
        humanResourceDao.deleteTraineesToTrainer(trainerId);
    }

    /**
     * <p>
     * This method is used to update the assigned trainees to trainer
     * </p>
     */
    public boolean updateTraineesToTrainer(String trainerId, List<String> traineesId) {
        humanResourceDao.updateTraineesToTrainer(trainerId, traineesId);
        return true;
    }
    
    public void defaultTrainees() {
        Trainee firstTrainee = new Trainee("Guna", "K", "I2I5","1996-08-11" , 25, "male", "3", "java", "kumastha", "guna@gmail.com", "9445401205");
        Trainee secondTrainee = new Trainee("velayan", "M", "I2I6","2000-02-28" , 22, "male", "3", "java", "mari", "velayan@gmail.com", "7094038636");
        Trainee thirdTrainee = new Trainee("velachi", "j", "I2I7","1994-08-10" , 28, "female", "3", "java", "kumar", "velachi@gmail.com", "9487656755");
        Trainee fourthTrainee = new Trainee("Gaja", "K", "I2I8","1999-03-09" , 23, "male", "3", "java", "raju", "gaja@gmail.com", "9455443322"); 
        traineeDao.insertTrainee(firstTrainee);
        traineeDao.insertTrainee(secondTrainee);
        traineeDao.insertTrainee(thirdTrainee);
        traineeDao.insertTrainee(fourthTrainee);
    }  

    public void defaultTrainers() {
        Trainer firstTrainer = new Trainer("vijay", "K", "I2I1","1988-08-11" , 34, "male", "java", "10", "ravi", "vijay@gmail.com", "9445401205");
        Trainer secondTrainer = new Trainer("sivashankar", "M", "I2I2","1990-02-28" , 32, "male", "java", "6", "maruthu", "siva@gmail.com", "7094038636");
        Trainer thirdTrainer = new Trainer("jagaJeevan", "s", "I2I3","1991-08-10" , 31, "female", "python", "6", "kumar", "jaga@gmail.com", "9487656755");
        Trainer fourthTrainer = new Trainer("madhan", "s", "I2I4","1992-03-09" , 30, "male", "java", "8", "raasu", "madhan@gmail.com", "9455443322"); 
        trainerDao.insertTrainer(firstTrainer);
        trainerDao.insertTrainer(secondTrainer);
        trainerDao.insertTrainer(thirdTrainer);
        trainerDao.insertTrainer(fourthTrainer);
    }      
}
