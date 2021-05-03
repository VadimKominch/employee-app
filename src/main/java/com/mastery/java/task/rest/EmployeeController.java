package com.mastery.java.task.rest;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

@Tag(name="Employee Controller", description="implements CRUD operations with employee")
@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;


    @ExceptionHandler({ NumberFormatException.class})
    public ResponseEntity<String> handleException() {
        return new ResponseEntity<String>("Error",HttpStatus.BAD_REQUEST);
    }


    @Operation(
            summary = "Get employee by id",
            description = "Pass id to get employee from database"
    )
    @GetMapping(value = "/{id}")
    public ResponseEntity getEmployee(@PathVariable Long id) {
            return new ResponseEntity<Employee>(service.getEmployeeById(id), HttpStatus.OK);
    }


    @Operation(
            summary = "Get all employees",
            description = "Get all employees from database(default sort by id) "
    )
    @GetMapping
    public ResponseEntity getAllEmployee() {
        return new ResponseEntity<List<Employee>>(service.getAll(),HttpStatus.OK);
    }


    @Operation(
            summary = "save employee",
            description = "Save employee in database(all fields must be non-null values)"
    )
    @PostMapping
    public ResponseEntity saveEmployee(@RequestBody @Parameter(description = "Employee json body") Employee employee) {
        service.insert(employee);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update employee",
            description = "Delete employee from database"
    )
    @PutMapping(value = "/{id}")
    public ResponseEntity updateEmployee(@RequestBody @Parameter(description = "Employee json body")Employee employee,
                                         @PathVariable @Min(0) @Parameter(description = "Employee id") Long id) {
        service.update(employee,id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Operation(
            summary = "Update employee",
            description = "Delete employee from database"
    )
    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteEmployee(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
