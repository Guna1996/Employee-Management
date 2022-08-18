/*
 * Copyright (c) 2022, ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */
package com.ideas2it.controller;

import com.ideas2it.exception.MyCustomException;
import com.ideas2it.model.Trainee;
import com.ideas2it.model.Trainer;
import com.ideas2it.service.Service;
import com.ideas2it.utils.ValidationUtil;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.text.SimpleDateFormat;


/**
 * The {@code EmployeeController} class contains the main method. access  and controls the methods of the
 * class {@code Service}, Accessed by creating an instance of that class.
 * Implementation to get the Employee Input and display Output
 *
 *
 * @author  Gunaseelan K
 * since 1.0
 * jls Advanced logics+
 */
public class EmployeeController {
    private Scanner scanner = new Scanner(System.in);
    private Service service = new Service();
    private static boolean isContinue = true;
 
    public static void main(String[] args) {
        EmployeeController employeeController = new EmployeeController();
        do {
            try {
                employeeController.startOperation();
            } catch(InputMismatchException error) {  
                System.out.println(error+" Error!! please Enter valid input");
            } 
        } while (isContinue);      
    }
    
    
    /**
     * <p>
     * This the concrete method which calls all other methods for various operations for trainee portal and trainer portal
     * </p>
     * 
     * 
     */    
    public void startOperation() {
        service.defaultTrainees();
        service.defaultTrainers();
        Scanner scannerInput = new Scanner(System.in);
        while (isContinue) {
            System.out.print("\n\nChoose one option from below\n 1. Trainee Portal \n 2. Trainer Portal\n 3. HR Portal\n 4. exit\n ");  
            int choice = scannerInput.nextInt();
            switch (choice) {
                case 1:
                    try {
                        traineePortal(choice);
                    } catch(InputMismatchException error) {  
                        System.out.println(error+" Error!! invalid input");
                    }   
          	    break;

                case 2:
                    try {
                        trainerPortal(choice);
                    } catch(InputMismatchException error) {  
                        System.out.println(error+" Error!! invalid input");
                    }  
                    break;
         
                case 3:
                    try {  
                        humanResourcePortal();
                    } catch(InputMismatchException error) {  
                        System.out.println(error+" Error!! invalid input");
                    }  
                    break;

                default:
                        System.out.print("Thank you");
                        isContinue = false;
                        break;
            }          
        }               
    }

    /**
     * <p>
     * This method is used Perform Trainee operations
     * </p>
     * 
     */ 
    public void traineePortal(int choice) {
        Scanner scannerInput = new Scanner(System.in);
        boolean isChoice = true;
        do {
            System.out.print("\n\n****choose one option from below from TRAINEE PORTAL****\n 1. Add your data\n"
                +" 2. update your complete details\n 3. View your data\n 4. update your specific detail\n 5. Go Back\n");  
            int operation = scannerInput.nextInt(); 
            switch (operation) {
	        case 1:
                    try {
                        addOrUpdateEmployeeDetails(operation, choice);
                    } catch(MyCustomException error) {  
                        System.out.println(error.getMessage()); 
                    } 
                    break;

                case 2:
                    try {
                        addOrUpdateEmployeeDetails(operation, choice);
                    } catch(MyCustomException error) {  
                        System.out.println(error.getMessage()); 
                    } 
                    break;
                                                            
                case 3:
                    System.out.print("Enter Trainee EmailId: ");
                    String traineeEmail = scanner.next();
                    System.out.print("Enter Trainee DOB (YYYY-MM-DD): ");
                    String traineeDob = scanner.next();
                    viewTraineeByEmailAndDob(traineeEmail, traineeDob);
                    break;
       
                case 4:
                    try {
                        updateEmployeeDetail(choice);
                    } catch(MyCustomException error) {  
                        System.out.println(error.getMessage()); 
                    } 
                    break;

                default:
                    System.out.print("Thank you");
                    isChoice = false;
            }    
        } while (isChoice);   
    }       
    
