package com.example.RentalManagementApi.properties.impl;

import com.example.RentalManagementApi.errorHandling.PropertyNotFoundException;
import com.example.RentalManagementApi.errorHandling.UserNotFoundException;
import com.example.RentalManagementApi.properties.Property;
import com.example.RentalManagementApi.properties.PropertyRepo;
import com.example.RentalManagementApi.properties.PropertyService;
import com.example.RentalManagementApi.security.jwt.JwtService;
import com.example.RentalManagementApi.user.User;
import com.example.RentalManagementApi.user.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {

   private final PropertyRepo propertyRepo;
   private final UserRepo userRepo;
   private final JwtService jwtService;

    @Override
    public List<Property> getAllProperties(String token) {
        var user = getUser(token);
        return propertyRepo.findPropertyByUserId(user.getId());
    }

    @Override
    public Long addProperty(Property property, String token) {
        var user = getUser(token);
        property.setUser(user);
        return propertyRepo.save(property).getId();
    }

    @Override
    public Property getById(Long propertyId, String token) {
        var user = getUser(token);
        return propertyRepo.findByIdAndUserId(propertyId, user.getId())
                .orElseThrow(() -> new PropertyNotFoundException(propertyId));
    }

    @Override
    public void deleteProp(Long propertyId, String token) {
        var user = getUser(token);
        int deletedRow = propertyRepo.deleteByIdAndUserId(propertyId, user.getId());
        if (deletedRow == 0) {
            throw new PropertyNotFoundException(propertyId);
        }
    }

    @Override
    public void UpdateProp(Long id, Property property) {
        var optionalProperty = propertyRepo.findById(id)
                .orElseThrow(() -> new PropertyNotFoundException(id));
            optionalProperty.setName(property.getName());
            optionalProperty.setAddress(property.getAddress());
            propertyRepo.save(optionalProperty);
    }

    @Override
    public long getPropertyCount() {
        return propertyRepo.count();
    }

    private User getUser(String token) {
        final String email = jwtService.extractEmail(token.substring(7));
        return userRepo.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
    }
}

