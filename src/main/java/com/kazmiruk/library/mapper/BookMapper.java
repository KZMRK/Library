package com.kazmiruk.library.mapper;

import com.kazmiruk.library.dto.BookRequest;
import com.kazmiruk.library.dto.BookResponse;
import com.kazmiruk.library.entities.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {AuthorMapper.class, CategoryMapper.class})
public interface BookMapper {

    Book toEntity(BookRequest bookDTO);

    BookResponse toResponse(Book book);
}
