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

    @PostMapping("/credit")
    public ResponseEntity<String> credit(@RequestParam Long userId,
                                         @RequestParam BigDecimal amount) {

        userService.credit(userId, amount);
        return ResponseEntity.ok("Wallet credited");
    }

    @PostMapping("/debit")
    public ResponseEntity<String> debit(@RequestParam Long userId,
                                        @RequestParam BigDecimal amount) {

        userService.debit(userId, amount);
        return ResponseEntity.ok("Wallet debited");
    }

    @PostMapping("/reserve")
    public ResponseEntity<String> reserve(
            @RequestParam Long userId,
            @RequestParam BigDecimal amount) {

        userService.reserveAmount(userId, amount);

        return ResponseEntity.ok("Funds reserved");
    }

    @PostMapping("/release")
    public ResponseEntity<String> release(
            @RequestParam Long userId,
            @RequestParam BigDecimal amount) {

        userService.releaseAmount(userId, amount);

        return ResponseEntity.ok("Funds released");
    }

    @PostMapping("/consume")
    public ResponseEntity<String> consume(
            @RequestParam Long userId,
            @RequestParam BigDecimal amount) {

        userService.consumeReservedAmount(
                userId,
                amount);

        return ResponseEntity.ok(
                "Reserved funds consumed");
    }
}
