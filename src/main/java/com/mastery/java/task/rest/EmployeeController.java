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
@RequestMapping(value = "/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;


    @ExceptionHandler({ NumberFormatException.class})
    public ResponseEntity<String> handleException() {
        return new ResponseEntity<String>("Error",HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getEmployee(@PathVariable String id) {
            return new ResponseEntity<Employee>(service.getEmployeeById(Long.parseLong(id)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getAllEmployee() {
        return new ResponseEntity<List<Employee>>(service.getAll(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveEmployee(@RequestBody Employee employee) {
        service.insert(employee);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity updateEmployee(@RequestBody Employee employee,@PathVariable Long id) {
        service.update(employee,id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteEmployee(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
