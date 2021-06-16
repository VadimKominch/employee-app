package com.mastery.java.task.jms;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JmsConsumer {
    Logger logger = LoggerFactory.getLogger(JmsConsumer.class);

    private EmployeeService employeeService;

    public JmsConsumer(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @JmsListener(destination = "simplewebapp.employee.queue")
    public void recieveEmployee(Employee employee) {
        logger.info("Recieved employee {} from active mq",employee);
        employeeService.insert(employee);
    }
}
