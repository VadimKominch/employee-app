package com.mastery.java.task.jms;

import com.mastery.java.task.dto.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;


@Component
public class JmsProducer {

    private Destination destination;
    private JmsTemplate jmsTemplate;


    public JmsProducer(Destination destination, JmsTemplate jmsTemplate) {
        this.destination = destination;
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessage(Employee employee) {
        jmsTemplate.convertAndSend(destination, employee);
    }

}
