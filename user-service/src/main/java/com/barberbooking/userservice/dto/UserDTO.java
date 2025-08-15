package com.barberbooking.userservice.dto;

import com.barberbooking.userservice.model.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String phone;
    private Role role;
}
