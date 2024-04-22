package com.kazmiruk.library.service;

import com.kazmiruk.library.model.dto.RegisterDto;
import com.kazmiruk.library.model.entities.User;
import com.kazmiruk.library.model.exception.BadRequestException;
import com.kazmiruk.library.type.RoleType;
import com.kazmiruk.library.mapper.UserMapper;
import com.kazmiruk.library.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Transactional
    public void register(RegisterDto registerDto) {
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new BadRequestException(
                    String.format("The user with email \"%s\" already exists", registerDto.getEmail())
            );
        }
        User user = userMapper.toEntity(registerDto);
        user.setRole(RoleType.ROLE_READER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
