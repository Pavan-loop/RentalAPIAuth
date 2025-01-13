package com.example.RentalManagementApi.tenants;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TenantDTO {
    private String name;
    private String idProof;
    private String idNumber;
    private String phone;
    private String members;
}
