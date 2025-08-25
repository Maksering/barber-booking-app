package com.barberbooking.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignUpRequest {
    @NotBlank @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    private String phone; //OPTIONAL

    private String role;  //CLIENT, MASTER, ADMIN
}
