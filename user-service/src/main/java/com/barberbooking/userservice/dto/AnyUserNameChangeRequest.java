package com.barberbooking.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AnyUserNameChangeRequest {

    @NotBlank @Email
    private String emailOfUserForChange;

    @NotBlank
    private String newName;
}
