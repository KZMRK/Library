package com.kazmiruk.library.mapper;

import com.kazmiruk.library.dto.BookRequest;
import com.kazmiruk.library.dto.BookResponse;
import com.kazmiruk.library.entities.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AuthorMapper.class, CategoryMapper.class})
public interface BookMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "authors", ignore = true)
    Book toEntity(BookRequest bookDTO);

    BookResponse toResponse(Book book);
}
