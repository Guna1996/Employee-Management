package com.ideas2it.model;
import com.ideas2it.model.Employee;
 
public class Trainer extends Employee {
    private String experience;
    private String expertiseIn;
 
    public Trainer() {
    }
    public Trainer(String firstName, String lastName, String employeeId, String dob, int age, String gender, 
        String expertiseIn, String experience, String fatherName, String email, String phoneNumber) { 
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeId = employeeId;
        this.dob = dob;
        this.age = age;
        this.gender = gender;
        this.expertiseIn = expertiseIn;
        this.experience = experience;
        this.fatherName = fatherName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }   

    public String getExperience() {
        return experience;
    }

    public String getExpertiseIn() {
        return expertiseIn;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public void setExpertiseIn(String expertiseIn) {
        this.expertiseIn = expertiseIn;
    }

}