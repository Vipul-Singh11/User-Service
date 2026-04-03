package com.stock.userservice.service.impl;

import com.stock.userservice.dto.UserResponse;
import com.stock.userservice.entity.User;
import com.stock.userservice.exception.ResourceNotFoundException;
import com.stock.userservice.repository.UserRepository;
import com.stock.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    /**
     * Fetch currently authenticated user using SecurityContext
     */
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

    /**
     * Fetch user by ID (Admin use case)
     */
    @Override
    public UserResponse getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + id));

        return mapToResponse(user);
    }

    /**
     * Entity → DTO mapping
     */
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