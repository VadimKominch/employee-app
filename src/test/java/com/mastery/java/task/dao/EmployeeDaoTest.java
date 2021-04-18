package com.mastery.java.task.dao;


import com.mastery.java.task.config.AppConfiguration;
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
@ContextConfiguration(classes = {AppConfiguration.class})
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
		Employee employee = employeeDao.getById(first);
        Assert.assertNotNull(employee);
	}
	
	@Test(expected = EmptyResultDataAccessException.class)
	public void checkIfGetInvalidIdEntityFromDbGetException() {
		Employee employee = employeeDao.getById(invalidId);
	}
	
	@Test
	public void checkIfAllRecordsFromDbWillBeRecieved() {
		List<Employee> employees = employeeDao.getAll();
		Assert.assertEquals(3,employeeDao.getAll().size());
	}
	@Test
	public void checkIfNewEmployeeWillBeInserted() {
		int oldCapacity = employeeDao.getAll().size();
		Employee newEmployee = new Employee(0L,"Sasha","Kominch","engineer", Gender.MALE,new java.sql.Date(new Date().getTime()),2);
		employeeDao.save(newEmployee);
		Assert.assertEquals(oldCapacity+1,employeeDao.getAll().size());
	}
	
	@Test(expected = NullPointerException.class)
	public void checkIfNullWillNotBeInserted() {
		int oldCapacity = employeeDao.getAll().size();
		employeeDao.save(null); //raise SQLException for null data
	}
	
	@Test
	public void checkIfChangeSomeFieldsInExistingEntityWillBeRepresentedInDb() {
		Employee employee = employeeDao.getById(first);
		String actual = employee.getFirstName();
		employee.setFirstName("Undefined");
		employeeDao.update(first,employee);
		Employee newEmployee = employeeDao.getById(first);
		System.out.println(employee.getFirstName());
		System.out.println(newEmployee.getFirstName());
		Assert.assertNotEquals(actual,newEmployee.getFirstName());
	}

	@Test(expected = EmptyResultDataAccessException.class)
    public void checkIfExisitingItemWillBeDeleted() {
        employeeDao.delete(idToDelete);
        employeeDao.getById(idToDelete);
    }

    @Test
    public void checkIfNonExistingItemWillNotChangeBase() {
	    int prevCapacity = employeeDao.getAll().size();
        employeeDao.delete(invalidId);
        Assert.assertEquals(prevCapacity,employeeDao.getAll().size());
    }
}
