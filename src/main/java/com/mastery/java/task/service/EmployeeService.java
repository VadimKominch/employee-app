package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.exception.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
@Validated
@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    public Employee getEmployeeById(Long id) throws EmployeeNotFoundException {
            Employee emp =  employeeDao.findById(id).orElse(null);
            if(emp == null) {
                throw new EmployeeNotFoundException(id);
            }
            return emp;
    }

    public List<Employee> getAll() {
        return (List<Employee>) employeeDao.findAll();
    }

    public void insert(Employee employee) {
        employeeDao.save(employee);
    }

    public void delete(Long id) throws EmployeeNotFoundException {
        getEmployeeById(id);
        employeeDao.deleteById(id);
    }

    public void update(Employee employee,Long id) throws EmployeeNotFoundException {
            getEmployeeById(id);
            employee.setEmployeeId(id);
            employeeDao.save(employee);
    }
}
