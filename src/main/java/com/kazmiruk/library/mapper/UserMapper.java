package com.kazmiruk.library.mapper;

import com.kazmiruk.library.dto.RegisterRequest;
import com.kazmiruk.library.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "role", ignore = true)
    @Mapping(target = "id", ignore = true)
    User toEntity(RegisterRequest registerRequest);
}
