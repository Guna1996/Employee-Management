package com.ideas2it.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;  
import java.time.Period;  
import java.util.Date;
import java.util.Scanner;

enum Gender{
    Male, male, female, Female;
}

/**
 * The {@code ValidationUtil} has methods to validate the given input and perform simple logics using the input.
 * 
 *
 * @author  Gunaseelan K
 * since 1.0
 * jls Advanced logics+
 */   
public class ValidationUtil {
    static int count = 8;
    static String company = "I2I";
    public static String AMOUNT_REGEX = "[0-9]";
    public static String PHONE_NUMBER_REGEX = "(0/91)?[7-9][0-9]{9}";
    public static String EMAIL_REGEX = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";   
    public static String NAME_REGEX = "^[a-zA-Z]*$";
    
    /**
     * <p>
     * This method is used to validate string using regex
     * </p>
     *
     */    
    public static boolean validate(String regex, String validated) {
        boolean result = validated.matches(regex);
        return result;
    }

    /**
     * <p>
     * This method is used to validate date
     * </p>
     *
     */
    public static boolean validateDate(String validated) {
        try {
            LocalDate date = LocalDate.parse(validated); 
        }
        catch (Exception e) {
            return false;
        } 
        return true;                                  
    }       
    
    /**
     * <p>
     * This method is used to validate gender
     * </p>
     *
     */
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
    
    /**
     * <p>
     * This method is used to calculate age
     * </p>
     *
     */
    public static int calculateAge(String dob) {
        LocalDate date = LocalDate.parse(dob);
        LocalDate currentDate = LocalDate.now(); 
        return Period.between(date, currentDate).getYears();
    }
}