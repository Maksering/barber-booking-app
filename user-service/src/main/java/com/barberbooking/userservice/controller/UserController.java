package com.barberbooking.userservice.controller;

import com.barberbooking.userservice.dto.UserDto;
import com.barberbooking.userservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/id/{id}")
    @Operation(summary = "Получение пользователя по ID")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('CLIENT') and @userService.getCurrentUserId() == #id)")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto userDto = userService.getUserById(id);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Получение пользователя по email")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('CLIENT') and #email == principal.username)")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        UserDto userDto = userService.getUserByEmail(email);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/debug/principal")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> debugPrincipal(Authentication authentication) {
        return ResponseEntity.ok(Map.of(
                "name", authentication.getName(),
                "authorities", authentication.getAuthorities(),
                "principal", authentication.getPrincipal(),
                "details", authentication.getDetails(),
                "class", authentication.getPrincipal().getClass()
        ));
    }
}
