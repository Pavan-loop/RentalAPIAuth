package com.example.RentalManagementApi.Payment;

import com.example.RentalManagementApi.tenants.TenantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
@CrossOrigin("*")
public class PaymentController {

    private final PaymentService paymentService;

    private final TenantService tenantService;

    public PaymentController(PaymentService paymentService, TenantService tenantService) {
        this.paymentService = paymentService;
        this.tenantService = tenantService;
    }

    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayment(){
        List<Payment> payments = paymentService.getAllPayment();
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }

    @GetMapping("/sum")
    public ResponseEntity<LastMonthEarning> getSum(){
        return new ResponseEntity<>(paymentService.getTotalAmount(), HttpStatus.OK);
    }

    @DeleteMapping("/tenant/{id}")
    public ResponseEntity<String> deleteConnection(@PathVariable Long id){
        boolean isDeleted = paymentService.deleteConnection(id);
        boolean isTenantDeleted = tenantService.deleteTenant(id);
        if (isDeleted && isTenantDeleted)
            return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
        else
            return new ResponseEntity<>("Problem",HttpStatus.NO_CONTENT);
    }
}
