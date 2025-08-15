package com.barberbooking.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignUpRequest {
    @NotBlank @Email
    private String email;
    @NotBlank @Size(min=8)
    private String password;
    @NotBlank
    private String name;

    private String phone; //OPTIONAL
    private String role;  //CLIENT, MASTER, ADMIN

}
