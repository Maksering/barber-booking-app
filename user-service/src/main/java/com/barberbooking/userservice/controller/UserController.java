package com.barberbooking.userservice.controller;

import com.barberbooking.userservice.dto.*;
import com.barberbooking.userservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/me")
    @Operation(summary = "Получение информации о сам себе")
    public ResponseEntity<UserDto> getCurrentUser (){
        UserDto userDto = userService.getCurrentUser();
        return ResponseEntity.ok(userDto);
    }

    @PutMapping("/me/update/name")
    @Operation(summary = "Обновление текущего имени")
    public ResponseEntity<UserDto> updateCurrentUserName(@Valid @RequestBody CurrentUserNameChangeRequest request) {
        UserDto userDto = userService.updateCurrentUserName(request);
        return ResponseEntity.ok(userDto);
    }

    @PutMapping("/me/update/phone")
    @Operation(summary = "Обновление текущего телефона")
    public ResponseEntity<UserDto> updateCurrentUserPhone(@Valid @RequestBody CurrentUserPhoneChangeRequest request) {
        UserDto userDto = userService.updateCurrentUserPhone(request);
        return ResponseEntity.ok(userDto);
    }

    @PutMapping("/{id}/update/name")
    @Operation(summary = "Обновление  имени любого пользователя")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> updateAnyUserName(@Valid @RequestBody AnyUserNameChangeRequest request) {
        UserDto userDto = userService.updateAnyUserName(request);
        return ResponseEntity.ok(userDto);
    }

    @PutMapping("/{id}/update/phone")
    @Operation(summary = "Обновление текущего имени")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> updateAnyUserPhone(@Valid @RequestBody AnyUserPhoneChangeRequest request) {
        UserDto userDto = userService.updateAnyUserPhone(request);
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
