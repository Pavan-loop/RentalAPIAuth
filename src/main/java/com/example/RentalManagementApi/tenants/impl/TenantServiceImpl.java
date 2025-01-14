package com.example.RentalManagementApi.tenants.impl;

import com.example.RentalManagementApi.errorHandling.ParentHaveChildException;
import com.example.RentalManagementApi.errorHandling.UnitNotFoundException;
import com.example.RentalManagementApi.tenants.Tenant;
import com.example.RentalManagementApi.tenants.TenantDTO;
import com.example.RentalManagementApi.tenants.TenantRepo;
import com.example.RentalManagementApi.tenants.TenantService;
import com.example.RentalManagementApi.units.Unit;
import com.example.RentalManagementApi.units.UnitRepo;
import com.example.RentalManagementApi.units.UnitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TenantServiceImpl implements TenantService {
    private final TenantRepo tenantRepo;
    private final UnitRepo unitRepo;

    @Override
    public List<Tenant> getAllTenant() {
        return tenantRepo.findAll();
    }

    @Override
    public void addTenant(Long id, TenantDTO tenantDTO) {
        var unitOptional = unitRepo.findById(id)
                .orElseThrow(() -> new UnitNotFoundException(id));

        var tenant = Tenant.builder()
                .name(tenantDTO.getName())
                .idProof(tenantDTO.getIdProof())
                .idNumber(tenantDTO.getIdNumber())
                .phone(tenantDTO.getPhone())
                .members(tenantDTO.getMembers())
                .Status("Occupied")
                .period("11")
                .unit(unitOptional)
                .build();

        tenantRepo.save(tenant);
    }

    @Override
    public boolean deleteTenant(Long id) {
        try{
            tenantRepo.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public long getCount() {
        return tenantRepo.count();
    }

    @Override
    public void deletePTenant(Long id) {

        try {
            tenantRepo.deletePTenant(id);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    @Override
    public boolean updateStatus(Long id) {
        Optional<Tenant> optionalTenant = tenantRepo.findById(id);
        if (optionalTenant.isPresent()){
            Tenant tenant1 = optionalTenant.get();
            tenant1.setStatus("Paid");
            tenantRepo.save(tenant1);
            return true;
        }
        return false;
    }


}