     /**
     * <p>
     * This method is used Perform Trainer operations
     * </p>
     * 
     */ 
    public void trainerPortal(int choice) {
        Scanner scannerInput = new Scanner(System.in);
        boolean isDecession = true;
        do {
            System.out.print("\n\n****choose one option from below from TRAINER PORTAL****"
                +"\n 1. Add Trainer data\n 2. update your complete details\n 3. view all Trainer data"
                +"\n 4. View all Trainee data\n 5. Delete a Trainee data\n 6. Delete a Trainer data"
                +"\n 7. update your Specific detail\n 8. Go Back\n");
            int trainerOption = scannerInput.nextInt();
  
            switch (trainerOption) {
                case 1:
                    try {
                        addOrUpdateEmployeeDetails(trainerOption, choice);
                    } catch(MyCustomException error) {  
                        System.out.println(error.getMessage()); 
                    } 
                    break;
                
                case 2:
                    try {
                        addOrUpdateEmployeeDetails(trainerOption, choice);
                    } catch(MyCustomException error) {  
                        System.out.println(error.getMessage()); 
                    } 
                    break;                                  

                case 3:
                    displayTrainers();
                    break;
                           
                case 4:
                    displayTrainees();
                    break;

                case 5:
                    deleteTrainee();
                    break;
                            
                case 6:
                    deleteTrainer();
                    break;

                case 7:
                    try {
                        updateEmployeeDetail(choice);
                    } catch(MyCustomException error) {  
                        System.out.println(error.getMessage()); 
                    } 
                    break;

                default:
                    System.out.print("Thank you");
                    isDecession = false;
            }
        } while(isDecession);
    }
 
    public void humanResourcePortal() {
        Scanner scannerInput = new Scanner(System.in);
        boolean isOption = true;
        do {
            System.out.print("\n\n****choose one option from below from HR PORTAL****"
                +"\n 1. Assign Trainees to Trainer\n 2. update trainees to trainer"
                +"\n 3. view all Trainees mapped to trainers\n 4. delete Team using trainer id.\n 5. Go Back\n");
            int hrOption = scannerInput.nextInt();
            
            switch (hrOption) {
                case 1:
                    assignTraineesToTrainer();
                    break;
                 
                case 2:
                    updateTraineesToTrainer();
                    break;                                  
                     
                case 3:
                    displayAssignedTraineesToTrainer(); 
                    break;

                case 4:
                    deleteTeam();
                    break;     
                       
                default:
                    System.out.print("Thank you");
                    isOption = false;  
            }  
        } while (isOption);
    }

    /**
     * <p>
     * This method is used to add or update Employee details
     * </p>
     * 
     */ 
    public void addOrUpdateEmployeeDetails(int operation, int choice) throws MyCustomException {
        boolean isTraineeAvailable = false;
        boolean isTrainerAvailable = false;
        int age = 0;
        int trainee = 1;
        int trainer = 2;
        int add = 1;
        int update = 2;
        String employeeEmail = null , employeeDob = null;
        String firstName = "", lastName = "", employeeId = "", skills = "", dob = "", gender = "", traineeBatch = "",
            fatherName = "", email = "", phoneNumber = "", expertiseIn = "", experience = "";
        if (operation == (update)) {
            System.out.print("Enter Trainee EmailId: ");
            employeeEmail = scanner.next();
            System.out.print("Enter Trainee DOB: ");
            employeeDob = scanner.next();
            if (choice == trainee) {
                isTraineeAvailable = service.isTraineeAvailable(employeeEmail,  employeeDob);
            } else {
                isTrainerAvailable = service.isTrainerAvailable(employeeEmail,  employeeDob);   
            }
        }
        if ((operation == add) || isTraineeAvailable || isTrainerAvailable) {
            System.out.print("Enter the Required Data to **SignUP**\n"); 
            try { 
	        firstName = validateString("First Name:");
	        lastName = validateString("Last Name:");
	        dob = validateString("dob (YYYY-MM-DD):");
                age = ValidationUtil.calculateAge(dob);
                gender = validateString("Gender:");

                if ((choice == trainer) || isTrainerAvailable) {
                    expertiseIn = validateString("ExpertiseIn:");
                    System.out.println("Experience:");
	            experience = scanner.next();
                } else if ((choice == trainee) || isTraineeAvailable) {
                    System.out.println("TraineeBatch:");
                    traineeBatch = scanner.next();
                    skills = validateString("skills:");
                }
                fatherName = validateString("Father Name  :");
                email = validateString("Email Id:");
                phoneNumber = validateString("phoneNumber:");
                System.out.println("EmployeeId Generated");  
                employeeId = ValidationUtil.generateEmployeeId();
            } catch (MyCustomException exception) {
                throw new MyCustomException(exception.getMessage());
            } 
            timeDelay();
            if ((operation == add) && (choice == trainee)) {
                service.addTrainee(firstName, lastName, employeeId, dob, age, gender, traineeBatch, skills,
                    fatherName, email, phoneNumber);
                System.out.println("\ntrainee Added SUCCESSFULLY");
            } else if (isTraineeAvailable && (choice == trainee)) {
                service.updateTrainee(firstName, lastName, employeeId, dob, age, gender, traineeBatch, skills,
                    fatherName, email, phoneNumber, employeeEmail, employeeDob);
                System.out.println("\ntrainee Modified SUCCESSFULLY");
            } else if ((operation == add) && (choice == trainer)) {  
                service.addTrainer(firstName, lastName, employeeId, dob, age, gender, expertiseIn, experience,
                    fatherName, email, phoneNumber);
                System.out.println("\ntrainer Added SUCCESSFULLY");
            } else if (isTrainerAvailable && (choice == trainer)) {
                service.updateTrainer(firstName, lastName, employeeId, dob, age, gender, expertiseIn, experience,
                    fatherName, email, phoneNumber, employeeEmail, employeeDob);
                System.out.println("\ntrainer Modified SUCCESSFULLY");
            }
        } else {
            
            System.out.println("invalid email or dob");
        }      
    }

