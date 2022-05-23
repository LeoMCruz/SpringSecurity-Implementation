package com.example.capetinha.config.converter;

import com.example.capetinha.domain.model.User;
import com.example.capetinha.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserDTOConverter {
    public UserDTO fromUser(User user){
        return UserDTO.builder()
                .id(user.getId())
                .login(user.getLogin())
                .build();
    }
}
