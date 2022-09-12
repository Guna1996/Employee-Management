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
import com.ideas2it.utils.Constants;
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
     * This the concrete method which perform Trainer and Trainee operations
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
     * This method is used perform trainee operations
     * </p>
     * 
     */ 
    public void traineePortal() {
        Scanner scannerInput = new Scanner(System.in);
        boolean isChoice = true;
        do {
            logger.info("\n\n****choose one option from below from TRAINEE PORTAL****\n 1. Add your details\n"
                +" 2. update your complete details\n 3. display your projects\n 4. view your details\n  5. Go Back\n");  
            int operation = scannerInput.nextInt(); 
            switch (operation) {
	        case 1:
                    try {
                        addOrUpdateEmployeeDetails(Constants.ADD, Constants.TRAINEE);
                    } catch(CustomException exception) {  
                        logger.error(exception.getMessage()); 
                    } 
                    break;

                case 2:
                    try {
                        addOrUpdateEmployeeDetails(Constants.UPDATE, Constants.TRAINEE);
                    } catch(CustomException exception) {  
                        logger.error(exception.getMessage()); 
                    } 
                    break;
 
                case 3:
                    try {
                        displayProjectsAssignedToEmployee(); 
                    } catch(CustomException exception) {  
                        logger.error(exception.getMessage()); 
                    } 
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
                +" 4. Display all Trainees\n 5. view your details\n 6. delete employee details\n 7. Display All Employees\n 8. Display projects assigned to you\n 9. Go Back\n");
            int trainerOption = scannerInput.nextInt();
  
            switch (trainerOption) {
                case 1:
                    try {
                        addOrUpdateEmployeeDetails(Constants.ADD, Constants.TRAINER);
                    } catch(CustomException exception) {  
                        logger.error(exception.getMessage()); 
                    } 
                    break;
                
                case 2:
                    try {
                        addOrUpdateEmployeeDetails(Constants.UPDATE, Constants.TRAINER);
                    } catch(CustomException error) {  
                        logger.error(error.getMessage()); 
                    } 
                    break;
 
                case 3:
                    try {
                        displayEmployeesDetailsByRoleName(Constants.TRAINER);
                    } catch(CustomException exception) {  
                        logger.error(exception.getMessage()); 
                    }                                   
                    break;
 
                case 4:
                    try {
                       displayEmployeesDetailsByRoleName(Constants.TRAINEE);
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

                case 8:
                    try {
                        displayProjectsAssignedToEmployee(); 
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
                +" 7. Display projects assigned to a particular employee\n 8. Manage projects\n 9. Go Back\n");
            int trainerOption = scannerInput.nextInt();
  
            switch (trainerOption) {
                case 1:
                    try {
                        addOrUpdateEmployeeDetails(Constants.ADD, Constants.PROJECT_MANAGER);
                    } catch(CustomException exception) {  
                        logger.error(exception.getMessage()); 
                    } 
                    break;
                
                case 2:
                    try {
                        addOrUpdateEmployeeDetails(Constants.UPDATE, Constants.PROJECT_MANAGER);
                    } catch(CustomException exception) {  
                        logger.error(exception.getMessage()); 
                    } 
                    break;
 
                case 3:
                    try {
                        displayEmployeesDetailsByRoleName(Constants.TRAINER);
                    } catch(CustomException exception) {  
                        logger.error(exception.getMessage()); 
                    }                                   
                    break; 
                case 4:
                    try {
                        displayEmployeesDetailsByRoleName(Constants.PROJECT_MANAGER);
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
                        displayProjectsAssignedToEmployee(); 
                    } catch(CustomException exception) {  
                        logger.error(exception.getMessage()); 
                    } 
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
            logger.info("\n\nChoose one option from below\n 1. Add projects \n 2. Update projects\n 3. Display projects\n  4. assign projects to employees\n 5. display assigned projects to employees\n 6. display employees assigned to project\n 7. delete Assigned employees to project\n 8. Go back\n ");  
            int choice = scannerInput.nextInt();
            switch (choice) {
                case 1:
                    try {
                        addOrUpdateProjectDetails(Constants.ADD, Constants.PROJECT_MANAGER);
                    } catch( CustomException error) {  
                        logger.error(error.getMessage()); 
                    } 
                    break;
                case 2:
                    try {
                        addOrUpdateProjectDetails(Constants.UPDATE, Constants.PROJECT_MANAGER);
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
                        assignProjectToEmployees();
                    } catch( CustomException error) {  
                        logger.error(error+" Error!! invalid input");
                    }  
                    break;   
                case 5:
                    try {
                        displayAssignedProjectsToEmployees();
                    } catch( CustomException error) {  
                        logger.error(error+" Error!! invalid input");
                    }  
                    break;
                case 6:
                    try {
                        displayEmployeesAssignedToProject();
                    } catch( CustomException error) {  
                        logger.error(error+" Error!! invalid input");
                    }  
                    break;        

                case 7: 
                    try {
                        deleteAssignedEmployeeToProject();
                    } catch( CustomException error) {  
                        logger.error(error+" Error!! invalid input");
                    }    
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
        int failed = 0;
        Scanner scannerInput = new Scanner(System.in);
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
            roleName = validate("enter the employee role you want to update:");
        }
        if (operation.equals(add) || employeeService.isEmployeeAvailable(employeeId)) {
            System.out.print("Enter the Required Data to **SignUP**\n"); 
	    employeeDto.setFirstName(validate("First Name:"));
	    employeeDto.setLastName(validate("Last Name:"));
            try {
	        employeeDto.setDob(LocalDate.parse(validate("dob (YYYY-MM-DD):")));
                employeeDto.setGender(validate("Gender:"));
                employeeDto.setDesignation(validate("Designation:"));
                employeeDto.setCity(validate("City :"));
	        employeeDto.setDateOfJoining(LocalDate.parse(validate("Date of Joining:")));
            } catch(Exception error) {
                throw new CustomException("date parse error!!");
            }
            employeeDto.setBatch(validate("Batch:"));
            employeeDto.setFatherName(validate("Father Name  :"));
            employeeDto.setEmail(validate("Email Id:"));
            employeeDto.setPhoneNumber(validate("phoneNumber:")); 
            employeeDto.setStatus(Constants.ACTIVE); 
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
        Scanner scannerInput = new Scanner(System.in);
        System.out.print("**Enter your employeeeId you want to delete** ");
        int employeeId = scannerInput.nextInt();
        if (employeeService.isEmployeeAvailable(employeeId)) {
            String messageFromService = employeeService.deleteEmployee(employeeId);
            if (messageFromService != null) {
                logger.info(messageFromService);
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
	    projectDto.setName(validate("Project Name:"));
	    projectDto.setClientName(validate("Client Name:"));
            projectDto.setCompanyName(validate("Company Name:"));
            try {
	        projectDto.setStartDate(LocalDate.parse(validate("Started Date (YYYY-MM-DD):")));
                projectDto.setStatus(validate("Status:"));
                projectDto.setEstimatedDuration(validate("Estimated Duration (in month):"));
                projectDto.setDescription(validate("Description :"));
	        projectDto.setTechnologyUsed(validate("Technology Used:"));
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

    public void deleteProject() throws CustomException {
        Scanner scannerInput = new Scanner(System.in);
        System.out.print("**Enter your projectId you want to delete** ");
        int projectId = scannerInput.nextInt();
        if (projectService.isProjectAvailable(projectId)) {
            String messageFromService = projectService.deleteProject(projectId);
            if (messageFromService != null) {
                logger.info(messageFromService);
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
        String email = validate("Email Id:");
        System.out.print("Enter the Project Id: ");
        int projectId = scannerInput.nextInt();  
        if (employeeService.isEmployeeAvailable(employeeId) && projectService.isProjectAvailable(projectId)) {
         // if (!assignedProjectsToEmployees.containsKey(projectId)) {   
                System.out.print("Enter the number of Employees you want to Assign: ");
                int number = scanner.nextInt();
                for (int index=0; index<number;index++) {
                    EmployeeProjectDto employeeProjectDto = new EmployeeProjectDto();
                    System.out.print("Enter the Employee Id: ");
                    int assignedEmployeeId = scanner.nextInt();
                    employeeProjectDto.setEmployeeId(assignedEmployeeId);
                    employeeProjectDto.setProjectId(projectId);
                    employeeProjectDto.setAssignedBy(email);
                    employeeProjectDto.setStatus(validate("Status:"));
                    employeeProjectDto.setAssignedDate(LocalDate.parse(validate("Assigned Date (YYYY-MM-DD):")));
                    employeeProjectDto.setEmployeeRole(validate("Employee Role :"));
                    employeeProjectDto.setRelievedDate(LocalDate.parse(validate("Relieved Date (YYYY-MM-DD):")));                  
                    assignedEmployeesToProjectDto.add(employeeProjectDto);
                }
                String messageFromService = employeeProjectService.assignProjectToEmployees(assignedEmployeesToProjectDto);
                if (messageFromService != null) {      
                    System.out.print(messageFromService);
                } else {
                    System.out.print("Invalid employee ID!!");
                }
         // } else {
         //     System.out.print("This Employee already assigned try again!");
         // }
        } else {
            System.out.print("invalid employeeId or projectId");
        }
    }  

    public void displayAssignedProjectsToEmployees() throws CustomException{
        List<EmployeeProjectDto> assignedEmployeesDto = employeeProjectService.getassignedProjectToEmployees();
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

    public void displayEmployeesAssignedToProject() throws CustomException{
        Scanner scannerInput = new Scanner(System.in);
        System.out.print("**Enter your projectId** ");
        int projectId = scannerInput.nextInt();
        if (projectService.isProjectAvailable(projectId)) {
            List<EmployeeDto> employeeDtos = employeeProjectService.getEmployeesDetailsByProjectId(projectId);
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
    }  

    public void displayProjectsAssignedToEmployee() throws CustomException{
        Scanner scannerInput = new Scanner(System.in);
        System.out.print("**Enter your employeeId** ");
        int employeeId = scannerInput.nextInt();
        if (employeeService.isEmployeeAvailable(employeeId)) {
            List<ProjectDto> projectDtos = employeeProjectService.getProjectsDetailsByEmployeeId(employeeId);
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
    }

    public void deleteAssignedEmployeeToProject() throws CustomException{
        Scanner scannerInput = new Scanner(System.in);
        System.out.print("**Enter your projectId you want to delete** ");
        int projectId = scannerInput.nextInt();
        if (projectService.isProjectAvailable(projectId)) {
            String messageFromService = projectService.deleteEmployeesAssignedToProjectByProjectId(projectId);
            if (messageFromService != null) {
                logger.info(messageFromService);
            } else {
                logger.info("\nProcess FAILED..invalid project id");
            }
        } else { 
            logger.info("projectId not found");
        }     
    }

    /**
     * <p>
     * This method is used to validate names while getting input
     * </p>
     * 
     * @parm input is used as attribute to validate the given input
     */ 
     public String validate(String input) throws CustomException{
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
            } else {
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