    /**
     * <p>
     * This method is used to update Employees specific detail by tainees email and dob
     * </p>
     *
     */ 

    public void updateEmployeeDetail(int choice) throws MyCustomException {
        String newData;
        int trainee = 1;
        int age;
        boolean isTraineeAvailable = false;
        boolean isTrainerAvailable = false;
        System.out.print("Enter Trainee EmailId: ");
        String email = scanner.next();
        System.out.print("Enter Trainee DOB: ");
        String dob = scanner.next();
        if (choice == trainee) {
            isTraineeAvailable = service.isTraineeAvailable(email,  dob);
        } else {
            isTrainerAvailable = service.isTrainerAvailable(email,  dob);
        }
        if (isTraineeAvailable || isTrainerAvailable) {
            if (choice == trainee) {
                System.out.println("choose the specifc detail you want to ***MODIFY***\n1. firstName\n2. lastName\n"
                    +"3. employeeId\n4. dob\n5. gender\n6. traineeBatch\n7. skills\n8. fatherName\n9. email\n10. phoneNumber\n");  
            } else {
                System.out.println("choose the specifc detail you want to ***MODIFY***\n1. firstName\n2. lastName\n"
                    +"3. employeeId\n4. dob\n5. gender\n6. ExpertiseIn\n7. Experience\n8. fatherName\n9. email\n10. phoneNumber\n"); 
            }
            int task = scanner.nextInt();
            if (task == 4) {
                try {
                    newData = validateString("dob (YYYY-MM-DD):");
                    age = ValidationUtil.calculateAge(newData);
                } catch (MyCustomException exception) {
                    throw new MyCustomException("invalid");
                }
                timeDelay();
                if (choice == trainee) {
                    service.updateSpecificDataOfTrainee(task, newData, age, email, dob);
                    System.out.println("\ntrainee detail Modified SUCCESSFULLY");
                } else {
                    service.updateSpecificDataOfTrainer(task, newData, age, email, dob);
                    System.out.println("\ntrainer detail Modified SUCCESSFULLY");
                }           
            } else if (task > 10) {
                System.out.println("invalid option");
            } else {
                System.out.println("Enter the new data:");
                newData = scanner.next();
                timeDelay();
                if (choice == trainee) {
                    service.updateSpecificDataOfTrainee(task, newData, 0, email, dob);
                    System.out.println("\ntrainee detail Modified SUCCESSFULLY");
                } else {
                    service.updateSpecificDataOfTrainer(task, newData, 0, email, dob);
                    System.out.println("\ntrainer detail Modified SUCCESSFULLY");
                }   
            }
        } else {
            System.out.println("invalid email or dob!!");
        }
    }   
    
