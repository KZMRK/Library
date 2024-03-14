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
        bookService.deleteBook(id);
    }

    /* Ability to borrow books;*/
    /*@PutMapping("/borrow")
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
    }*/

    /* Ability to return books; */
    /*@PutMapping("/return/{bookId}")
    public ResponseEntity<?> returnBook(
            @PathVariable Integer bookId,
            @AuthenticationPrincipal User user
    ) {
        Book returnedBook = bookService.returnBook(bookId, user);
        return ResponseEntity.ok(returnedBook);
    }*/

    /* Ability to view borrowed books; */
    /*@GetMapping("/borrowed")
    public ResponseEntity<?> getBorrowedBooks(@AuthenticationPrincipal User user) {
        List<Book> book = bookService.getBooksByReader(user);
        return ResponseEntity.ok(book);
    }*/

    /* Ability to view books by one or multiple categories *//*
    @GetMapping("/byCategories")
    public ResponseEntity<?> getBooksByCategories(@RequestParam List<Integer> categoriesIds) {
        List<Book> books = bookService.getBooksByCategories(categoriesIds);
        return ResponseEntity.ok(books);
    }*/

    /* Ability to view borrowed books for any of these ages: */
    /*@GetMapping("/borrowed/{ageCategory}")
    public ResponseEntity<?> getBooksByAgeRange(@PathVariable AgeCategory ageCategory) {
        List<Book> books = bookService.getBooksByReaderAge(ageCategory);
        return ResponseEntity.ok(books);
    }*/

    /* Ability to view the top 5 most popular books in the library for the last year; *//*
    @GetMapping("/top5")
    public ResponseEntity<?> getTop5BooksLastYear() {
        List<Book> books = bookService.getTop5BooksLastYear();
        return ResponseEntity.ok(books);
    }*/
}
