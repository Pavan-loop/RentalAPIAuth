package com.example.RentalManagementApi.Payment.Impl;

import com.example.RentalManagementApi.Payment.LastMonthEarning;
import com.example.RentalManagementApi.Payment.Payment;
import com.example.RentalManagementApi.Payment.PaymentRepo;
import com.example.RentalManagementApi.Payment.PaymentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepo paymentRepo;

    public PaymentServiceImpl(PaymentRepo paymentRepo) {
        this.paymentRepo = paymentRepo;
    }

    @Override
    public List<Payment> getAllPayment() {
        return paymentRepo.findAll();
    }

    @Override
    public LastMonthEarning getTotalAmount() {
        return paymentRepo.getSum();
    }

    @Override
    public boolean deleteConnection(Long id) {
        try {
            paymentRepo.deleteConnection(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
