package com.ideas2it.model;
import com.ideas2it.model.Employee;

public class Trainee extends Employee {
    private String traineeBatch;
    private String skills;

    public Trainee(String firstName, String lastName, String employeeId, String dob, int age, String gender, String traineeBatch, 
        String skills, String fatherName, String email,  String phoneNumber) { 
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeId = employeeId;
        this.dob = dob;
        this.age = age;
        this.gender = gender;
        this.traineeBatch = traineeBatch;
        this.skills = skills;
        this.fatherName = fatherName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    
    public String getTraineeBatch() {
        return traineeBatch;
    }
    public String getSkills() {
        return skills;
    }
    public void setTraineeBatch(String traineeBatch) {
        this.traineeBatch = traineeBatch;
    }
    public void setSkills(String skills) {
        this.skills = skills;
    }
    
}








