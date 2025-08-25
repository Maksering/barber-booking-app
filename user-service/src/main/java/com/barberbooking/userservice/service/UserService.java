package com.barberbooking.userservice.service;


import com.barberbooking.userservice.dto.JwtResponse;
import com.barberbooking.userservice.dto.LoginRequest;
import com.barberbooking.userservice.dto.SignUpRequest;
import com.barberbooking.userservice.dto.UserDto;
import com.barberbooking.userservice.entity.Role;
import com.barberbooking.userservice.entity.User;
import com.barberbooking.userservice.repository.UserRepository;
import com.barberbooking.userservice.security.JwtTokenProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public JwtResponse authenticate(LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (BadCredentialsException e) {
            log.error("Invalid login credentials for email: {}", request.getEmail());
            throw new RuntimeException("Invalid email or password");
        }

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String jwtToken = jwtTokenProvider.generateToken(user);

        return new JwtResponse(
                jwtToken,
                "Bearer",
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getRole().name()
        );
    }

    @Transactional
    public UserDto createUser(SignUpRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setName(request.getName());
        user.setPhone(request.getPhone());

        try {
            user.setRole(Role.valueOf(request.getRole().toUpperCase()));
        } catch (IllegalArgumentException e) {
            user.setRole(Role.CLIENT);
        }

        User savedUser = userRepository.save(user);

        return convertToDto(savedUser);
    }

    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return convertToDto(user);
    }

    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return convertToDto(user);
    }

    private UserDto convertToDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setPhone(user.getPhone());
        dto.setRole(user.getRole().name());
        return dto;
    }
}
