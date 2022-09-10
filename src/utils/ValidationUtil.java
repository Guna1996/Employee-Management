package com.ideas2it.utils;

import java.time.format.DateTimeParseException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;  
import java.time.Period;  
import java.util.Date;
import java.util.Scanner;


enum Gender{
    Male, male, female, Female;
}

public class ValidationUtil {
    static int count = 8;
    static String company = "I2I";
    public static String AMOUNT_REGEX = "[0-9]";
    public static String PHONE_NUMBER_REGEX = "(0/91)?[7-9][0-9]{9}";
    public static String EMAIL_REGEX = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";   
    public static String NAME_REGEX = "^[a-zA-Z]*$";
    
    public static String generateStaffNumber() {
        count++;
        String employeeId = company + count;
        return employeeId; 
    }
          
    public static boolean validate(String regex, String validated) {
        boolean result = validated.matches(regex);
        return result;
    }

    public static boolean validateDob(String validated) {
        try {
            LocalDate date = LocalDate.parse(validated); 
        }
        catch (Exception e) {
            return false;
        } 
        return true;                                  
    }       
    
    public static boolean validateGender(String validated) {
        Gender[] genderEnum = Gender.values();
        boolean isValid = false;
        for (Gender searchGender: genderEnum) {
            String validation = searchGender.toString();
            if (validated.equals(validation)) {
                isValid = true;
                break; 
            }                         
        }
        return isValid;
    }    
    
    public static int calculateAge(String dob) {
        LocalDate date = LocalDate.parse(dob);
        LocalDate currentDate = LocalDate.now(); 
        return Period.between(date, currentDate).getYears();
    }
}