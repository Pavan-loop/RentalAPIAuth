package com.example.RentalManagementApi.TimeFrame;

import com.example.RentalManagementApi.tenants.Tenant;
import com.example.RentalManagementApi.tenants.TenantRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@EnableScheduling
public class PayRent {

    public final TenantRepo tenantRepo;

    public PayRent(TenantRepo tenantRepo) {
        this.tenantRepo = tenantRepo;
    }

    @Scheduled(cron = "0 0 0 5 * ?")
    public void alert(){
        List<Tenant> tenants = tenantRepo.findAll();
        for (Tenant tenant: tenants){
            if (Objects.equals(tenant.getStatus(), "Paid")){
                tenant.setStatus("Un Paid");
                tenantRepo.save(tenant);
 
            }
        }
    }

    @Scheduled(cron = "0 0 0 5 * ?")
    public void update(){
        List<Tenant> tenants = tenantRepo.findAll();
        for (Tenant tent : tenants){
            int reduce = Integer.parseInt(tent.getPeriod());
            if (reduce > 0){
                reduce--;
                String insert = Integer.toString(reduce);
                tent.setPeriod(insert);
                tenantRepo.save(tent);
            }
        }
    }
}
