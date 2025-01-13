package com.example.RentalManagementApi.errorHandling;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String email){
        super("User with username: " + email + " not found");
    }
}
