package com.mastery.java.task.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.lang.annotation.Target;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @NotNull
    @JsonProperty(value = "firstName",required = true)
    private String firstName;
    @NotNull
    @JsonProperty(required = true)
    private String lastName;
    @NotNull
    @JsonProperty(required = true)
    private String jobTitle;
    @NotNull
    @JsonProperty(value = "gender",required = true)
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @NotNull
    @JsonProperty(required = true)
    private Date dateOfBirth;

    @Column(name="department_id")
    @NotNull
    @JsonProperty(required = true)
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

    public String getGender() {
        return gender.getValue();
    }

    public void setGender(String value) {
        this.gender = Gender.getGender(value);
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

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", gender=" + gender +
                ", dateOfBirth=" + dateOfBirth +
                ", department=" + department +
                '}';
    }
}
