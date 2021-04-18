package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dto.Employee;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class EmployeeService {


    private EmployeeDao employeeDao;

    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public Employee getEmployeeById(Long id) {
        try {
            return employeeDao.getById(id);
        } catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Employee> getAll() {
        return employeeDao.getAll();
    }

    public void insert(Employee employee) {
        if(employee != null)
            employeeDao.save(employee);
    }

    public void delete(Long id) {

        employeeDao.delete(id);
    }

    public void update(Employee employee,Long id) {
        if(employee != null)
            employeeDao.update(id,employee);
    }
}
