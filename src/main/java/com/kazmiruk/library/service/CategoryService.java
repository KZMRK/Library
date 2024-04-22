package com.kazmiruk.library.service;

import com.kazmiruk.library.mapper.CategoryMapper;
import com.kazmiruk.library.model.dto.CategoryDto;
import com.kazmiruk.library.model.entities.Category;
import com.kazmiruk.library.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    @Transactional
    public Set<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toSet());
    }

    @Transactional
    public CategoryDto createCategory(CategoryDto categoryRequest) {
        Category category = categoryMapper.toEntity(categoryRequest);
        category = categoryRepository.save(category);
        return categoryMapper.toDto(category);
    }

    @Transactional
    public CategoryDto updateCategory(
            Integer categoryId,
            @RequestBody CategoryDto categoryRequest) {
        Category category = categoryRepository.getOneById(categoryId);
        categoryMapper.updateEntity(category, categoryRequest);
        return categoryMapper.toDto(category);
    }

    public void deleteCategory(Integer categoryId) {
        Category category = categoryRepository.getOneById(categoryId);
        categoryRepository.delete(category);
    }
}
