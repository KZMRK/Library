package com.kazmiruk.library.repositories;

import com.kazmiruk.library.model.entities.Author;
import com.kazmiruk.library.model.entities.Book;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRepository extends BaseRepository<Author, Long> {

    @Query("SELECT a.books FROM Author a WHERE a.id = :authorId")
    List<Book> findBooksByAuthorId(Long authorId);

}
