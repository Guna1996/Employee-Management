/*
 * Copyright (c) 2022, ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */
package com.ideas2it.controller;


import com.ideas2it.exception.MyCustomException;
import com.ideas2it.dto.EmployeeDto;
import com.ideas2it.service.Service;
import com.ideas2it.utils.ValidationUtil;
import com.ideas2it.utils.Constant;
import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


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
    static Logger logger = Logger.getLogger(EmployeeController.class);
 
    public static void main(String[] args) {
        EmployeeController employeeController = new EmployeeController();
        BasicConfigurator.configure();
        do {
            try {
                employeeController.startOperation();
            } catch(InputMismatchException error) {  
                logger.error(error+" Error!! please Enter valid input");
            } 
        } while (isContinue);      
    }
    
    
    /**
     * <p>
     * This the concrete method which perform Trainer and trainee operations
     * </p>
     * 
     * 
     */    
    public void startOperation() {
        Scanner scannerInput = new Scanner(System.in);
        while (isContinue) {
            logger.info("\n\nChoose one option from below\n 1. Trainee Portal \n 2. Trainer Portal\n 3. exit\n ");  
            int choice = scannerInput.nextInt();
            switch (choice) {
                case 1:
                    try {
                        traineePortal();
                    } catch(InputMismatchException error) {  
                        logger.error(error+" Error!! invalid input");
                    }   
          	    break;

                case 2:
                    try {
                        trainerPortal();
                    } catch(InputMismatchException error) {  
                        logger.error(error+" Error!! invalid input");
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
    public void traineePortal() {
        Scanner scannerInput = new Scanner(System.in);
        boolean isChoice = true;
        do {
            logger.info("\n\n****choose one option from below from TRAINEE PORTAL****\n 1. Add your data\n"
                +" 2. update your complete details\n 3. Go Back\n");  
            int operation = scannerInput.nextInt(); 
            switch (operation) {
	        case 1:
                    try {
                        addOrUpdateEmployeeDetails(Constant.ADD, Constant.TRAINEE);
                    } catch(MyCustomException error) {  
                        logger.error(error.getMessage()); 
                    } 
                    break;

                case 2:
                    try {
                        addOrUpdateEmployeeDetails(Constant.UPDATE, Constant.TRAINEE);
                    } catch(MyCustomException error) {  
                        logger.error(error.getMessage()); 
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
    public void trainerPortal() {
        Scanner scannerInput = new Scanner(System.in);
        boolean isDecession = true;
        do {
            logger.info("\n\n****choose one option from below from TRAINER PORTAL****"
                +"\n 1. Add Trainer data\n 2. update your complete details\n 3. Display all trainers\n 4. Display all Trainees\n 5. Go Back\n");
            int trainerOption = scannerInput.nextInt();
  
            switch (trainerOption) {
                case 1:
                    try {
                        addOrUpdateEmployeeDetails(Constant.ADD, Constant.TRAINER);
                    } catch(MyCustomException error) {  
                        logger.error(error.getMessage()); 
                    } 
                    break;
                
                case 2:
                    try {
                        addOrUpdateEmployeeDetails(Constant.UPDATE, Constant.TRAINER);
                    } catch(MyCustomException error) {  
                        logger.error(error.getMessage()); 
                    } 
                    break;
 
                case 3 :
                    try {
                        displayEmployeeDetails(Constant.TRAINER);
                    } catch(MyCustomException error) {  
                        logger.error(error.getMessage()); 
                    }                                   
                    break;
 
                case 4 :
                    try {
                        displayEmployeeDetails(Constant.TRAINEE);
                    } catch(MyCustomException error) {  
                        logger.error(error.getMessage()); 
                    } 
                    break;
 
                default:
                    System.out.print("Thank you");
                    isDecession = false;
            }
        } while(isDecession);
    }
 
   
    /**
     * <p>
     * This method is used to add or update Employee details
     * </p>
     * 
     */ 
    public void addOrUpdateEmployeeDetails(String operation, String userType) throws MyCustomException {
        String trainee = "trainee";
        String trainer = "trainer";
        String add = "add";
        String update = "update";
        String email = null , dob = null;
        EmployeeDto employeeDto = new EmployeeDto();
        if (operation.equals(update)) {
            System.out.print("**Enter Trainer email and Dob to Login** ");
            email = validateString("Email Id:");
            dob = validateString("dob (YYYY-MM-DD):");
        }
        if (operation.equals(add) || operation.equals(update)) {
            System.out.print("Enter the Required Data to **SignUP**\n"); 
            try { 
	        employeeDto.setFirstName(validateString("First Name:"));
	        employeeDto.setLastName(validateString("Last Name:"));
	        employeeDto.setDob(validateString("dob (YYYY-MM-DD):"));
                employeeDto.setGender(validateString("Gender:"));
                employeeDto.setDesignation(validateString("Designation:"));
                employeeDto.setCity(validateString("City :"));
	        employeeDto.setDateOfJoining(validateString("Date of Joining:"));
                employeeDto.setBatch(validateString("Batch:"));
                employeeDto.setFatherName(validateString("Father Name  :"));
                employeeDto.setEmail(validateString("Email Id:"));
                employeeDto.setPhoneNumber(validateString("phoneNumber:"));
                logger.info("StaffNumber Generated");  
                employeeDto.setStaffNumber(ValidationUtil.generateStaffNumber());
                timeDelay();
                if (operation.equals(add)) {                    
                    if(service.addEmployee(employeeDto, userType)) {
                        logger.info("\nEmployee Added SUCCESSFULLY");
                    } else {
                        logger.info("\nProcess FAILED");
                    }
                } else if(operation.equals(update)) {
                    if(service.updateEmployee(employeeDto, email, dob)) {
                        logger.info("\nEmployee Added SUCCESSFULLY");
                    } else {
                        logger.info("\nProcess FAILED");
                    }
                }
            } catch (MyCustomException exception) {
                throw new MyCustomException(exception.getMessage());
            }     
        } else {
            
            logger.info("invalid email or dob");
        }      
    }

    public void displayEmployeeDetails(String employeeRole) throws MyCustomException{
        List<EmployeeDto> employeesDto = service.getEmployeeDetails(employeeRole);
        System.out.println("---------------------------------------------------------------------------------"
            +"------------------------------------------------------------------------------------------");  
        System.out.format("%17s %8s %8s %15s %8s %15s %5s %15s %8s %15s %20s %13s\n", "FIRST_NAME", "LAST_NAME", "STAFF_NUMBER", 
            "DATE_OF_BIRTH", "GENDER", "DATE_OF_JOINING", "BATCH", "DESIGNATION", "CITY", "FATHER_NAME", "EMAIL", "PHONE_NUMBER" ); 
        System.out.println("------------------------------------------------------------------------------------"
            +"---------------------------------------------------------------------------------------");
        for (EmployeeDto employeeDto: employeesDto) {
            System.out.println(employeeDto);  
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
            logger.info(input);
            string = scanner.next();
            if (input.equals("dob (YYYY-MM-DD):") || input.equals("Date of Joining:")) {
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
            } else if (input.equals("phoneNumber:") || input.equals("Batch:")) {
                isValid = ValidationUtil.validate(ValidationUtil.PHONE_NUMBER_REGEX, string);
            } else {
                isValid = ValidationUtil.validate(ValidationUtil.NAME_REGEX, string);
            }
            if (isValid) {
                break; 
            }else{
                invalidInput++;
                logger.info("\nInvalid Input!! you have "+loop--+" more chance");
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