/*
 * Copyright (c) 2022, ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */
package com.ideas2it.controller;


import com.ideas2it.exception.CustomException;
import com.ideas2it.dto.EmployeeDto;
import com.ideas2it.dto.EmployeeProjectDto;
import com.ideas2it.dto.ProjectDto;
import com.ideas2it.service.Service;
import com.ideas2it.utils.ValidationUtil;
import com.ideas2it.utils.Constant;
import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;

import java.time.LocalDate;  
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
            logger.info("\n\nChoose one option from below\n 1. Trainee Portal \n 2. Trainer Portal\n 3. Project manager Portal\n 4. exit\n ");  
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

                case 3:
                    
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
            logger.info("\n\n****choose one option from below from TRAINEE PORTAL****\n 1. Add your details\n"
                +" 2. update your complete details\n 3. update Specific detail\n 4. view your details\n 5. Go Back\n");  
            int operation = scannerInput.nextInt(); 
            switch (operation) {
	        case 1:
                    try {
                        addOrUpdateEmployeeDetails(Constant.ADD, Constant.TRAINEE);
                    } catch(CustomException exception) {  
                        logger.error(exception.getMessage()); 
                    } 
                    break;

                case 2:
                    try {
                        addOrUpdateEmployeeDetails(Constant.UPDATE, Constant.TRAINEE);
                    } catch(CustomException exception) {  
                        logger.error(exception.getMessage()); 
                    } 
                    break;
 
                case 3:
                   
                    break;

                case 4:
                    try {
                        displayEmployeeDetailsById();
                    } catch(CustomException exception) {  
                        logger.error(exception.getMessage()); 
                    } 
                    
                                                                   
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
                +"\n 1. Add Trainer details\n 2. update your complete details\n 3. Display all trainers\n"
                +" 4. Display all Trainees\n 5. view your details\n 6. delete employee details\n 7. Display All Employees\n 8. Go Back\n");
            int trainerOption = scannerInput.nextInt();
  
            switch (trainerOption) {
                case 1:
                    try {
                        addOrUpdateEmployeeDetails(Constant.ADD, Constant.TRAINER);
                    } catch(CustomException exception) {  
                        logger.error(exception.getMessage()); 
                    } 
                    break;
                
                case 2:
                    try {
                        addOrUpdateEmployeeDetails(Constant.UPDATE, Constant.TRAINER);
                    } catch(CustomException error) {  
                        logger.error(error.getMessage()); 
                    } 
                    break;
 
                case 3:
                    try {
                        displayEmployeesDetailsByRoleName(Constant.TRAINER);
                    } catch(CustomException exception) {  
                        logger.error(exception.getMessage()); 
                    }                                   
                    break;
 
                case 4:
                    try {
                       displayEmployeesDetailsByRoleName(Constant.TRAINEE);
                    } catch(CustomException exception) {  
                        logger.error(exception.getMessage()); 
                    } 
                    break;

                case 5:
                    try {
                         displayEmployeeDetailsById();
                    } catch(CustomException exception) {  
                        logger.error(exception.getMessage()); 
                    } 
                   
                    break;
                case 6:
                   try {
                        deleteEmployee();
                    } catch(CustomException exception) {  
                        logger.error(exception.getMessage()); 
                    } 
                   
                    break;
                 
                case 7:
                    try {    
                        displayEmployeesDetails();
                    } catch(CustomException exception) {  
                        logger.error(exception.getMessage()); 
                    } 
                    break;
                 
                default:
                    System.out.print("Thank you");
                    isDecession = false;
            }
        } while(isDecession);
    }
 
    public void projectManagerPortal() {
        Scanner scannerInput = new Scanner(System.in);
        boolean isDecession = true;
        do {
            logger.info("\n\n****choose one option from below from PROJECT MANAGER PORTAL****"
                +"\n 1. Add Project Manager details\n 2. update your complete details\n 3. Display all Trainers\n"
                +" 4. Display all Projectmanagers\n 5. view your details\n 6. delete ProjectManager details\n"
                +" 7. update Specific detail\n 8. Manage projects\n 9. Go Back\n");
            int trainerOption = scannerInput.nextInt();
  
            switch (trainerOption) {
                case 1:
                    try {
                        addOrUpdateEmployeeDetails(Constant.ADD, Constant.PROJECT_MANAGER);
                    } catch(CustomException exception) {  
                        logger.error(exception.getMessage()); 
                    } 
                    break;
                
                case 2:
                    try {
                        addOrUpdateEmployeeDetails(Constant.UPDATE, Constant.PROJECT_MANAGER);
                    } catch(CustomException exception) {  
                        logger.error(exception.getMessage()); 
                    } 
                    break;
 
                case 3:
                    try {
                        displayEmployeesDetailsByRoleName(Constant.TRAINER);
                    } catch(CustomException exception) {  
                        logger.error(exception.getMessage()); 
                    }                                   
                    break; 
                case 4:
                    try {
                        displayEmployeesDetailsByRoleName(Constant.PROJECT_MANAGER);
                    } catch(CustomException exception) {  
                        logger.error(exception.getMessage()); 
                    }       
                    break;
                case 5:
                    try {
                         displayEmployeeDetailsById();
                    } catch(CustomException exception) {  
                        logger.error(exception.getMessage()); 
                    } 
                                                  
                    break;  
                case 6:
                    try {
                        deleteEmployee();
                    } catch(CustomException exception) {  
                        logger.error(exception.getMessage()); 
                    } 
                    break;                 
                case 7:
                   
                    break;
                case 8:
                    
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
    public void addOrUpdateEmployeeDetails(String operation, String userType) throws CustomException {
        Scanner scannerInput = new Scanner(System.in);
        int failed = 0;
        int employeeId = 0;
        String add = "add";
        String update = "update";
        String email = null;
        LocalDate dob = null;
        String roleName = null;
        boolean isEmployeeAvailable = false;
        EmployeeDto employeeDto = new EmployeeDto();
        if (operation.equals(update)) {
            System.out.print("**Enter your employeeeId** ");
            employeeId = scannerInput.nextInt();
            employeeDto.setId(employeeId);
            roleName = validateString("enter the employee role you want to update:");
        }
        if (operation.equals(add) || operation.equals(update)) {
            System.out.print("Enter the Required Data to **SignUP**\n"); 
	    employeeDto.setFirstName(validateString("First Name:"));
	    employeeDto.setLastName(validateString("Last Name:"));
            try {
	        employeeDto.setDob(LocalDate.parse(validateString("dob (YYYY-MM-DD):")));
                employeeDto.setGender(validateString("Gender:"));
                employeeDto.setDesignation(validateString("Designation:"));
                employeeDto.setCity(validateString("City :"));
	        employeeDto.setDateOfJoining(LocalDate.parse(validateString("Date of Joining:")));
            } catch(Exception error) {
                throw new CustomException("date parse error!!");
            }
            employeeDto.setBatch(validateString("Batch:"));
            employeeDto.setFatherName(validateString("Father Name  :"));
            employeeDto.setEmail(validateString("Email Id:"));
            employeeDto.setPhoneNumber(validateString("phoneNumber:")); 
            employeeDto.setStatus("active"); 
            timeDelay();
            if (operation.equals(add)) {                    
                if(service.addEmployee(employeeDto, userType)) {
                    logger.info("\n1 row inserted..Employee added Successfully");
                } else {
                    logger.info("\n0 row inserted..process failed");
                }
            } else if(operation.equals(update)) {
                if(service.updateEmployee(employeeDto, roleName)) {
                    logger.info("\nEmployee updated SUCCESSFULLY");
                } else {
                    logger.info("\nProcess FAILED..invalid email or dob");
                }
            }
    
        } else { 
            logger.info("invalid email or dob");
        }      
    }

    public void displayEmployeesDetails() throws CustomException {
        List<EmployeeDto> employeeDtos = service.getEmployeesDetails();
        System.out.println("---------------------------------------------------------------------------------"
            +"------------------------------------------------------------------------------------------");  
        System.out.format("%5s %15s %8s %15s %8s %15s %5s %15s %8s %15s %25s %13s %8s %15s\n", "ID", "FIRST_NAME", "LAST_NAME", 
            "DATE_OF_BIRTH", "GENDER", "DATE_OF_JOINING", "BATCH", "DESIGNATION", "CITY", "FATHER_NAME", "EMAIL", "PHONE_NUMBER", "STATUS", "ROLE"); 
        System.out.println("------------------------------------------------------------------------------------"
            +"---------------------------------------------------------------------------------------");
        for (EmployeeDto employeeDto: employeeDtos) {
            System.out.println(employeeDto);  
        } 
    }

    public void displayEmployeesDetailsByRoleName(String roleName) throws CustomException {
        List<EmployeeDto> employeeDtos = service.getEmployeesDetailsByRoleName(roleName);
        System.out.println("---------------------------------------------------------------------------------"
            +"------------------------------------------------------------------------------------------");  
        System.out.format("%5s %15s %8s %15s %8s %15s %5s %15s %8s %15s %25s %13s %8s %15s\n", "ID", "FIRST_NAME", "LAST_NAME", 
            "DATE_OF_BIRTH", "GENDER", "DATE_OF_JOINING", "BATCH", "DESIGNATION", "CITY", "FATHER_NAME", "EMAIL", "PHONE_NUMBER", "STATUS", "ROLE"); 
        System.out.println("------------------------------------------------------------------------------------"
            +"---------------------------------------------------------------------------------------");
        for (EmployeeDto employeeDto: employeeDtos) {
            System.out.println(employeeDto);  
        } 
    }

    public void displayEmployeeDetailsById() throws CustomException {
        Scanner scannerInput = new Scanner(System.in);
        System.out.print("**Enter your employeeeId** ");
        int employeeId = scannerInput.nextInt();
        EmployeeDto employeeDto = service.getEmployeeDetailsById(employeeId);
        System.out.println("---------------------------------------------------------------------------------"
            +"------------------------------------------------------------------------------------------");  
        System.out.format("%5s %15s %8s %15s %8s %15s %5s %15s %8s %15s %25s %13s %8s %15s\n", "ID", "FIRST_NAME", "LAST_NAME", 
            "DATE_OF_BIRTH", "GENDER", "DATE_OF_JOINING", "BATCH", "DESIGNATION", "CITY", "FATHER_NAME", "EMAIL", "PHONE_NUMBER", "STATUS", "ROLE"); 
        System.out.println("------------------------------------------------------------------------------------"
            +"---------------------------------------------------------------------------------------");
        System.out.println(employeeDto);         
    }    



    public void deleteEmployee() throws CustomException {
        int failed = 0;
        Scanner scannerInput = new Scanner(System.in);
        System.out.print("**Enter your employeeeId you want to delete** ");
        int employeeId = scannerInput.nextInt();
        if (service.deleteEmployee(employeeId) != failed) {
            logger.info("\nEmployee Deleted SUCCESSFULLY");
        } else {
            logger.info("\nProcess FAILED..invalid email or dob");
        }  
    }
    
    /**
     * <p>
     * This method is used to validate names while getting input
     * </p>
     * 
     * @parm input is used as attribute to validate the given input
     */ 
     public String validateString(String input) throws CustomException{
        int invalidInput = 0;
        int loop = 4;
        String string= null;
        boolean isValid = false;

        for (int index = 0; index <= 4; index++) {
            logger.info(input);
            if (input.equals("dob (YYYY-MM-DD):") || input.equals("Date of Joining:") || input.equals("Started Date (YYYY-MM-DD):") || input.equals("Assigned Date (YYYY-MM-DD):") || input.equals("Relieved Date (YYYY-MM-DD):")) {
                string = scanner.next();
                isValid = ValidationUtil.validateDob(string);
                try {
                    int age = ValidationUtil.calculateAge(string);
                } catch (DateTimeParseException exception) {
                    isValid = false;        
                } 
            } else if (input.equals("Gender:")) {
                string = scanner.next();
                isValid = ValidationUtil.validateGender(string);
            } else if (input.equals("Batch:") || input.equals("Estimated Duration (in month):") ) {
               string = scanner.next();
               isValid = ValidationUtil.validate(ValidationUtil.AMOUNT_REGEX, string);
            } else if (input.equals("Email Id:")) {
                string = scanner.next();
                isValid = ValidationUtil.validate(ValidationUtil.EMAIL_REGEX, string);
            } else if (input.equals("phoneNumber:")) {
                string = scanner.next();
                isValid = ValidationUtil.validate(ValidationUtil.PHONE_NUMBER_REGEX, string);
            } else {
                string = scanner.next();
                isValid = ValidationUtil.validate(ValidationUtil.NAME_REGEX, string);
            }
            if (isValid) {
                break; 
            }else{
                invalidInput++;
                logger.info("\nInvalid Input!! you have "+loop--+" more chance");
            }
            if (invalidInput == 5) {
                throw new CustomException("Invalid Input Try again!!");
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