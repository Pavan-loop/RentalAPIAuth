package com.example.RentalManagementApi.Payment;


import java.util.List;

public interface PaymentService{
    List<Payment> getAllPayment();
//    void addPayment(Long id, Payment payment);
    LastMonthEarning getTotalAmount();
    boolean deleteConnection(Long id);
}
