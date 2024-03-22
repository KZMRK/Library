package com.kazmiruk.library.mapper;

import com.kazmiruk.library.dto.CategoryResponse;
import com.kazmiruk.library.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryResponse toResponse(Category category);


    @Mapping(target = "books", ignore = true)
    Category toEntity(CategoryResponse categoryRequest);

    List<CategoryResponse> toResponse(List<Category> categories);
}
