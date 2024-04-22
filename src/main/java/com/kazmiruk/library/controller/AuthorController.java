package com.kazmiruk.library.controller;

import com.kazmiruk.library.model.dto.AuthorDto;
import com.kazmiruk.library.model.dto.BookDto;
import com.kazmiruk.library.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<AuthorDto> createAuthor(
            @RequestBody @Valid AuthorDto authorDTO
    ) {
        return ResponseEntity.ok(authorService.createAuthor(authorDTO));
    }

    @GetMapping
    public ResponseEntity<Set<AuthorDto>> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDto> updateAuthor(
            @PathVariable Long id,
            @Valid @RequestBody AuthorDto authorRequest
    ) {
        return ResponseEntity.ok(authorService.updateAuthor(id, authorRequest));
    }

    @GetMapping("/{authorId}/books")
    public ResponseEntity<Set<BookDto>> getAllAuthorBooks(@PathVariable Long authorId) {
        return ResponseEntity.ok(authorService.getAllAuthorBooks(authorId));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteAuthor(
            @PathVariable Long id
    ) {
        authorService.deleteAuthor(id);
    }
}
