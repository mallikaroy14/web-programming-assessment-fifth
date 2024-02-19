package com.example.JPATest.exceptions;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(String message){
        super(message);
    }
}
