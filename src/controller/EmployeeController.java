/*
 * Copyright (c) 2022, ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */
package com.ideas2it.controller;

import com.ideas2it.dto.EmployeeDto;
import com.ideas2it.dto.EmployeeProjectDto;
import com.ideas2it.dto.ProjectDto;
import com.ideas2it.exception.CustomException;
import com.ideas2it.service.EmployeeService;
import com.ideas2it.service.EmployeeProjectService;
import com.ideas2it.service.ProjectService;
import com.ideas2it.utils.Constant;
import com.ideas2it.utils.ValidationUtil;

import java.time.format.DateTimeParseException;
import java.time.LocalDate;  
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

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
    private EmployeeService employeeService = new EmployeeService();
    private EmployeeProjectService employeeProjectService = new EmployeeProjectService();
    private ProjectService projectService = new ProjectService();
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

                case 4:
                    
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
                    try {
                        manageProjects();
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
   
     public void manageProjects() throws CustomException{
        Scanner scannerInput = new Scanner(System.in);
        boolean isChoice = true;
        do {
            logger.info("\n\nChoose one option from below\n 1. Add projects \n 2. Update projects\n 3. Display projects\n 4. update specific detail of a project\n 5. assign projects to employees\n 6. display assigned projects to employees\n 7. delete assigned employees to project\n 8. Go back\n ");  
            int choice = scannerInput.nextInt();
            switch (choice) {
                case 1:
                    try {
                        addOrUpdateProjectDetails(Constant.ADD, Constant.PROJECT_MANAGER);
                    } catch( CustomException error) {  
                        logger.error(error.getMessage()); 
                    } 
                    break;
                case 2:
                    try {
                        addOrUpdateProjectDetails(Constant.UPDATE, Constant.PROJECT_MANAGER);
                    } catch( CustomException error) {  
                        logger.error(error.getMessage()); 
                    } 
                    break;
                case 3:
                    try {
                        displayProjects();
                    } catch( CustomException error) {  
                        logger.error(error+" Error!! invalid input");
                    }  
                    break; 
                case 4:
                    try {
                        updateProjectDetail(Constant.PROJECT_MANAGER);
                    } catch( CustomException error) {  
                        logger.error(error+" Error!! invalid input");
                    }  
                    break; 
                 case 5:
                    try {
                        assignProjectToEmployees();
                    } catch( CustomException error) {  
                        logger.error(error+" Error!! invalid input");
                    }  
                    break;   
                case 6:
                    
                    break;
                case 7:
                     
                    break;              
                default:
                        System.out.print("Thank you");
                        isChoice = false;
                        break;
            }          
        }while (isChoice);               
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
        EmployeeDto employeeDto = new EmployeeDto();
        if (operation.equals(update)) {
            System.out.print("**Enter your employeeeId** ");
            employeeId = scannerInput.nextInt();
            employeeDto.setId(employeeId);
            roleName = validateString("enter the employee role you want to update:");
        }
        if (operation.equals(add) || employeeService.isEmployeeAvailable(employeeId)) {
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
                int generatedEmployeeId = employeeService.addEmployee(employeeDto, userType);                  
                if (generatedEmployeeId != failed) {
                    logger.info("\n1 row inserted..Employee added Successfully");
                } else {
                    logger.info("\n0 row inserted..process failed");
                }
            } else if (operation.equals(update)) {
                String result = employeeService.updateEmployee(employeeDto, roleName);
                if (result != null) {
                    logger.info(result);
                } else {
                    logger.info("\nProcess FAILED..invalid employee id");
                }
            }
    
        } else { 
            logger.info("employeeId not found");
        }      
    }

    public void displayEmployeesDetails() throws CustomException {
        List<EmployeeDto> employeeDtos = employeeService.getEmployeesDetails();
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
        List<EmployeeDto> employeeDtos = employeeService.getEmployeesDetailsByRoleName(roleName);
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
        if (employeeService.isEmployeeAvailable(employeeId)) {
            EmployeeDto employeeDto = employeeService.getEmployeeDetailsById(employeeId);
            System.out.println("---------------------------------------------------------------------------------"
                +"------------------------------------------------------------------------------------------");  
            System.out.format("%5s %15s %8s %15s %8s %15s %5s %15s %8s %15s %25s %13s %8s %15s\n", "ID", "FIRST_NAME", "LAST_NAME", 
                "DATE_OF_BIRTH", "GENDER", "DATE_OF_JOINING", "BATCH", "DESIGNATION", "CITY", "FATHER_NAME", "EMAIL", "PHONE_NUMBER", "STATUS", "ROLE"); 
            System.out.println("------------------------------------------------------------------------------------"
                +"---------------------------------------------------------------------------------------");
            System.out.println(employeeDto);  
        } else { 
            logger.info("employeeId not found");
        }        
    }     



    public void deleteEmployee() throws CustomException {
        int failed = 0;
        Scanner scannerInput = new Scanner(System.in);
        System.out.print("**Enter your employeeeId you want to delete** ");
        int employeeId = scannerInput.nextInt();
        if (employeeService.isEmployeeAvailable(employeeId)) {
            String isDeleted = employeeService.deleteEmployee(employeeId);
            if (isDeleted != null) {
                logger.info(isDeleted);
            } else {
                logger.info("\nProcess FAILED..invalid employee id");
            }
        } else { 
            logger.info("employeeId not found");
        }     
    }
   
    public void addOrUpdateProjectDetails(String operation, String userType) throws  CustomException {
        int failed = 0;
        String add = "add";
        String update = "update";
        String email = null;
        LocalDate dob = null;
        int projectId = 0;
        int employeeId = 0;
        Scanner scannerInput = new Scanner(System.in);
        ProjectDto projectDto = new ProjectDto();
        if (operation.equals(update)) {
            System.out.print("**Enter your employeeeId** ");
            employeeId = scannerInput.nextInt();
            System.out.print("**Enter the Project Id you want to update:");
            projectId = scannerInput.nextInt();
            projectDto.setId(projectId);            
        }
        if (operation.equals(add) || (employeeService.isEmployeeAvailable(employeeId) && projectService.isProjectAvailable(projectId))) {
            System.out.print("Enter the Required Data to **add Project**\n"); 
	    projectDto.setName(validateString("Project Name:"));
	    projectDto.setClientName(validateString("Client Name:"));
            projectDto.setCompanyName(validateString("Company Name:"));
            try {
	        projectDto.setStartDate(LocalDate.parse(validateString("Started Date (YYYY-MM-DD):")));
                projectDto.setStatus(validateString("Status:"));
                projectDto.setEstimatedDuration(validateString("Estimated Duration (in month):"));
                projectDto.setDescription(validateString("Description :"));
	        projectDto.setTechnologyUsed(validateString("Technology Used:"));
            } catch(Exception error) {
                throw new  CustomException("date parse error!!");
            }
            timeDelay();
            if (operation.equals(add)) {                    
                if(projectService.addProject(projectDto) != failed) {
                    logger.info("\n1 row inserted..Project added Successfully");
                } else {
                    logger.info("\n0 row inserted..process failed");
                }
            } else if(operation.equals(update)) {
                if(projectService.updateProject(projectDto) != null) {
                    logger.info("\nProject updated SUCCESSFULLY");
                } else {
                    logger.info("\nProcess FAILED..invalid employee id or project id ");
                }
            }
    
        } else {
            
            logger.info("invalid employeeId or projectId");
        }      
    }

    public void displayProjects() throws  CustomException{
        List<ProjectDto> projectDtos = projectService.getProjectsDetails();
        System.out.println("---------------------------------------------------------------------------------"
            +"------------------------------------------------------------------------------------------");  
        System.out.format(" %17s %17s %17s %17s %17s %17s %17s %17s %17s\n", "ID", "NAME", "CLIENT_NAME", 
            "COMPANY_NAME", "START DATE", "ESTIMATED_DURATION", "ESTIMATED_BUDGET", "TECHNOLOGY_USED", "STATUS"); 
        System.out.println("------------------------------------------------------------------------------------"
            +"---------------------------------------------------------------------------------------");
        for (ProjectDto projectDto: projectDtos) {
            System.out.println(projectDto);  
        } 
    }

    public void updateProjectDetail(String userType) throws  CustomException{
        int isUpdated = 0;
        Scanner scannerInput = new Scanner(System.in);
        System.out.print("**Enter your employeeeId** ");
        int employeeId = scannerInput.nextInt();
        if (employeeService.isEmployeeAvailable(employeeId)) {
            System.out.print("**Enter the Project Id you want to update:"); 
            int projectId = scannerInput.nextInt();
            System.out.print("select the data you want to update\n 1.Name\n 2.Client Name\n 3. Company Name\n"
                + " 4. Estimated Duration\n 5. Description\n 6. Technology Used\n 7. Status\n");
            int option = scannerInput.nextInt();
            switch(option) {
                case 1:
                    String name = validateString("Name:");
                    isUpdated = projectService.updateProjectDetail("name", name, projectId);
                    break;
                case 2:
                    String clientName = validateString("Client Name:");
                    isUpdated = projectService.updateProjectDetail("client_name", clientName, projectId); 
                    break;
                case 3:
                    String CompanyName = validateString("Company Name:");
                    isUpdated = projectService.updateProjectDetail("company_name", CompanyName, projectId); 
                    break;
                case 4:
                    String estimatedDuration = validateString("Estimated Duration (in month):");
                    isUpdated = projectService.updateProjectDetail("estimated_duration", estimatedDuration, projectId);
                    break;
                case 5:
                    String estimatedBudget = validateString("Description:");
                    projectService.updateProjectDetail("estimated_budget", estimatedBudget, projectId);
                    break;
                case 6:
                    String technologyUsed = validateString("Technology Used :");
                    isUpdated = projectService.updateProjectDetail("technology_used", technologyUsed, projectId);
                   break;
                case 7:
                    String Status = validateString("Status  :");
                    isUpdated = projectService.updateProjectDetail("status", Status, projectId);
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

    public void deleteProject() throws CustomException {
        int failed = 0;
        Scanner scannerInput = new Scanner(System.in);
        System.out.print("**Enter your projectId you want to delete** ");
        int projectId = scannerInput.nextInt();
        if (projectService.isProjectAvailable(projectId)) {
            String isDeleted = projectService.deleteProject(projectId);
            if (isDeleted != null) {
                logger.info(isDeleted);
            } else {
                logger.info("\nProcess FAILED..invalid project id");
            }
        } else { 
            logger.info("projectId not found");
        }     
    }
    
    public void assignProjectToEmployees() throws CustomException{
        List<EmployeeProjectDto> assignedEmployeesToProjectDto = new ArrayList<EmployeeProjectDto>();
        Scanner scannerInput = new Scanner(System.in);
        System.out.print("**Enter your employeeeId** ");
        int employeeId = scannerInput.nextInt();
        if (employeeService.isEmployeeAvailable(employeeId)) {
            System.out.print("Enter the Project Id: ");
            int projectId = scannerInput.nextInt();  
         // if (!assignedProjectsToEmployees.containsKey(projectId)) {   
                System.out.print("Enter the number of Employees you want to Assign: ");
                int number = scanner.nextInt();
                for (int index=0; index<number;index++) {
                    EmployeeProjectDto employeeProjectDto = new EmployeeProjectDto();
                    System.out.print("Enter the Employee Id: ");
                    int assignedEmployeeId = scanner.nextInt();
                    employeeProjectDto.setEmployeeId(assignedEmployeeId);
                    employeeProjectDto.setProjectId(projectId);
                    employeeProjectDto.setAssignedDate(LocalDate.parse(validateString("Assigned Date (YYYY-MM-DD):")));
                    employeeProjectDto.setEmployeeRole(validateString("Employee Role :"));
                    employeeProjectDto.setRelievedDate(LocalDate.parse(validateString("Relieved Date (YYYY-MM-DD):")));                  
                    assignedEmployeesToProjectDto.add(employeeProjectDto);
                }
                if (true) {      
                    employeeProjectService.assignProjectToEmployees(assignedEmployeesToProjectDto);
                    System.out.print("Assigned Successfully");
                } else {
                    System.out.print("invalid employee ID!!");
                }
         // } else {
         //     System.out.print("This Employee already assigned try again!");
         // }
        } else {
            System.out.print("invalid login id or password");
        }
    }  

    /* public void displayAssignedProjectsToEmployees() throws CustomException{
        List<EmployeeProjectDto> assignedEmployeesDto = service.getAssignedProjectsToEmployees();
        System.out.println("---------------------------------------------------------------------------------"
            +"------------------------------------------------------------------------------------------");  
        System.out.format("%20s %20s %20s %20s %20s %20s %20s %20s\n", "ID", "EMPLOYEE ID", "PROJECT ID", 
            "ASSIGNED ON", "ASSIGNED BY", "STATUS", "EMPLOYEE ROLE", "RELIEVED DATE"); 
        System.out.println("------------------------------------------------------------------------------------"
            +"---------------------------------------------------------------------------------------");
        for (EmployeeProjectDto employeeProjectDto: assignedEmployeesDto) {
            System.out.println(employeeProjectDto);  
        } 
    }  

    public void deleteAssignedEmployeeToProject(String userType) throws CustomException{
        int failed = 0;
        System.out.print("**Enter your email and Dob to Login** ");
        String employeeEmail = validateString("Email Id:");
        LocalDate employeeDob = LocalDate.parse(validateString("dob (YYYY-MM-DD):")); 
        if (service.checkIsEmployeeAvailable(employeeEmail, employeeDob, Constant.PROJECT_MANAGER)) {
            System.out.print("Enter the Employee Id you want to delete: ");
            int employeeId = scanner.nextInt();
            System.out.print("Enter the Project Id you want to delete: ");
            int projectId = scanner.nextInt();
            if (service.deleteAssignedEmployeeToProjectById(employeeId, projectId) != failed) {
                logger.info("\n1 row deleted..Employee deleted Successfully");
            } else {
                logger.info("\n0 row deleted..process failed");
            }
        } else {     
            logger.info("invalid email or dob");
        }   
    }      
      */
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
           Thread.sleep(500);  
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