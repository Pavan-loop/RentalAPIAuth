package com.example.RentalManagementApi.Payment;

import com.example.RentalManagementApi.tenants.Tenant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private String amount;
    private String method;

    @JsonIgnore
    @ManyToMany
    private List<Tenant> tenant = new ArrayList<>();

    public Payment() {
    }

    public Payment(Long id, Date date, String amount, String method, List<Tenant> tenant) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.method = method;
        this.tenant = tenant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public List<Tenant> getTenant() {
        return tenant;
    }

    public void setTenant(List<Tenant> tenant) {
        this.tenant = tenant;
    }
}
