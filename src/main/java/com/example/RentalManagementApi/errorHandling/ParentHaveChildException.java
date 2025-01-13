package com.example.RentalManagementApi.errorHandling;

public class ParentHaveChildException extends RuntimeException{
    public ParentHaveChildException(String message){
        super(message);
    }
}
