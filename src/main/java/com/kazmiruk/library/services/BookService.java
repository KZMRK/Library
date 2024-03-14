package com.kazmiruk.library.services;

import com.kazmiruk.library.dto.BookRequest;
import com.kazmiruk.library.dto.BookResponse;
import com.kazmiruk.library.entities.Author;
import com.kazmiruk.library.entities.Book;
import com.kazmiruk.library.entities.Category;
import com.kazmiruk.library.mapper.BookMapper;
import com.kazmiruk.library.repositories.AuthorRepository;
import com.kazmiruk.library.repositories.BookLoanRepository;
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

    private final BookLoanRepository bookLoanRepository;

    private final BookMapper bookMapper;

    private final AuthorRepository authorRepository;

    private final CategoryRepository categoryRepository;

    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toResponse)
                .toList();
    }

    /*public List<Book> borrowBooksById(List<Integer> bookIds, User reader) {
        List<Book> books = bookRepository.findAllById(bookIds);
        List<BookLoan> bookLoans = new LinkedList<>();
        books = books.stream().filter(book -> book.getReader() == null).toList();
        books.forEach(book -> {
            book.setReader(reader);
            book.setBorrowedAt(LocalDate.now());
            BookLoan bookLoan = BookLoan.builder()
                    .reader(reader)
                    .book(book)
                    .borrowAt(LocalDate.now())
                    .build();
            bookLoans.add(bookLoan);
        });
        bookLoanRepository.saveAll(bookLoans);
        return bookRepository.saveAll(books);
    }*/

    /*public Book returnBook(Integer bookId, User user) {
        Book book = bookRepository.findById(bookId).get();
        System.out.println(book.getName());
        if (Objects.equals(book.getReader().getId(), user.getId())) {
            book.setReader(null);
            book.setBorrowedAt(null);
        }
        return bookRepository.save(book);
    }*/

    /*public List<Book> getBooksByReader(User user) {
        return bookRepository.findAllByReader(user);
    }*/

   /* public List<Book> getBooksByCategories(List<Integer> categoryIds) {
        return bookRepository.findAllByCategoriesId(categoryIds);
    }*/

    /*public List<Book> getBooksByReaderAge(AgeCategory ageCategory) {
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
    }*/

    /*public List<Book> getTop5BooksLastYear() {
        LocalDate lastYear = LocalDate.now().minusYears(1);
        return bookLoanRepository.findTop5BooksLastYear(lastYear);
    }

    */
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
        Book newBook = bookMapper.toEntity(bookRequest);
        Book updatedBook = bookRepository.findById(id)
                .map(book -> {
                    book.setName(bookRequest.getName());
                    book.setDescription(bookRequest.getDescription());
                    book.setAuthors(getAuthorsByIDs(bookRequest.getAuthorIDs()));
                    book.setCategories(getCategoriesByIDs(bookRequest.getCategoriesIDs()));
                    book.setNumOfPages(bookRequest.getNumOfPages());
                    return bookRepository.save(book);
                })
                .orElseGet(() -> bookRepository.save(newBook));

        return bookMapper.toResponse(updatedBook);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
