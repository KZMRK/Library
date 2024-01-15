package com.kazmiruk.library.services;

import com.kazmiruk.library.entities.Book;
import com.kazmiruk.library.entities.User;
import com.kazmiruk.library.enums.AgeCategory;
import com.kazmiruk.library.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> borrowBooksById(List<Integer> bookIds, User reader) {
        List<Book> books = bookRepository.findAllById(bookIds);
        books = books.stream().filter(book -> book.getReader() == null).toList();
        books.forEach(book -> {
            book.setReader(reader);
            book.setBorrowedAt(LocalDate.now());
        });
        return bookRepository.saveAll(books);
    }

    public Book returnBook(Integer bookId, User user) {
        Book book = bookRepository.findById(bookId).get();
        if (book.getReader().equals(user)) {
            book.setReader(null);
            book.setBorrowedAt(null);
        }
        return bookRepository.save(book);
    }

    public List<Book> getBooksByReader(User user) {
        return bookRepository.findAllByReader(user);
    }

    public List<Book> getBooksByCategories(List<Integer> categoryIds) {
        return bookRepository.findAllByCategoriesId(categoryIds);
    }

    public List<Book> getBooksByReaderAge(AgeCategory ageCategory) {
        List<Book> books;
        switch (ageCategory) {
            case UNDER_18 ->
                books = bookRepository.findAllByReaderDateOfBirthGreaterThan(
                        LocalDate.now().minusYears(18)
                );
            case BETWEEN_18_AND_23 ->
                books = bookRepository.findALlByReaderDateOfBirthBetween(
                        LocalDate.now().minusYears(24),
                        LocalDate.now().minusYears(18)
                );
            case BETWEEN_24_AND_35 ->
                books = bookRepository.findALlByReaderDateOfBirthBetween(
                        LocalDate.now().minusYears(36),
                        LocalDate.now().minusYears(24)
                );
            case BETWEEN_36_AND_50 ->
                books = bookRepository.findALlByReaderDateOfBirthBetween(
                        LocalDate.now().minusYears(51),
                        LocalDate.now().minusYears(36)
                );
            default ->
                books = bookRepository.findAllByReaderDateOfBirthLessThan(
                        LocalDate.now().minusYears(51)
                );
        }
        return books;
    }
}
