package com.kazmiruk.library.mapper;

import com.kazmiruk.library.model.dto.ReaderDto;
import com.kazmiruk.library.model.dto.RegisterDto;
import com.kazmiruk.library.model.dto.UserDto;
import com.kazmiruk.library.model.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "role", ignore = true)
    @Mapping(target = "id", ignore = true)
    User toEntity(RegisterDto registerDto);

    UserDto toDto(User user);

    @Mapping(target = "dateOfBirth", ignore = true)
    User toEntity(UserDto userDto);

    ReaderDto toReaderDto(User user);
}
