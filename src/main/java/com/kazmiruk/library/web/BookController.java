package com.kazmiruk.library.web;

import com.kazmiruk.library.dto.BookRequest;
import com.kazmiruk.library.dto.BookResponse;
import com.kazmiruk.library.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    /* Ability to view all books in the library */
    @GetMapping
    public ResponseEntity<List<BookResponse>> getBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @PostMapping
    public ResponseEntity<BookResponse> addBookToLibrary(
            @RequestBody BookRequest bookRequest
    ) {
        return ResponseEntity.ok(bookService.addBookToLibrary(bookRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> editBook(
            @RequestBody BookRequest bookRequest, @PathVariable Long id) {
        return ResponseEntity.ok(bookService.editBook(id, bookRequest));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteBook(
            @PathVariable Long id
    ) {
        bookService.deleteBookById(id);
    }
}
