package com.techproeducation.backendproject.initialwork.exceptions;


//Exception for Date-Time parsing operations
public class TimeFormatException extends RuntimeException{

    public TimeFormatException(String message) {
        super(message);
    }
}