   /**
    * <p>
    * This method is used to Display trainees details
    * </p>
    * 
    */ 
    public void displayTrainees() {
        int index = 0;
        timeDelay();
        System.out.println("\n\n-----------------------------------------------------------------------------"
            +"------------------------------------------------------------------------------------------");  
        System.out.printf("%17s %17s %17s %17s %10s %10s %10s %10s %17s %20s %13s\n", "FIRST NAME", "LAST NAME", 
            "EMPLOYEE ID", "DATE OF BIRTH", "AGE", "GENDER", "TRANEE BATCH", "SKILLS", "FATHER NAME", "EMAIL", "PHONE NUMBER" ); 
        System.out.println("------------------------------------------------------------------------------"
            +"-----------------------------------------------------------------------------------------"); 
        List<Trainee> trainees = service.getTraineesDetail();
        for (Trainee trainee: trainees) {
            System.out.format("%17s %17s %17s %17s %10s %10s %10s %10s %17s %20s %13s\n", trainee.getFirstName(), 
            trainee.getLastName(), trainee.getEmployeeId(), trainee.getDob(), trainee.getAge(), trainee.getGender(),
            trainee.getTraineeBatch(), trainee.getSkills(), trainee.getFatherName(), trainee.getEmail(), trainee.getPhoneNumber());
            index++;
        }

        if (index == 0) {
            System.out.println("*********************************Empty*********************************"); 
        }    
    }
    
     /**
     * <p>
     * Get and Display particular Trainee details by email and DOB
     * </p>
     *
     * @parm email verifies and helps to find the trainee to display
     * @parm dob verifies and helps to find the trainee to display
     */
    public void viewTraineeByEmailAndDob(String email, String dob) {
        boolean isTraineeAvailable = false;
        isTraineeAvailable = service.isTraineeAvailable(email, dob); 
        timeDelay();
        if (isTraineeAvailable) {
            System.out.println("\n\n---------------------------------------------------------------------------------"
                +"------------------------------------------------------------------------------------------");  
            System.out.printf("%17s %17s %17s %17s %10s %17s %10s %17s %24s %13s\n", "FIRST NAME", "LAST NAME", "EMPLOYEE ID", 
                "DATE OF BIRTH", "GENDER", "TRIANEE BATCH", "SKILLS", "FATHER NAME", "EMAIL", "PHONE NUMBER" ); 
            System.out.println("----------------------------------------------------------------------------------"
                +"-----------------------------------------------------------------------------------------"); 
            Trainee trainee = service.getTraineeDetail(email, dob);
            System.out.format("%17s %17s %17s %17s %10s %17s %10s %17s %24s %13s\n", trainee.getFirstName(), 
                trainee.getLastName(), trainee.getEmployeeId(), trainee.getDob(), trainee.getGender(), trainee.getTraineeBatch(), 
                trainee.getSkills(), trainee.getFatherName(), trainee.getEmail(), trainee.getPhoneNumber());      
        } else {
            System.out.print("Invalid EmailId or DOB");
        }
    }    
    
     /**
     * <p>
     * This method is used to Delete trainee details
     * </p>
     * 
     */ 
    public void deleteTrainee() {
        boolean isDeleted = false;
        boolean isAvailable = false;
        System.out.print("Enter Trainer EmailId: ");
        String trainerEmail = scanner.next();
        System.out.print("Enter Trainer DOB (DD/MM/YYYY): ");
        String trainerDob = scanner.next();
        isAvailable = service.isTrainerAvailable(trainerEmail , trainerDob);
        if (isAvailable) {
            System.out.println("Enter Trainee Employee Number:");
            String employeeId = scanner.next();
            isDeleted = service.deleteTrainee(employeeId);
            if (isDeleted) {
                System.out.println("Deleted SUCCESSFULLY");
            } else {
                System.out.println("Trainee NOT Found");
            }
        } else {
            System.out.println("Invalid EmailId or DOB");
        }
    }
  
