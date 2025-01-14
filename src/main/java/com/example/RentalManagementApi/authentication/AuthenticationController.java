package com.example.RentalManagementApi.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<String> createUser(
            @RequestBody RegisterRequest request,
            @RequestParam String role
    ) {
        authenticationService.createUser(request, role);
        return new ResponseEntity<>("User Created", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUserAndSendToken(
            @RequestBody LoginRequest request
    ) {
       return new ResponseEntity<>(authenticationService.loginUserAndSendToken(request), HttpStatus.ACCEPTED);
    }
}
