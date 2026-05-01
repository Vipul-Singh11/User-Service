package com.stock.userservice.service.impl;

import com.stock.userservice.dto.UserResponse;
import com.stock.userservice.entity.User;
import com.stock.userservice.exception.ResourceNotFoundException;
import com.stock.userservice.repository.UserRepository;
import com.stock.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse getCurrentUser() {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        return mapToResponse(user);
    }

    @Override
    public UserResponse getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + id));

        return mapToResponse(user);
    }

    // 🔥 WALLET: CREDIT
    @Override
    public void credit(Long userId, BigDecimal amount) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + userId));

        user.setWalletBalance(user.getWalletBalance().add(amount));

        userRepository.save(user);
    }

    // 🔥 WALLET: DEBIT
    @Override
    public void debit(Long userId, BigDecimal amount) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + userId));

        if (user.getWalletBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

        user.setWalletBalance(user.getWalletBalance().subtract(amount));

        userRepository.save(user);
    }

    private UserResponse mapToResponse(User user) {

        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .walletBalance(user.getWalletBalance())
                .role(user.getRole().name())
                .build();
    }
}