    /**
     * <p>
     * This method is used to Display trainers details
     * </p>
     * 
     */ 
    public void displayTrainers() {
        timeDelay();
        int index=0;
        System.out.println("\n\n----------------------------------------------------------------------------------"
            +"-----------------------------------------------------------------------------------------");  
        System.out.printf("%17s %17s %15s %10s %10s %10s %10s %17s %17s %20s %13s\n", "FIRST NAME", "LAST NAME", 
            "EMPLOYEE ID", "DATE OF BIRTH", "AGE", "GENDER", "EXPERTISE IN", "EXPERIENCE", "FATHER NAME", "EMAIL", "PHONE NUMBER" ); 
        System.out.println("----------------------------------------------------------------------------------"
            +"-----------------------------------------------------------------------------------------"); 
        List<Trainer> trainerList = service.getTrainersDetail();
        for (Trainer trainer: trainerList) {
            System.out.format("%17s %17s %15s %10s %10s %10s %10s %17s %17s %20s %13s\n", trainer.getFirstName(), trainer.getLastName(), 
            trainer.getEmployeeId(), trainer.getDob(), trainer.getAge(), trainer.getGender(), trainer.getExpertiseIn(), 
            trainer.getExperience(), trainer.getFatherName(), trainer.getEmail(), trainer.getPhoneNumber());
            index++;
        }
        if (index == 0) {
            System.out.println("*********************************Empty*********************************"); 
        }
    } 

    /**
     * <p>
     * Get and 44Display Trainer details by email and DOB
     * </p>
     * 
     * @parm email verifies and helps to find the trainer to display
     * @parm dob verifies and helps to find the trainer to display
     */
    public void viewTrainerByEmailAndDob(String email, String dob) {
        boolean isTrainerAvailable = false;
        isTrainerAvailable = service.isTrainerAvailable(email , dob); 
        timeDelay();
        if (isTrainerAvailable == true) {
            System.out.println("\n\n---------------------------------------------------------------------------------"
                +"------------------------------------------------------------------------------------------");  
            System.out.printf("%17s %17s %17s %10s %17s %10s %17s %17s %24s %13s\n", "FIRST NAME", "LAST NAME", "EMPLOYEE ID", 
                "DATE OF BIRTH", "GENDER", "EXPERTISE IN", "EXPERIENCE", "FATHER NAME", "EMAIL", "PHONE NUMBER" ); 
            System.out.println("------------------------------------------------------------------------------------"
                +"---------------------------------------------------------------------------------------"); 
            Trainer trainer = service.getTrainerDetail(email, dob);
            System.out.format("%17s %17s %17s %10s %17s %10s %17s %17s %24s %13s\n", trainer.getFirstName(), 
                trainer.getLastName(), trainer.getEmployeeId(), trainer.getDob(), trainer.getGender(), 
                trainer.getExpertiseIn(), trainer.getExperience(), trainer.getFatherName(), trainer.getEmail(), trainer.getPhoneNumber());      
        }   
        else
            System.out.print("Invalid EmailId or DOB");
    } 
 
     /**
     * <p>
     * This method is used to Delete trainer details
     * </p>
     * 
     */ 
    public void deleteTrainer() {
        boolean isDeleted = false;
        boolean isAvailable = false;
        System.out.print("Enter Trainer EmailId: ");
        String trainerEmail = scanner.next();
        System.out.print("Enter Staff DOB (YYYY-MM-DD): ");
        String trainerDob = scanner.next();
        isAvailable = service.isTrainerAvailable(trainerEmail , trainerDob);
        timeDelay();
        if (isAvailable) {
            System.out.print("\nEnter Trainer EmployeId: ");
            String employeeId = scanner.next();
            isDeleted = service.deleteTrainer(employeeId);
            if (isDeleted) {
                System.out.println("\nDeleted SUCCESSFULLY");
            } else {
                System.out.println("\nTrainee NOT Found");
            }
        } else {
            System.out.println("\nInvalid EmailId or DOB");
        }
    }
    
     /**
     * <p>
     * This method is used to Assign Trainees to Trainer details
     * </p>
     * 
     */ 
    public void assignTraineesToTrainer() {
        List<String> assignedTraineeId = new ArrayList<String>();
        Map<String, List<String>> assignedTraineesAndTrainer = service.getAssignedTraineesToTrainer();
        System.out.print("Enter the User Id:");
        String userId = scanner.next();
        System.out.print("Enter the Password");
        String password = scanner.next();
        if (userId.equals("IDEAS2") && password.equals("IT")) {
            System.out.print("\t\t\tTRAINER LIST\n\n");
            displayTrainers();
            System.out.print("\n\n\t\t\tTRAINEE LIST\n\n");
            displayTrainees();
            boolean isAssigned = false;
            System.out.print("Enter the Trainer Id: ");
            String trainerId = scanner.next();  
            if (!assignedTraineesAndTrainer.containsKey(trainerId)) {   
                System.out.print("Enter the number of trainee you want to Assign: ");
                int number = scanner.nextInt();

                for (int index=0; index<number;index++) {
                    System.out.print("Enter the Trainee Id: ");
                    String traineeId = scanner.next();
                    assignedTraineeId.add(traineeId);
                }
                isAssigned = service.assignTraineesToTrainer(trainerId, assignedTraineeId);
                timeDelay();
                if (isAssigned) {      
                    System.out.print("\nAssigned Successfully");
                } else {
                    System.out.print("\ninvalid employee ID!!");
                }
            } else {
                System.out.print("\nThis trainer already assigned try again!");
            }
        } else {
            System.out.print("\ninvalid login id or password");
        }      
    }
    
