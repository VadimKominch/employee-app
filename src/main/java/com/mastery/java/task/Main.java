package com.mastery.java.task;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import java.util.Date;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class,args);
        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
        System.out.println("Sending an email message.");
        jmsTemplate.convertAndSend("mailbox", new Employee(0L,"Sasha","Kominch","engineer", Gender.MALE,new java.sql.Date(new Date().getTime()),2));
    }
}

