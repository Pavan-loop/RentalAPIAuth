package com.example.RentalManagementApi.errorHandling;

public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException() {
        super("Access denied");
    }
}
