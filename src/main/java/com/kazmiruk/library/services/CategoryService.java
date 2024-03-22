package com.kazmiruk.library.services;

import com.kazmiruk.library.dto.CategoryResponse;
import com.kazmiruk.library.mapper.CategoryMapper;
import com.kazmiruk.library.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    public List<CategoryResponse> getAllCategories() {
        return categoryMapper.toResponse(categoryRepository.findAll());
    }
}
