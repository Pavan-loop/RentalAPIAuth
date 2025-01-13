package com.example.RentalManagementApi.security.config;

import com.example.RentalManagementApi.errorHandling.UserNotFoundException;
import com.example.RentalManagementApi.user.User;
import com.example.RentalManagementApi.user.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByEmail(username)
                .orElseThrow(() -> {
                    log.error("User not Found");
                    return new UserNotFoundException(username);
                });
    }
}