    /**
     * <p>
     * This method is used to Update Trainees to Trainer details
     * </p>
     * 
     */ 
    public void updateTraineesToTrainer() {
        List<String> assignedTraineesId = new ArrayList<String>();
        Map<String, List<String>> assignedTraineesAndTrainer = service.getAssignedTraineesToTrainer();
        System.out.print("Enter the User Id:");
        String userId = scanner.next();
        System.out.print("Enter the Password");
        String password = scanner.next();

        if (userId.equals("IDEAS2") && password.equals("IT")) {
            displayAssignedTraineesToTrainer();
            boolean isUpdated = false;
            System.out.print("Enter the Trainer Id: ");
            String trainerId = scanner.next();  

            if (assignedTraineesAndTrainer.containsKey(trainerId)) {   
                System.out.print("Enter the number of trainee you want to Assign: ");
                int number = scanner.nextInt();

                for (int index=0; index<number;index++) {
                    System.out.print("Enter the Trainee Id: ");
                    String traineeId = scanner.next();
                    assignedTraineesId.add(traineeId);
                }
                isUpdated = service.updateTraineesToTrainer(trainerId, assignedTraineesId);
                timeDelay();
                if (isUpdated) {      
                    System.out.print("\nUpdated Successfully");
                } else {
                    System.out.print("\ninvalid employee ID!!");
                }
            } else {
                System.out.print("\nThis trainer doesn't exist try again!");
            }
        } else {
            System.out.print("\ninvalid login id or password");
        }
    }
   
    /**
     * <p>
     * This method is used to Display Trainees to Trainer details
     * </p>
     * 
     */ 
    public void displayAssignedTraineesToTrainer() {
        int index = 0;
        List<Trainer> trainers = service.getTrainersDetail();
        List<Trainee> trainees = service.getTraineesDetail();
        Map<String, List<String>> assignedTraineesAndTrainer = service.getAssignedTraineesToTrainer();
        timeDelay();
        for (Map.Entry<String, List<String>> entry:assignedTraineesAndTrainer.entrySet()) {
            index++;
            System.out.println("\n\n----------------------------------------------------------------------------------"
                +" TEAM "+index+"----------------------------------------------------------------------------------");  
            String assignedTrainer = entry.getKey();   
            List<String> assignedTrainees = entry.getValue();
            System.out.println("\nTRAINER:\n");

            for (Trainer trainer: trainers) {
                if (assignedTrainer.equals(trainer.getEmployeeId())) {
                    System.out.println("----------------------------------------------------------------------------------"
                        +"-----------------------------------------------------------------------------------------");  
                    System.out.printf("%17s %17s %15s %10s %10s %10s %10s %17s %17s %20s %13s\n", "FIRST NAME", "LAST NAME", 
                        "EMPLOYEE ID", "DATE OF BIRTH", "AGE", "GENDER", "EXPERTISE IN", "EXPERIENCE", "FATHER NAME", "EMAIL", "PHONE NUMBER" ); 
                    System.out.println("----------------------------------------------------------------------------------"
                        +"-----------------------------------------------------------------------------------------"); 
                    System.out.format("%17s %17s %15s %10s %10s %10s %10s %17s %17s %20s %13s\n", trainer.getFirstName(), trainer.getLastName(), 
                        trainer.getEmployeeId(), trainer.getDob(), trainer.getAge(), trainer.getGender(), trainer.getExpertiseIn(), 
                        trainer.getExperience(), trainer.getFatherName(), trainer.getEmail(), trainer.getPhoneNumber());
                    break;
                }
            }
            System.out.println("\nASSIGNED TRAINEES:\n");
            System.out.println("-----------------------------------------------------------------------------"
                +"------------------------------------------------------------------------------------------");  
            System.out.printf("%17s %17s %17s %17s %10s %10s %10s %10s %17s %20s %13s\n", "FIRST NAME", "LAST NAME", 
                "EMPLOYEE ID", "DATE OF BIRTH", "AGE", "GENDER", "TRANEE BATCH", "SKILLS", "FATHER NAME", "EMAIL", "PHONE NUMBER" ); 
            System.out.println("------------------------------------------------------------------------------"
                +"-----------------------------------------------------------------------------------------");
 
            for (Trainee trainee: trainees) {
                if (assignedTrainees.contains(trainee.getEmployeeId())) {
                    System.out.format("%17s %17s %17s %17s %10s %10s %10s %10s %17s %20s %13s\n",  trainee.getFirstName(), 
                        trainee.getLastName(),  trainee.getEmployeeId(),  trainee.getDob(),  trainee.getAge(),  trainee.getGender(),  trainee.getTraineeBatch(), 
                        trainee.getSkills(),  trainee.getFatherName(),  trainee.getEmail(),  trainee.getPhoneNumber());
                }  
            }
        }  
    }

