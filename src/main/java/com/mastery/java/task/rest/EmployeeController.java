package com.mastery.java.task.rest;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.EmployeeService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService service;


    @ExceptionHandler({ NumberFormatException.class})
    public ResponseEntity<String> handleException() {
        return new ResponseEntity<String>("Error",HttpStatus.OK);
    }

    @GetMapping(value = "/one/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable String id) {
            return new ResponseEntity<Employee>(service.getEmployeeById(Long.parseLong(id)), HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<?> getAllEmployee() {
        return new ResponseEntity<List<Employee>>(service.getAll(),HttpStatus.OK);
    }

    @PostMapping(value="/add")
    public void saveEmployee(@RequestBody Employee employee) {
        System.out.println(employee.getGender().name());
        service.insert(employee);
    }

    @PostMapping(value = "/update/id/{id}")
    public void updateEmployee(@RequestBody Employee employee,@PathVariable Long id) {
        service.update(employee,id);
    }

    @DeleteMapping(value = "/delete/id/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        service.delete(id);
    }
}
