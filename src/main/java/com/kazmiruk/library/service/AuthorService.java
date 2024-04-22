package com.kazmiruk.library.service;

import com.kazmiruk.library.model.dto.AuthorDto;
import com.kazmiruk.library.model.dto.BookDto;
import com.kazmiruk.library.model.entities.Author;
import com.kazmiruk.library.mapper.AuthorMapper;
import com.kazmiruk.library.mapper.BookMapper;
import com.kazmiruk.library.model.entities.Book;
import com.kazmiruk.library.repositories.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    private final AuthorMapper authorMapper;

    private final BookMapper bookMapper;

    @Transactional
    public AuthorDto createAuthor(AuthorDto authorRequest) {
        Author author = authorMapper.toEntity(authorRequest);
        return authorMapper.toDto(authorRepository.save(author));
    }

    @Transactional(readOnly = true)
    public Set<AuthorDto> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream().map(authorMapper::toDto).collect(Collectors.toSet());
    }

    @Transactional
    public AuthorDto updateAuthor(Long authorId, AuthorDto authorDto) {
        Author author = authorRepository.getOneById(authorId);
        authorMapper.updateEntity(author, authorDto);
        return authorMapper.toDto(author);
    }

    @Transactional
    public void deleteAuthor(Long authorId) {
        Author author = authorRepository.getOneById(authorId);
        authorRepository.delete(author);
    }

    @Transactional(readOnly = true)
    public Set<BookDto> getAllAuthorBooks(Long authorId) {
        List<Book> books = authorRepository.findBooksByAuthorId(authorId);
        return books.stream().map(bookMapper::toDto).collect(Collectors.toSet());
    }
}
