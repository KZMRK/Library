package com.kazmiruk.library.repositories;

import com.kazmiruk.library.model.entities.Book;
import com.kazmiruk.library.model.entities.ReaderBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReaderBookRepository extends JpaRepository<ReaderBook,Long> {

    Optional<ReaderBook> findByBook(Book book);
}