    /**
     * <p>
     * This method is used to Delete Trainees to Trainer details
     * </p>
     * 
     */ 
    public void deleteTeam() {
        Map<String, List<String>> assignedTraineesAndTrainer = service.getAssignedTraineesToTrainer();
        System.out.print("Enter the User Id:");
        String userId = scanner.next();
        System.out.print("Enter the Password");
        String password = scanner.next();
        if (userId.equals("ideas") && password.equals("2it")) {
            displayAssignedTraineesToTrainer();
            boolean isUpdated = false;
            System.out.print("Enter the Trainer id of team you wish to delete: ");
            String trainerId = scanner.next(); 
            timeDelay(); 
            if (assignedTraineesAndTrainer.containsKey(trainerId)) {   
                service.deleteTraineesToTrainer(trainerId);
                System.out.print("\nteam of tarainer" + trainerId + "is DELETED");
            } else {
                System.out.print("\nThis trainer doesn't exist try again!");
            }
        } else {
            System.out.print("\ninvalid login id or password");
        }
    }
  
    /**
     * <p>
     * This method is used to validate names while getting input
     * </p>
     * 
     * @parm input is used as attribute to validate the given input
     */ 
     public String validateString(String input) throws MyCustomException{
        int invalidInput = 0;
        int loop = 4;
        String string= null;
        boolean isValid = false;

        for (int index = 0; index <= 4; index++) {
            System.out.println(input);
            string = scanner.next();
            if (input.equals("dob (YYYY-MM-DD):")) {
                isValid = ValidationUtil.validateDob(string);
                try {
                    int age = ValidationUtil.calculateAge(string);
                } catch (DateTimeParseException exception) {
                    isValid = false;        
                } 
            } else if (input.equals("Gender:")) {
                isValid = ValidationUtil.validateGender(string);
            } else if (input.equals("Email Id:")) {
                isValid = ValidationUtil.validate(ValidationUtil.EMAIL_REGEX, string);
            } else if (input.equals("phoneNumber:")) {
                isValid = ValidationUtil.validate(ValidationUtil.PHONE_NUMBER_REGEX, string);
            } else {
                isValid = ValidationUtil.validate(ValidationUtil.NAME_REGEX, string);
            }
            if (isValid) {
                break; 
            }else{
                invalidInput++;
                System.out.println("\nInvalid Input!! you have "+loop--+" more chance");
            }
            if (invalidInput == 5) {
                throw new MyCustomException("Invalid Input Try again!!");
            }
        }
        return string;
    }

    public void timeDelay() {
        try {
           System.out.print("Processing");
           Thread.sleep(500); // Just to give the user a chance to see "hello". 
           System.out.print(".");
           Thread.sleep(500);
           System.out.print(".");
           Thread.sleep(500);
           System.out.print(".");
           Thread.sleep(500);
           System.out.print(".");
           Thread.sleep(500);
           System.out.print("\b\b\b\b\b\b\b\b\b\b\b\b\b\b");
           Thread.sleep(500);
           System.out.print("                                         ");
        } catch (Exception e) {
        }
    }
}  