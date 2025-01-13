package com.example.RentalManagementApi.errorHandling;

public class UnitNotFoundException extends RuntimeException{
    public UnitNotFoundException(Long id) {
        super("Unit with id: " + id + " not found");
    }
}
