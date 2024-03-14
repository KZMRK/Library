package com.kazmiruk.library.mapper;

import com.kazmiruk.library.dto.CategoryResponse;
import com.kazmiruk.library.entities.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryResponse toDto(Category category);
    Category toEntity(CategoryResponse categoryRequest);
    List<CategoryResponse> toResponse(List<Category> categories);
}
