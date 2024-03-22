package com.kazmiruk.library.services;

import com.kazmiruk.library.dto.AuthorRequest;
import com.kazmiruk.library.dto.AuthorResponse;
import com.kazmiruk.library.dto.BookResponse;
import com.kazmiruk.library.entities.Author;
import com.kazmiruk.library.mapper.AuthorMapper;
import com.kazmiruk.library.mapper.BookMapper;
import com.kazmiruk.library.repositories.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    private final AuthorMapper authorMapper;

    private final BookMapper bookMapper;

    public List<AuthorResponse> getAllAuthors() {
        return authorMapper.toResponses(authorRepository.findAll());
    }

    public AuthorResponse addAuthor(AuthorRequest authorRequest) {
        Author author = authorMapper.toEntity(authorRequest);
        return authorMapper.toResponse(authorRepository.save(author));
    }

    public AuthorResponse editAuthor(Long id, AuthorRequest authorRequest) {
        Author newAuthor = authorMapper.toEntity(authorRequest);
        Author updatedAuthor = authorRepository.findById(id)
                .map(author -> {
                    author.setFirstName(authorRequest.getFirstName());
                    author.setLastName(authorRequest.getLastName());
                    return authorRepository.save(author);
                })
                .orElseGet(() -> authorRepository.save(newAuthor));
        return authorMapper.toResponse(updatedAuthor);
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

    public List<BookResponse> getBooksByAuthorId(Long authorId) {
        return authorRepository
                .findById(authorId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Author with id %d not found)", authorId)))
                .getBooks().stream()
                .map(bookMapper::toResponse)
                .toList();
    }
}
