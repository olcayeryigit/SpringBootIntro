package com.olcay.springBootIntro.exception;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String message){
        super(message);
    }
}
