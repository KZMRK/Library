package com.kazmiruk.library.controllers;

import com.kazmiruk.library.entities.Book;
import com.kazmiruk.library.entities.User;
import com.kazmiruk.library.enums.AgeCategory;
import com.kazmiruk.library.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<?> getBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @PutMapping("/borrow")
    public ResponseEntity<?> borrowBook(
        @RequestParam List<Integer> bookIds,
        @AuthenticationPrincipal User user
    ) {
        if (bookIds.size() > 7) {
            return ResponseEntity.badRequest()
                    .body("You can borrow up to 7 books at a time");
        }
        List<Book> borrowedBooks = bookService.borrowBooksById(bookIds, user);
        return ResponseEntity.ok(borrowedBooks);
    }

    @PutMapping("{bookId}")
    public ResponseEntity<?> returnBook(
            @PathVariable Integer bookId,
            @AuthenticationPrincipal User user
    ) {
        Book returnedBook = bookService.returnBook(bookId, user);
        return ResponseEntity.ok(returnedBook);
    }

    @GetMapping("/borrowed")
    public ResponseEntity<?> getBorrowedBooks(@AuthenticationPrincipal User user) {
        List<Book> book = bookService.getBooksByReader(user);
        return ResponseEntity.ok(book);
    }

    @GetMapping("/byCategories")
    public ResponseEntity<?> getBooksByCategories(@RequestParam List<Integer> categoriesIds) {
        List<Book> books = bookService.getBooksByCategories(categoriesIds);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/borrowed/{ageCategory}")
    public ResponseEntity<?> getBooksByAgeRange(@PathVariable AgeCategory ageCategory) {
        List<Book> books = bookService.getBooksByReaderAge(ageCategory);
        return ResponseEntity.ok(books);
    }

}
