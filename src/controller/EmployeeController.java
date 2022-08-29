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
                    try {
                        projectManagerPortal();
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
            logger.info("\n\n****choose one option from below from TRAINEE PORTAL****\n 1. Add your details\n"
                +" 2. update your complete details\n 3. update Specific detail\n 4. view your details\n 5. Go Back\n");  
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
 
                case 3:
                   try {
                        updateEmployeeDetail(Constant.TRAINEE);
                    } catch(MyCustomException error) {  
                        logger.error(error.getMessage()); 
                    } 
                    break;

                case 4:
                    try {
                        displayEmployeeDetails(Constant.TRAINEE);
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
                +"\n 1. Add Trainer details\n 2. update your complete details\n 3. Display all trainers\n"
                +" 4. Display all Trainees\n 5. view your details\n 6. delete employee details\n 7. update Specific detail\n 8. Go Back\n");
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
 
                case 3:
                    try {
                        displayEmployeesDetails(Constant.TRAINER);
                    } catch(MyCustomException error) {  
                        logger.error(error.getMessage()); 
                    }                                   
                    break;
 
                case 4:
                    try {
                        displayEmployeesDetails(Constant.TRAINEE);
                    } catch(MyCustomException error) {  
                        logger.error(error.getMessage()); 
                    } 
                    break;

                case 5:
                    try {
                        displayEmployeeDetails(Constant.TRAINER);
                    } catch(MyCustomException error) {  
                        logger.error(error.getMessage()); 
                    }                                   
                    break;
  
                case 6:
                    try {
                        deleteEmployeeDetails(Constant.TRAINER);
                    } catch(MyCustomException error) {  
                        logger.error(error.getMessage()); 
                    } 
                    break;
                 
                case 7:
                   try {
                        updateEmployeeDetail(Constant.TRAINER);
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
 
    public void projectManagerPortal() {
        Scanner scannerInput = new Scanner(System.in);
        boolean isDecession = true;
        do {
            logger.info("\n\n****choose one option from below from PROJECT MANAGER PORTAL****"
                +"\n 1. Add Project Manager details\n 2. update your complete details\n 3. Display all Trainers\n"
                +" 4. Display all Projectmanagers\n 5. view your details\n 6. delete ProjectManager details\n"
                +" 7. update Specific detail\n 8. add projects\n 9. update projects\n 10. Go Back\n");
            int trainerOption = scannerInput.nextInt();
  
            switch (trainerOption) {
                case 1:
                    try {
                        addOrUpdateEmployeeDetails(Constant.ADD, Constant.PROJECT_MANAGER);
                    } catch(MyCustomException error) {  
                        logger.error(error.getMessage()); 
                    } 
                    break;
                
                case 2:
                    try {
                        addOrUpdateEmployeeDetails(Constant.UPDATE, Constant.PROJECT_MANAGER);
                    } catch(MyCustomException error) {  
                        logger.error(error.getMessage()); 
                    } 
                    break;
 
                case 3:
                    try {
                        displayEmployeesDetails(Constant.TRAINER);
                    } catch(MyCustomException error) {  
                        logger.error(error.getMessage()); 
                    }                                   
                    break;
 
                case 4:
                    try {
                        displayEmployeesDetails(Constant.PROJECT_MANAGER);
                    } catch(MyCustomException error) {  
                        logger.error(error.getMessage()); 
                    } 
                    break;

                case 5:
                    try {
                        displayEmployeeDetails(Constant.PROJECT_MANAGER);
                    } catch(MyCustomException error) {  
                        logger.error(error.getMessage()); 
                    }                                   
                    break;
  
                case 6:
                    try {
                        deleteEmployeeDetails(Constant.PROJECT_MANAGER);
                    } catch(MyCustomException error) {  
                        logger.error(error.getMessage()); 
                    } 
                    break;
                 
                case 7:
                   try {
                        updateEmployeeDetail(Constant.PROJECT_MANAGER);
                    } catch(MyCustomException error) {  
                        logger.error(error.getMessage()); 
                    } 
                    break;

                case 8:
                    try {
                        addOrUpdateProjectDetails(Constant.ADD, Constant.PROJECT_MANAGER);
                    } catch(MyCustomException error) {  
                        logger.error(error.getMessage()); 
                    } 
                    break;
 
                case 9:
                    try {
                        addOrUpdateProjectDetails(Constant.UPDATE, Constant.PROJECT_MANAGER);
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
        int failed = 0;
        String add = "add";
        String update = "update";
        String email = null;
        LocalDate dob = null;
        boolean isEmployeeAvailable = false;
        EmployeeDto employeeDto = new EmployeeDto();
        if (operation.equals(update)) {
            System.out.print("**Enter your email and Dob to Login** ");
            email = validateString("Email Id:");
            dob = LocalDate.parse(validateString("dob (YYYY-MM-DD):"));
            isEmployeeAvailable = service.checkIsEmployeeAvailable(email, dob, userType);
        }
        if (operation.equals(add) || isEmployeeAvailable) {
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
                throw new MyCustomException("date parse error!!");
            }
            logger.info("Batch:");
            String batch = scanner.next();
            employeeDto.setBatch(batch);
            employeeDto.setFatherName(validateString("Father Name  :"));
            employeeDto.setEmail(validateString("Email Id:"));
            employeeDto.setPhoneNumber(validateString("phoneNumber:"));  
            timeDelay();
            if (operation.equals(add)) {                    
                if(service.addEmployee(employeeDto, userType) != failed) {
                    logger.info("\n1 row inserted..Employee added Successfully");
                } else {
                    logger.info("\n0 row inserted..process failed");
                }
            } else if(operation.equals(update)) {
                if(service.updateEmployee(employeeDto, email, dob) != failed) {
                    logger.info("\nEmployee updated SUCCESSFULLY");
                } else {
                    logger.info("\nProcess FAILED..invalid email or dob");
                }
            }
    
        } else {
            
            logger.info("invalid email or dob");
        }      
    }

    public void displayEmployeesDetails(String employeeRole) throws MyCustomException{
        List<EmployeeDto> employeeDtos = service.getEmployeesDetails(employeeRole);
        System.out.println("---------------------------------------------------------------------------------"
            +"------------------------------------------------------------------------------------------");  
        System.out.format("%5s %17s %8s %15s %8s %15s %5s %15s %8s %15s %20s %13s %8s\n", "ID", "FIRST_NAME", "LAST_NAME", 
            "DATE_OF_BIRTH", "GENDER", "DATE_OF_JOINING", "BATCH", "DESIGNATION", "CITY", "FATHER_NAME", "EMAIL", "PHONE_NUMBER", "STATUS"); 
        System.out.println("------------------------------------------------------------------------------------"
            +"---------------------------------------------------------------------------------------");
        for (EmployeeDto employeeDto: employeeDtos) {
            System.out.println(employeeDto);  
        } 
    }

    public void displayEmployeeDetails(String userType) throws MyCustomException{
        System.out.print("**Enter your email and Dob to Login** ");
        String email = validateString("Email Id:");
        LocalDate dob = LocalDate.parse(validateString("dob (YYYY-MM-DD):")); 
        if (service.checkIsEmployeeAvailable(email, dob, userType)) {
            EmployeeDto employeeDto = service.getEmployeeDetails(email, dob, userType);
            System.out.println("---------------------------------------------------------------------------------"
                +"------------------------------------------------------------------------------------------");  
            System.out.format("%5s %17s %8s %15s %8s %15s %5s %15s %8s %15s %20s %13s %8s\n", "ID", "FIRST_NAME", "LAST_NAME", 
                "DATE_OF_BIRTH", "GENDER", "DATE_OF_JOINING", "BATCH", "DESIGNATION", "CITY", "FATHER_NAME", "EMAIL", "PHONE_NUMBER", "STATUS"); 
            System.out.println("------------------------------------------------------------------------------------"
                +"---------------------------------------------------------------------------------------");
            System.out.println(employeeDto);  
        } else {            
            logger.info("invalid email or dob");
        }     
    }
 
    public void deleteEmployeeDetails(String userType) throws MyCustomException{
        System.out.print("**Enter your email and Dob to Login** ");
        String email = validateString("Email Id:");
        LocalDate dob = LocalDate.parse(validateString("dob (YYYY-MM-DD):")); 
        if (service.checkIsEmployeeAvailable(email, dob, userType)) {
            System.out.print("Enter the Employee Id you want to delete: ");
            int employeeId = scanner.nextInt();
            if (service.deleteEmployeeById(employeeId) >= 1) {
                logger.info("\n1 row deleted..Employee deleted Successfully");
            } else {
                logger.info("\n0 row deleted..process failed");
            }
        } else {     
            logger.info("invalid email or dob");
        }   
    }
 
    public void updateEmployeeDetail(String userType) throws MyCustomException{
        int isUpdated = 0;
        Scanner scannerInput = new Scanner(System.in);
        System.out.print("**Enter your email and Dob to Login** ");
        String employeeEmail = validateString("Email Id:");
        LocalDate employeeDob = LocalDate.parse(validateString("dob (YYYY-MM-DD):")); 
        if (service.checkIsEmployeeAvailable(employeeEmail, employeeDob, userType)) {
            System.out.print("select the data you want to update\n 1. First Name\n 2. Last Name\n 3. Staff Number\n"
                + " 4. Date Of Birth\n 5. Gender\n 6. Date Of Joining\n 7. Batch\n 8. Designation\n 9. city\n 10.Father Name\n 11. Email\n 12. Phone Number\n");
            int option = scannerInput.nextInt();
            switch(option) {
                case 1:
                    String firstName = validateString("First Name:");
                    isUpdated = service.updateEmployeeDetail("first_name", firstName, employeeEmail, employeeDob);
                    break;
                case 2:
                    String lastName = validateString("Last Name:");
                    isUpdated = service.updateEmployeeDetail("last_name", lastName, employeeEmail, employeeDob); 
                    break;
                case 3:
                    String staffNumber = validateString("Staff Name:");
                    isUpdated = service.updateEmployeeDetail("staff_number", staffNumber, employeeEmail, employeeDob);
                    break;
                case 4:
                    String dob = validateString("dob (YYYY-MM-DD):");
                    isUpdated = service.updateEmployeeDetail("dob", dob, employeeEmail, employeeDob);
                    break;
                case 5:
                    String gender = validateString("Gender:");
                    isUpdated = service.updateEmployeeDetail("gender", gender, employeeEmail, employeeDob);
                    break;
                case 6:
                    String dateOfJoining = validateString("Date of Joining:");
                    isUpdated = service.updateEmployeeDetail("date_of_joining", dateOfJoining, employeeEmail, employeeDob);
                    break;
                case 7:
                    logger.info("Batch:");
                    String batch = scanner.next();
                    isUpdated = service.updateEmployeeDetail("batch", batch, employeeEmail, employeeDob);
                    break;
                case 8:
                    String designation = validateString("Designation:");
                    service.updateEmployeeDetail("designation", designation, employeeEmail, employeeDob);
                    break;
                case 9:
                    String city = validateString("City :");
                    isUpdated = service.updateEmployeeDetail("city", city, employeeEmail, employeeDob);
                   break;
                case 10:
                    String fatherName = validateString("Father Name  :");
                    isUpdated = service.updateEmployeeDetail("father_name", fatherName, employeeEmail, employeeDob);
                    break;
                case 11:
                    String email = validateString("Email Id:");
                    isUpdated = service.updateEmployeeDetail("email", email, employeeEmail, employeeDob);
                    break;
                case 12:
                    String phoneNumber = validateString("phoneNumber:");
                    isUpdated = service.updateEmployeeDetail("phone_number", phoneNumber, employeeEmail, employeeDob);
                    break;
                default:
                    break;
            }    
            if (isUpdated >= 1) {
                logger.info("\nEmployee detail updated SUCCESSFULLY");
            } else {
                logger.info("\nProcess FAILED..");
            }
        } else {
            logger.info("\nInvalid Email or Dob"); 
        }  
    }

    public void addOrUpdateProjectDetails(String operation, String userType) throws MyCustomException {
        int failed = 0;
        String add = "add";
        String update = "update";
        String email = null;
        LocalDate dob = null;
        boolean isEmployeeAvailable = false;
        ProjectDto projectDto = new ProjectDto();
        if (operation.equals(update)) {
            System.out.print("**Enter your email and Dob to Login** ");
            email = validateString("Email Id:");
            dob = LocalDate.parse(validateString("dob (YYYY-MM-DD):"));
            isEmployeeAvailable = service.checkIsEmployeeAvailable(email, dob, userType);
        }
        if (operation.equals(add) || isEmployeeAvailable) {
            System.out.print("Enter the Required Data to **add Project**\n"); 
	    projectDto.setName(validateString("Project Name:"));
	    projectDto.setClientName(validateString("Client Name:"));
            projectDto.setCompanyName(validateString("Company Name:"));
            try {
	        projectDto.setStartDate(LocalDate.parse(validateString("Started Date (YYYY-MM-DD):")));
                projectDto.setStatus(validateString("Status:"));
                projectDto.setEstimatedDuration(validateString("Estimated Duration:"));
                projectDto.setEstimatedBudget(validateString("Estimated Budget :"));
	        projectDto.setTechnologyUsed(validateString("Technology Used:"));
            } catch(Exception error) {
                throw new MyCustomException("date parse error!!");
            }
            timeDelay();
            if (operation.equals(add)) {                    
                if(service.addProject(projectDto) != failed) {
                    logger.info("\n1 row inserted..Employee added Successfully");
                } else {
                    logger.info("\n0 row inserted..process failed");
                }
            } else if(operation.equals(update)) {
                if(service.updateProject(projectDto, email, dob) != failed) {
                    logger.info("\nEmployee updated SUCCESSFULLY");
                } else {
                    logger.info("\nProcess FAILED..invalid email or dob");
                }
            }
    
        } else {
            
            logger.info("invalid email or dob");
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
            if (input.equals("dob (YYYY-MM-DD):") || input.equals("Date of Joining:") || input.equals("Started Date (YYYY-MM-DD):")) {
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