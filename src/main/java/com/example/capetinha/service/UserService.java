package com.example.capetinha.service;

import com.example.capetinha.config.converter.UserDTOConverter;
import com.example.capetinha.domain.model.User;
import com.example.capetinha.domain.repository.UserRepository;
import com.example.capetinha.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserDTOConverter userDTOConverter;

    public UserDTO salvarUser(UserDTO userDTO){
        var user = User.builder()
                .senha(passwordEncoder.encode(userDTO.getSenha()))
                .login(userDTO.getLogin())
                .build();
        userRepository.save(user);
        return userDTOConverter.fromUser(user);
    }

    public List<UserDTO> buscarTodos(Pageable pageable){
        return userRepository.findAll(pageable).stream()
                .map(user -> userDTOConverter.fromUser(user))
                        .collect(Collectors.toList());
    }
    public UserDTO buscarById(Long id){
        return userRepository.findById(id).map(user ->
                userDTOConverter.fromUser(user)).orElse(null);
    }
}
