package com.barberbooking.userservice.mapper;

import com.barberbooking.userservice.dto.UserDTO;
import com.barberbooking.userservice.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public UserDTO mapToUserDTO(User entity){
        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setName(entity.getName());
        dto.setPhone(entity.getPhone());
        dto.setRole(entity.getRole());
        return dto;
    }

    public User mapToUser(UserDTO dto){
        User entity = new User();
        entity.setId(dto.getId());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setName(dto.getName());
        entity.setPhone(dto.getPhone());
        entity.setRole(dto.getRole());
        return entity;
    }
}
