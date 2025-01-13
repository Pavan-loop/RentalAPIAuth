package com.example.RentalManagementApi.units;

import com.example.RentalManagementApi.properties.Property;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "unit_type")
    private String unitType;
    private String amount;
    @Column(name = "unit_no")
    private String unitNo;

    @ManyToOne
    private Property property;
}
