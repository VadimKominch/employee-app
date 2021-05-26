package com.mastery.java.task.service;


import com.mastery.java.task.config.AppConfiguration;
import com.mastery.java.task.config.TestConfiguration;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;
import com.mastery.java.task.exception.UserNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class EmployeeServiceTest {

    private final static long first = 1;
    private final static long invalidId = Integer.MAX_VALUE -1;
    private final static long idToDelete = 3;

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void checkIfAllRecordsFromDbWillBeRecieved() {
        List<Employee> employees = employeeService.getAll();
        Assert.assertEquals(3,employeeService.getAll().size());
    }

    @Test
    public void checkIfNotExistingIdWillReturnNull() throws UserNotFoundException {
        Employee employee = employeeService.getEmployeeById(Long.MAX_VALUE-1);
        Assert.assertNull(employee);
    }

    @Test
    public void checkIfGetFirstEntityFromDbNotNull() throws UserNotFoundException {
        Employee employee = employeeService.getEmployeeById(first);
        Assert.assertNotNull(employee);
    }

    @Test
    public void checkIfNewEmployeeWillBeInserted() {
        int oldCapacity = employeeService.getAll().size();
        Employee newEmployee = new Employee(0L,"Sasha","Kominch","engineer", Gender.MALE,new java.sql.Date(new Date().getTime()),2);
        employeeService.insert(newEmployee);
        Assert.assertEquals(oldCapacity+1,employeeService.getAll().size());
    }

    @Test
    public void checkIfNullWillNotBeInserted() {
        int prevCapacity = employeeService.getAll().size();
        int oldCapacity = employeeService.getAll().size();
        employeeService.insert(null);
        Assert.assertEquals(prevCapacity,employeeService.getAll().size());
    }

    @Test
    public void checkIfChangeSomeFieldsInExistingEntityWillBeRepresentedInDb() throws UserNotFoundException {
        Employee employee = employeeService.getEmployeeById(first);
        String actual = employee.getFirstName();
        employee.setFirstName("Undefined");
        employeeService.update(employee,first);
        Employee newEmployee = employeeService.getEmployeeById(first);
        System.out.println(employee.getFirstName());
        System.out.println(newEmployee.getFirstName());
        Assert.assertNotEquals(actual,newEmployee.getFirstName());
    }

    @Test
    public void checkIfExisitingItemWillBeDeleted() throws UserNotFoundException {
        employeeService.delete(idToDelete);
        Assert.assertNull(employeeService.getEmployeeById(idToDelete));
    }

    @Test
    public void checkIfNonExistingItemWillNotChangeBase() {
        int prevCapacity = employeeService.getAll().size();
        employeeService.delete(invalidId);
        Assert.assertEquals(prevCapacity,employeeService.getAll().size());
    }


}
