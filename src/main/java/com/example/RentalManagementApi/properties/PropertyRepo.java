package com.example.RentalManagementApi.properties;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PropertyRepo extends JpaRepository<Property, Long> {
    Optional<Property> findById(long id);
    List<Property> findPropertyByUserId(Long id);
    Optional<Property> findByIdAndUserId(Long propertyId, Long userId);
    @Transactional
    int deleteByIdAndUserId(Long propertyId, Long userId);
}
