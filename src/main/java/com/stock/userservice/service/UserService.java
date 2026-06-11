package com.stock.userservice.service;

import com.stock.userservice.dto.UserResponse;

import java.math.BigDecimal;

public interface UserService {

    UserResponse getCurrentUser();

    UserResponse getUserById(Long id);

    void credit(Long userId, BigDecimal amount);

    void debit(Long userId, BigDecimal amount);

    void reserveAmount(Long userId, BigDecimal amount);

    void releaseAmount(Long userId, BigDecimal amount);

    void consumeReservedAmount(Long userId, BigDecimal amount);
}
