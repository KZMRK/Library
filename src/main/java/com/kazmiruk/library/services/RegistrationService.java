package com.kazmiruk.library.services;

import com.kazmiruk.library.util.exception.UserAlreadyExistAuthenticationException;
import com.kazmiruk.library.dto.RegisterRequest;
import com.kazmiruk.library.entities.User;
import com.kazmiruk.library.enums.Role;
import com.kazmiruk.library.mapper.UserMapper;
import com.kazmiruk.library.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public void register(RegisterRequest registerRequest) throws UserAlreadyExistAuthenticationException {
        boolean userWithGivenEmailExist = userRepository.findByEmail(registerRequest.getEmail()).isPresent();
        if (userWithGivenEmailExist) {
            throw new UserAlreadyExistAuthenticationException(String.format("The user with email \"%s\" already exists", registerRequest.getEmail()));
        }
        User user = userMapper.toEntity(registerRequest);
        user.setRole(Role.ROLE_READER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
