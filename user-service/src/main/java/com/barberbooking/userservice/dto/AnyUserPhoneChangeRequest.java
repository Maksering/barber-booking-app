package com.barberbooking.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AnyUserPhoneChangeRequest {

    @NotBlank @Email
    private String emailOfUserForChange;

    @NotBlank
    private String newPhone;
}
