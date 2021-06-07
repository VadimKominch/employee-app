package com.mastery.java.task.jms;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.rest.AsyncEmployeeController;
import com.mastery.java.task.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.*;
import java.util.Enumeration;
import java.util.stream.Stream;

@Component
public class JmsConsumer {
    Logger logger = LoggerFactory.getLogger(JmsConsumer.class);

    @JmsListener(destination = "simplewebapp.employee.queue",containerFactory = "jmsListenerContainerFactory")
    public void recieveEmployee(Employee employee) {
        logger.info("Recieved employee {} from active mq",employee);
    }
}
