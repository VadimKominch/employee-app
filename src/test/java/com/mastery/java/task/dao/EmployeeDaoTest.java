package com.mastery.java.task.dao;


import com.mastery.java.task.config.AppConfiguration;
import com.mastery.java.task.config.TestConfiguration;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class})
@PropertySource("classpath:application.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class EmployeeDaoTest {
	
	@Autowired
	private EmployeeDao employeeDao;
	private final static long first = 1;
	private final static long invalidId = Integer.MAX_VALUE -1;
	private final static long idToDelete = 3;
	//@Before
	
	@Test
	public void checkIfGetFirstEntityFromDbNotNull() {
		Employee employee = employeeDao.findById(first).get();
        Assert.assertNotNull(employee);
	}
	
	@Test(expected = EmptyResultDataAccessException.class)
	public void checkIfGetInvalidIdEntityFromDbGetException() {
		Employee employee = employeeDao.findById(invalidId).get();
	}
	
	@Test
	public void checkIfAllRecordsFromDbWillBeRecieved() {
		List<Employee> employees = (List<Employee>) employeeDao.findAll();
		Assert.assertEquals(3,employees.size());
	}
	@Test
	public void checkIfNewEmployeeWillBeInserted() {
		int oldCapacity = ((List<Employee>) employeeDao.findAll()).size();
		Employee newEmployee = new Employee(0L,"Sasha","Kominch","engineer", Gender.MALE,new java.sql.Date(new Date().getTime()),2);
		employeeDao.save(newEmployee);
		Assert.assertEquals(oldCapacity+1,((List<Employee>) employeeDao.findAll()).size());
	}
	
	@Test(expected = NullPointerException.class)
	public void checkIfNullWillNotBeInserted() {
		int oldCapacity = ((List<Employee>) employeeDao.findAll()).size();
		employeeDao.save(null); //raise SQLException for null data
	}
	
	@Test
	public void checkIfChangeSomeFieldsInExistingEntityWillBeRepresentedInDb() {
		Employee employee = employeeDao.findById(first).get();
		String actual = employee.getFirstName();
		employee.setFirstName("Undefined");
		employeeDao.save(employee);
		Employee newEmployee = employeeDao.findById(first).get();
		System.out.println(employee.getFirstName());
		System.out.println(newEmployee.getFirstName());
		Assert.assertNotEquals(actual,newEmployee.getFirstName());
	}

	@Test(expected = EmptyResultDataAccessException.class)
    public void checkIfExisitingItemWillBeDeleted() {
        employeeDao.deleteById(idToDelete);
        employeeDao.findById(idToDelete).get();
    }

    @Test
    public void checkIfNonExistingItemWillNotChangeBase() {
	    int prevCapacity = ((List<Employee>) employeeDao.findAll()).size();
        employeeDao.deleteById(invalidId);
        Assert.assertEquals(prevCapacity,((List<Employee>) employeeDao.findAll()).size());
    }
}
