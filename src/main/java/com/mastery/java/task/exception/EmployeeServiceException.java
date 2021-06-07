package com.mastery.java.task.exception;

public class EmployeeServiceException extends Exception {
    public EmployeeServiceException() {
        super();
    }

    public EmployeeServiceException(String message) {
        super(message);
    }

    public EmployeeServiceException(Throwable cause) {
        super(cause);
    }

    @Override
    public String toString() {
        return "EmployeeServiceException is caught";
    }
}
