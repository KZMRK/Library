package com.kazmiruk.library.controller;

import com.kazmiruk.library.model.dto.BookDto;
import com.kazmiruk.library.model.dto.ReaderBookDto;
import com.kazmiruk.library.model.dto.UserDto;
import com.kazmiruk.library.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookDto> createBook(
            @Valid @RequestBody BookDto bookRequest
    ) {
        BookDto bookResponse = bookService.createBook(bookRequest);
        return ResponseEntity.ok(bookResponse);
    }

    @GetMapping
    public ResponseEntity<Set<BookDto>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(
            @Valid @RequestBody BookDto bookRequest, @PathVariable Long id) {
        return ResponseEntity.ok(bookService.updateBook(id, bookRequest));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteBook(
            @PathVariable Long id
    ) {
        bookService.deleteBookById(id);
    }

    @PutMapping("/{id}/borrow")
    public ResponseEntity<ReaderBookDto> borrowBook(
            @AuthenticationPrincipal UserDto user,
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(bookService.borrowBook(id, user));
    }

    @PutMapping("/{id}/return")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void returnBook(
            @AuthenticationPrincipal UserDto user,
            @PathVariable Long id
    ) {
        bookService.returnBook(id, user);
    }
}
