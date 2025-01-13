package com.example.RentalManagementApi.tenants;

import com.example.RentalManagementApi.Payment.Payment;
import com.example.RentalManagementApi.units.Unit;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String idProof;
    private String idNumber;
    private String phone;
    private String members;
    private String Status;
    private String period;

    @OneToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;
}
