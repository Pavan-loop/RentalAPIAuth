package com.example.RentalManagementApi.authentication;

import com.example.RentalManagementApi.security.jwt.JwtService;
import com.example.RentalManagementApi.token.Token;
import com.example.RentalManagementApi.token.TokenRepo;
import com.example.RentalManagementApi.token.TokenType;
import com.example.RentalManagementApi.user.Roles;
import com.example.RentalManagementApi.user.User;
import com.example.RentalManagementApi.user.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final TokenRepo tokenRepo;

    public void createUser(RegisterRequest request, String role) {

        Roles addRole = Roles.valueOf(role.toUpperCase());

        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Collections.singleton(addRole))
                .build();

        userRepo.save(user);
    }

    public String loginUserAndSendToken(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        User user = userRepo.findByEmail(request.getEmail()).orElseThrow();
        String token = jwtService.generateToken(userDetails);
        var addToken = Token.builder()
                .token(token)
                .tokenType(TokenType.BEARER)
                .user(user)
                .isExpired(false)
                .isRevoked(false)
                .build();
        revokingToken(user);
        tokenRepo.save(addToken);
        return token;
    }

    private void revokingToken(User user) {
        var allTokens = tokenRepo.getValidTokenOfTheUser(user.getId());
        if (allTokens.isEmpty())
            return;
        allTokens.forEach(t -> {
            t.setExpired(true);
            t.setRevoked(true);
        });

        tokenRepo.saveAll(allTokens);

    }
}
