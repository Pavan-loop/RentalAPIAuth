package com.example.RentalManagementApi.Payment;


import java.util.List;

public interface PaymentService{
    List<Payment> getAllPayment();
    LastMonthEarning getTotalAmount();
    boolean deleteConnection(Long id);
}
