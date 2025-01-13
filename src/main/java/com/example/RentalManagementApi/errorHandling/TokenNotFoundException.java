package com.example.RentalManagementApi.errorHandling;

public class TokenNotFoundException extends RuntimeException{
    public TokenNotFoundException() {
        super("Token doesn't exists");
    }
}
