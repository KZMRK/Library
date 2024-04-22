package com.kazmiruk.library.mapper;

import com.kazmiruk.library.model.dto.ReaderBookDto;
import com.kazmiruk.library.model.entities.ReaderBook;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class, BookMapper.class})
public interface ReaderBookMapper {

    ReaderBookDto toDto(ReaderBook readerBook);

}
