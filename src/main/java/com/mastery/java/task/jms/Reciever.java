package com.mastery.java.task.jms;

import com.mastery.java.task.dto.Employee;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Reciever {

    @JmsListener(destination = "mailbox", containerFactory = "myFactory")
    public void receiveMessage(Employee employee) {
        System.out.println("Received <" + employee + ">");
    }

}
