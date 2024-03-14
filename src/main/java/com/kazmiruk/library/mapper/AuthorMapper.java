package com.kazmiruk.library.mapper;

import com.kazmiruk.library.dto.AuthorRequest;
import com.kazmiruk.library.dto.AuthorResponse;
import com.kazmiruk.library.entities.Author;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {


    Author toEntity(AuthorRequest authorRequest);

    AuthorResponse toResponse(Author author);

    List<AuthorResponse> toResponses(List<Author> authors);
}
