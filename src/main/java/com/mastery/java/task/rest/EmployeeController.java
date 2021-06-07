package com.mastery.java.task.rest;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.exception.EmployeeNotFoundException;
import com.mastery.java.task.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name="Employee Controller", description="implements CRUD operations with employee")
@Validated
@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

    Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService service;

    @Operation(
            summary = "Get employee by id",
            description = "Pass id to get employee from database"
    )
    @GetMapping(value = "/{id}")
    @ApiResponses(value = {@ApiResponse(responseCode="200",description = "return Employee with passed id"),
            @ApiResponse(responseCode="404",description = "return error message ")})
    public ResponseEntity getEmployee(@PathVariable @Parameter(description = "Employee id") Long id) throws EmployeeNotFoundException {
            logger.info("Getting employee by id {}",id);
            Employee employee = service.getEmployeeById(id);
            logger.debug("Request for employee {}",employee);
            return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }


    @Operation(
            summary = "Get all employees",
            description = "Get all employees from database(default sort by id) "
    )
    @GetMapping
    public ResponseEntity getAllEmployee() {
        logger.info("List of employees got");
        return new ResponseEntity<List<Employee>>(service.getAll(),HttpStatus.OK);
    }


    @Operation(
            summary = "save employee",
            description = "Save employee in database(all fields must be non-null values)"
    )
    @PostMapping
    public ResponseEntity saveEmployee(@RequestBody @Parameter(description = "Employee json body") Employee employee) {
        service.insert(employee);
        logger.info("Employee {} saved",employee);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update employee",
            description = "Delete employee from database"
    )
    @PutMapping(value = "/{id}")
    @ApiResponse(responseCode="200",description = "update employee with correctly passed id")
    public ResponseEntity updateEmployee(@Valid @RequestBody @Parameter(description = "Employee json body")Employee employee,
                                         @PathVariable @Parameter(description = "Employee id") Long id) throws EmployeeNotFoundException {
        service.update(employee,id);
        logger.info("Employee with id {} updated. New employee is {}",id,employee);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Operation(
            summary = "Delete employee",
            description = "Delete employee from database"
    )
    @DeleteMapping(value = "/{id}")
    @ApiResponse(responseCode="204",description = "delete employee with correctly passed id")
    public ResponseEntity deleteEmployee( @PathVariable @Parameter(description = "Employee id") Long id) throws EmployeeNotFoundException {
        service.delete(id);
        logger.info("Employee with id {} deleted",id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
