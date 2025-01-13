package com.example.RentalManagementApi.Payment;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PaymentRepo extends JpaRepository<Payment, Long> {
    @Query(
            value = "SELECT DATE_FORMAT(date, '%y-%m') AS month, SUM(amount) as sum FROM payment group by month order by month desc limit 1",
            nativeQuery = true
    )
    LastMonthEarning getSum();

    @Modifying
    @Transactional
    @Query(
            value = "DELETE FROM payment_tenant WHERE tenant_id=:id",
            nativeQuery = true
    )
    void deleteConnection(@Param("id") Long id);
}
