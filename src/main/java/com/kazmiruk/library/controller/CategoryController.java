package com.kazmiruk.library.controller;

import com.kazmiruk.library.model.dto.CategoryDto;
import com.kazmiruk.library.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryRequest) {
        return ResponseEntity.ok(categoryService.createCategory(categoryRequest));
    }

    @GetMapping
    public ResponseEntity<Set<CategoryDto>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(
            Integer id,
            @RequestBody CategoryDto categoryRequest
    ) {
        CategoryDto categoryResponse = categoryService.updateCategory(id, categoryRequest);
        return ResponseEntity.ok(categoryResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(
            @RequestParam Integer id
    ) {
        categoryService.deleteCategory(id);
    }

}
