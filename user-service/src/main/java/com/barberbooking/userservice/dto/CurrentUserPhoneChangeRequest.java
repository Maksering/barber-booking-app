package com.barberbooking.userservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CurrentUserPhoneChangeRequest {

    @NotBlank
    private String newPhone;
}
