package com.mastery.java.task.exception;

public class EmployeeNotFoundException extends Exception{
    private Long id;
    public EmployeeNotFoundException() {
        super();
    }

    public EmployeeNotFoundException(String message) {
        super(message);
    }

    public EmployeeNotFoundException(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
