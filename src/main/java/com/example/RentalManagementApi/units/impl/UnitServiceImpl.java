package com.example.RentalManagementApi.units.impl;

import com.example.RentalManagementApi.Payment.PaymentRepo;
import com.example.RentalManagementApi.errorHandling.PropertyNotFoundException;
import com.example.RentalManagementApi.errorHandling.UnitNotFoundException;
import com.example.RentalManagementApi.properties.PropertyRepo;
import com.example.RentalManagementApi.tenants.TenantRepo;
import com.example.RentalManagementApi.units.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class UnitServiceImpl implements UnitService {

    private final UnitRepo unitRepo;
    private final PropertyRepo propertyRepo;
    private final TenantRepo tenantRepo;
    private final PaymentRepo paymentRepo;


    @Override
    public List<Unit> getAllUnit() {
        return unitRepo.findAll();
    }

    @Override
    public void createUnit(Long propertyId, List<UnitDTO> unitDTO) {
        var propertyOptional = propertyRepo.findById(propertyId)
                .orElseThrow(() -> new PropertyNotFoundException(propertyId));
            var units = unitDTO.stream().map(dto ->
                Unit.builder()
                        .unitNo(dto.getUnitNo())
                        .unitType(dto.getUnitType())
                        .amount(dto.getAmount())
                        .property(propertyOptional)
                        .build())
                    .toList();
            unitRepo.saveAll(units);
    }


    @Override
    public Unit getUnitById(Long id) {
        return unitRepo.findById(id)
                .orElseThrow(() -> new UnitNotFoundException(id));
    }

    @Override
    public void updateById(Long unitId, UnitDTO unitDTO, Long propertyId) {
        var property = propertyRepo.findById(propertyId)
                .orElseThrow(() -> new PropertyNotFoundException(propertyId));
        var unit = unitRepo.findUnitByIdAndPropertyId(unitId, propertyId)
                .orElseThrow(() -> new UnitNotFoundException(unitId));

        unit.setUnitNo(unitDTO.getUnitNo());
        unit.setUnitType(unitDTO.getUnitType());
        unit.setAmount(unitDTO.getAmount());
        unit.setProperty(property);

        unitRepo.save(unit);
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            Long Pid = tenantRepo.getTenantIdByUnitId(id);
            paymentRepo.deleteConnection(Pid);
            tenantRepo.deleteTenantByUnitId(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public List<Unit> findUnitByPropertyId(Long id) {
        return unitRepo.findByPropertyId(id);
    }

    @Override
    public boolean deleteAll(Long propertyId) {
        try {
            unitRepo.deleteAll(propertyId);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<Status> getStatus(Long propertyId) {
        return unitRepo.getStatus(propertyId);
    }

    @Override
    public String getRentAmount(Long id) {
        return unitRepo.getRentAmount(id);
    }
}
