package com.example.RentalManagementApi.units;

import java.util.List;

public interface UnitService {
    List<Unit> getAllUnit();
    void createUnit(Long propertyId, List<UnitDTO> unit);
    Unit getUnitById(Long id);
    void updateById(Long unitId, UnitDTO unitDTO, Long propertyId);
    boolean deleteById(Long id);
    List<Unit> findUnitByPropertyId(Long id);
    boolean deleteAll(Long propertyId);
    List<Status> getStatus(Long propertyId);
    String getRentAmount(Long id);
}
