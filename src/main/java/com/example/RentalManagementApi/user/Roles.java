package com.example.RentalManagementApi.user;

public enum Roles {
    LANDLORD,
    TENANT,
    ADMIN;

    public String getAuthorities() {
        return "ROLE_" + this.name();
    }
}
