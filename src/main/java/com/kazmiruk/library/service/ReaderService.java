package com.kazmiruk.library.service;

import com.kazmiruk.library.mapper.UserMapper;
import com.kazmiruk.library.model.dto.ReaderDto;
import com.kazmiruk.library.model.entities.User;
import com.kazmiruk.library.repositories.UserRepository;
import com.kazmiruk.library.type.RoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReaderService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public Set<ReaderDto> getAllUsers() {
        List<User> readers = userRepository.findAllByRole(RoleType.ROLE_READER);
        return readers.stream()
                .map(userMapper::toReaderDto)
                .collect(Collectors.toSet());
    }

    public Set<ReaderDto> getReadersWithReturnBookTermExpired() {
        List<User> readers = userRepository.findWithReturnBookTermExpired();
        return readers.stream()
                .map(userMapper::toReaderDto)
                .collect(Collectors.toSet());
    }

    public Set<ReaderDto> getReadersWithoutBooks() {
        List<User> readers = userRepository.findWithoutBooks();
        return  readers.stream()
                .map(userMapper::toReaderDto)
                .collect(Collectors.toSet());
    }
}
