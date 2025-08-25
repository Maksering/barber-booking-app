package com.barberbooking.userservice.controller;

import com.barberbooking.userservice.dto.JwtResponse;
import com.barberbooking.userservice.dto.LoginRequest;
import com.barberbooking.userservice.dto.SignUpRequest;
import com.barberbooking.userservice.dto.UserDto;
import com.barberbooking.userservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/signup")
    @Operation(summary = "Регистрация нового пользователя")
    public ResponseEntity<UserDto> signUp(@Valid @RequestBody SignUpRequest request) {
        UserDto userDto = userService.createUser(request);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/login")
    @Operation(summary = "Аутентификация пользователя")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest request) {
        JwtResponse response = userService.authenticate(request);
        return ResponseEntity.ok(response);
    }
}
