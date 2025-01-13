package com.example.RentalManagementApi.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepo extends JpaRepository<Token, Long> {
    @Query("""
            SELECT t from Token t INNER JOIN User u on t.user.id = u.id
            WHERE u.id=:id AND (t.isExpired=false or t.isRevoked=false)
            """)
    List<Token> getValidTokenOfTheUser(long id);

    Optional<Token> findByToken(String token);
}
