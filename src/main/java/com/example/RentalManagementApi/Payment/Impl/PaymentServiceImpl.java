package com.example.RentalManagementApi.Payment.Impl;

import com.example.RentalManagementApi.Payment.LastMonthEarning;
import com.example.RentalManagementApi.Payment.Payment;
import com.example.RentalManagementApi.Payment.PaymentRepo;
import com.example.RentalManagementApi.Payment.PaymentService;
import com.example.RentalManagementApi.tenants.Tenant;
import com.example.RentalManagementApi.tenants.TenantRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepo paymentRepo;
    private final TenantRepo tenantRepo;

    public PaymentServiceImpl(PaymentRepo paymentRepo, TenantRepo tenantRepo) {
        this.paymentRepo = paymentRepo;
        this.tenantRepo = tenantRepo;
    }

    @Override
    public List<Payment> getAllPayment() {
        return paymentRepo.findAll();
    }

//    @Override
//    public void addPayment(Long id, Payment payment) {
//        Tenant tenant = tenantRepo.findById(id).orElseThrow();
//        tenant.getPayments().add(payment);
//        payment.getTenant().add(tenant);
//        paymentRepo.save(payment);
//        tenantRepo.save(tenant);
//    }

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
