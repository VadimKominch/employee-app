package com.mastery.java.task.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Date;
import java.util.Objects;

public class Employee {
    private Long employeeId;
    @JsonProperty("firstName")
    private String firstName;
    private String lastName;
    private String jobTitle;
    private Gender gender;
    private Date dateOfBirth;
    private Integer department;

    public Employee(Long employeeId, String firstName, String lastName, String jobTitle, Gender gender, Date dateOfBirth, Integer department) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobTitle = jobTitle;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.department = department;
    }

    public Employee() {
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return employeeId.equals(employee.employeeId) &&
                firstName.equals(employee.firstName) &&
                lastName.equals(employee.lastName) &&
                jobTitle.equals(employee.jobTitle) &&
                gender == employee.gender &&
                dateOfBirth.equals(employee.dateOfBirth) &&
                department.equals(employee.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, firstName, lastName, jobTitle, gender, dateOfBirth, department);
    }
}
