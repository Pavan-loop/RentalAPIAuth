package com.example.RentalManagementApi.security.config;

import com.example.RentalManagementApi.errorHandling.TokenNotFoundException;
import com.example.RentalManagementApi.token.TokenRepo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutHandlerImpl implements LogoutHandler {

    private final TokenRepo tokenRepo;

    @Override
    public void logout(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String jwt;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }

        jwt = authHeader.substring(7);
        var token = tokenRepo.findByToken(jwt)
                .orElseThrow(TokenNotFoundException::new);

        token.setRevoked(true);
        token.setExpired(true);
        tokenRepo.save(token);
    }
}
