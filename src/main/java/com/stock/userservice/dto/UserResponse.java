package com.stock.userservice.dto;

import lombok.*;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long id;
    private String username;
    private String email;

    private BigDecimal walletBalance;

    private BigDecimal reservedBalance;

    private BigDecimal availableBalance;

    private String role;
}