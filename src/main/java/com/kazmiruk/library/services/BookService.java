package com.kazmiruk.library.services;

import com.kazmiruk.library.dto.BookRequest;
import com.kazmiruk.library.dto.BookResponse;
import com.kazmiruk.library.entities.Author;
import com.kazmiruk.library.entities.Book;
import com.kazmiruk.library.entities.Category;
import com.kazmiruk.library.mapper.BookMapper;
import com.kazmiruk.library.repositories.AuthorRepository;
import com.kazmiruk.library.repositories.BookRepository;
import com.kazmiruk.library.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toResponse)
                .toList();
    }

    public BookResponse addBookToLibrary(BookRequest bookRequest) {
        List<Author> authors = getAuthorsByIDs(bookRequest.getAuthorIDs());
        Set<Category> categories = getCategoriesByIDs(bookRequest.getCategoriesIDs());

        Book book = Book.builder()
                .name(bookRequest.getName())
                .description(bookRequest.getDescription())
                .authors(authors)
                .categories(categories)
                .numOfPages(bookRequest.getNumOfPages())
                .build();

        Book savedBook = bookRepository.save(book);
        return bookMapper.toResponse(savedBook);
    }

    private List<Author> getAuthorsByIDs(List<Long> authorIDs) {
        return authorIDs.stream()
                .map(authorRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    private Set<Category> getCategoriesByIDs(List<Short> categoriesIDs) {
        return categoriesIDs.stream()
                .map(categoryRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
    }

    public BookResponse editBook(Long id, BookRequest bookRequest) {
        Set<Category> categories = getCategoriesByIDs(bookRequest.getCategoriesIDs());
        List<Author> authors = getAuthorsByIDs(bookRequest.getAuthorIDs());
        Book updatedBook = bookRepository.findById(id)
                .map(book -> {
                    bookRepository.deleteById(id);
                    book.setName(bookRequest.getName());
                    book.setDescription(bookRequest.getDescription());
                    book.setAuthors(authors);
                    book.setCategories(categories);
                    book.setNumOfPages(bookRequest.getNumOfPages());
                    return bookRepository.save(book);
                })
                .orElseGet(() -> {
                    Book newBook = bookMapper.toEntity(bookRequest);
                    newBook.setAuthors(authors);
                    newBook.setCategories(categories);
                    return bookRepository.save(newBook);
                });

        return bookMapper.toResponse(updatedBook);
    }

    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }
}
