package com.kazmiruk.library.mapper;

import com.kazmiruk.library.model.dto.AuthorDto;
import com.kazmiruk.library.model.entities.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    @Mapping(target = "books", ignore = true)
    Author toEntity(AuthorDto authorDto);

    AuthorDto toDto(Author author);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "books", ignore = true)
    void updateEntity(@MappingTarget Author author, AuthorDto authorDto);
}
