package com.kazmiruk.library.mapper;

import com.kazmiruk.library.model.dto.BookDto;
import com.kazmiruk.library.model.entities.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {AuthorMapper.class, CategoryMapper.class})
public interface BookMapper {

    @Mapping(target = "id", ignore = true)
    Book toEntity(BookDto bookDto);

    BookDto toDto(Book book);

    @Mapping(target = "id", ignore = true)
    void updateEntity(@MappingTarget Book book, BookDto bookDto);

}
