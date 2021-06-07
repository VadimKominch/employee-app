package com.mastery.java.task.rest;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.jms.JmsProducer;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name="Async Employee Controller", description="represents workflow for using activemq")
@RestController
@RequestMapping(value = "/employees/async/")
public class AsyncEmployeeController {
    Logger logger = LoggerFactory.getLogger(AsyncEmployeeController.class);

    @Autowired
    private JmsProducer jmsProducer;

    @Operation(
            summary = "Pass employee to active mq queue",
            description = "Passed from request body employee is validated and sent to queue"
    )
    @ApiResponses(value = {@ApiResponse(responseCode="200",description = "correctly passed employee stored in activemq queue")})
    @PostMapping
    public ResponseEntity<String> postEmployeeToQueue(@Valid @Parameter(description = "Employee json body") @RequestBody Employee employee) {
        logger.info("{} is sent to active mq queue",employee);
        jmsProducer.sendMessage(employee);
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }
}
