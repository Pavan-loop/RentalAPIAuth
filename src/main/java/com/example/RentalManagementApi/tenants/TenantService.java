package com.example.RentalManagementApi.tenants;

import java.util.List;

public interface TenantService {
    List<Tenant> getAllTenant();
    void addTenant(Long id, TenantDTO tenant);
    boolean deleteTenant(Long id);
    long getCount();
    void deletePTenant(Long id);
    boolean updateStatus(Long id);
}
