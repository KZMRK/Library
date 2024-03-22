package com.kazmiruk.library.web;

import com.kazmiruk.library.dto.AuthorRequest;
import com.kazmiruk.library.dto.AuthorResponse;
import com.kazmiruk.library.services.AuthorService;
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

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<AuthorResponse>> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @PostMapping
    public ResponseEntity<AuthorResponse> addAuthor(
            @RequestBody AuthorRequest authorDTO
    ) {
        return ResponseEntity.ok(authorService.addAuthor(authorDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponse> updateAuthor(
            @PathVariable Long id,
            @RequestBody AuthorRequest authorRequest
    ) {
        return ResponseEntity.ok(authorService.editAuthor(id, authorRequest));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteAuthor(
            @PathVariable Long id
    ) {
        authorService.deleteAuthor(id);
    }
}
