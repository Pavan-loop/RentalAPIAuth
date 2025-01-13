package com.example.RentalManagementApi.properties;

import java.util.List;

public interface PropertyService {

    List<Property> getAllProperties(String token);
    Long addProperty(Property property, String token);
    Property getById(Long propertyId, String token);
    void deleteProp(Long propertyId, String token);
    void UpdateProp(Long id, Property property);
    long getPropertyCount();
}
