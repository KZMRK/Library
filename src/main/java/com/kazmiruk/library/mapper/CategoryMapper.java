package com.kazmiruk.library.mapper;

import com.kazmiruk.library.model.dto.CategoryDto;
import com.kazmiruk.library.model.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "books", ignore = true)
    Category toEntity(CategoryDto categoryDto);

    CategoryDto toDto(Category category);

    @Mapping(target = "books", ignore = true)
    void updateEntity(@MappingTarget Category category, CategoryDto categoryDto);

}
