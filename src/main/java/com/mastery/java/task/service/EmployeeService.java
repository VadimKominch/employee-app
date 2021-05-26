package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
@Validated
@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    public Employee getEmployeeById(Long id) throws UserNotFoundException {
            Employee emp =  employeeDao.findById(id).orElse(null);
            if(emp == null) {
                throw new UserNotFoundException("User not found");
            }
            return emp;
    }

    public List<Employee> getAll() {
        return (List<Employee>) employeeDao.findAll();
    }

    public void insert(@Valid Employee employee) {
        employeeDao.save(employee);
    }

    public void delete(@Valid Long id) {

        employeeDao.deleteById(id);
    }

    public void update(@Valid Employee employee,@Valid Long id) {
            employee.setEmployeeId(id);
            employeeDao.save(employee);
    }
}
