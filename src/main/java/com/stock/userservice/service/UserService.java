package com.stock.userservice.service;

import com.stock.userservice.dto.UserResponse;

public interface UserService {

    UserResponse getCurrentUser();

    UserResponse getUserById(Long id);
}