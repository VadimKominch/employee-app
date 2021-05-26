package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDao extends CrudRepository<Employee,Long> {
}
