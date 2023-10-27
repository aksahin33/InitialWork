package com.techproeducation.backendproject.initialwork.exceptions;

//Exception for empty list from db
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
