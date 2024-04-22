package com.kazmiruk.library.service;

import com.kazmiruk.library.mapper.ReaderBookMapper;
import com.kazmiruk.library.mapper.UserMapper;
import com.kazmiruk.library.model.dto.BookDto;
import com.kazmiruk.library.model.dto.ReaderBookDto;
import com.kazmiruk.library.model.dto.UserDto;
import com.kazmiruk.library.model.entities.Author;
import com.kazmiruk.library.model.entities.Book;
import com.kazmiruk.library.model.entities.Category;
import com.kazmiruk.library.mapper.BookMapper;
import com.kazmiruk.library.model.entities.ReaderBook;
import com.kazmiruk.library.model.exception.BadRequestException;
import com.kazmiruk.library.repositories.AuthorRepository;
import com.kazmiruk.library.repositories.BookRepository;
import com.kazmiruk.library.repositories.CategoryRepository;
import com.kazmiruk.library.repositories.ReaderBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    private final AuthorRepository authorRepository;

    private final CategoryRepository categoryRepository;

    private final ReaderBookRepository readerBookRepository;

    private final UserMapper userMapper;

    private final ReaderBookMapper readerBookMapper;

    @Transactional
    public BookDto createBook(BookDto boorRequest) {
        Book book = bookMapper.toEntity(boorRequest);
        book.setCategories(getCategories(book.getCategories()));
        book.setAuthors(getAuthors(book.getAuthors()));
        return bookMapper.toDto(bookRepository.save(book));
    }

    private Set<Author> getAuthors(Set<Author> authors) {
        return authors.stream()
                .map(author -> authorRepository.getOneById(author.getId())).
                collect(Collectors.toSet());
    }

    private Set<Category> getCategories(Set<Category> categories) {
        return categories.stream()
                .map(category -> categoryRepository.getOneById(category.getId()))
                .collect(Collectors.toSet());
    }

    @Transactional(readOnly = true)
    public Set<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toSet());
    }
    @Transactional
    public BookDto updateBook(Long bookId, BookDto bookRequest) {
        Book book = bookRepository.getOneById(bookId);
        bookMapper.updateEntity(book, bookRequest);
        book.setAuthors(getAuthors(book.getAuthors()));
        book.setCategories(getCategories(book.getCategories()));
        return bookMapper.toDto(book);
    }

    @Transactional
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    public ReaderBookDto borrowBook(Long bookId, UserDto user) {
        Book book = bookRepository.getOneById(bookId);
        Optional<ReaderBook> readerBookOpt = readerBookRepository.findByBook(book);
        if (readerBookOpt.isPresent()) {
            throw new BadRequestException(
                    String.format("Book already borrowed by %s %s",
                            readerBookOpt.get().getReader().getFirstName(),
                            readerBookOpt.get().getReader().getLastName())
            );
        }
        ReaderBook readerBook = new ReaderBook(
                userMapper.toEntity(user),
                book,
                LocalDate.now().plusWeeks(3)
        );
        readerBook = readerBookRepository.save(readerBook);
        return readerBookMapper.toDto(readerBook);
    }

    @Transactional
    public void returnBook(Long id, UserDto reader) {
        Book book = bookRepository.getOneById(id);
        ReaderBook readerBook = readerBookRepository.findByBook(book).orElseThrow(() ->
                new BadRequestException("Book don't borrowed")
        );
        if (readerBook.getReader().getId().equals(reader.getId())) {
            readerBookRepository.delete(readerBook);
        } else {
            throw new BadRequestException("You cannot return this book");
        }
    }
}
