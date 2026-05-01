package com.stock.userservice.controller;

import com.stock.userservice.dto.UserResponse;
import com.stock.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser() {
        return ResponseEntity.ok(userService.getCurrentUser());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    // 🔥 WALLET: CREDIT
    @PostMapping("/credit")
    public ResponseEntity<String> credit(@RequestParam Long userId,
                                         @RequestParam BigDecimal amount) {

        userService.credit(userId, amount);
        return ResponseEntity.ok("Wallet credited");
    }

    // 🔥 WALLET: DEBIT
    @PostMapping("/debit")
    public ResponseEntity<String> debit(@RequestParam Long userId,
                                        @RequestParam BigDecimal amount) {

        userService.debit(userId, amount);
        return ResponseEntity.ok("Wallet debited");
    }
}
