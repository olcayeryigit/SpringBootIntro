package com.olcay.SpringBootIntro.exception;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String message){
        super(message);
    }
}
