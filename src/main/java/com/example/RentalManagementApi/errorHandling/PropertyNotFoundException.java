package com.example.RentalManagementApi.errorHandling;

public class PropertyNotFoundException extends RuntimeException{
    public PropertyNotFoundException(long id) {
        super("Property with id: " + id + " not found");
    }
}
