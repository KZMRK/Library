package com.kazmiruk.library.repositories;

import com.kazmiruk.library.entities.Book;
import com.kazmiruk.library.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findAllByReader(User reader);
}
