package com.kazmiruk.library.repositories;

import com.kazmiruk.library.entities.Book;
import com.kazmiruk.library.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    /*List<Book> findAllByReader(User reader);*/

    @Query("SELECT b FROM Book b JOIN b.categories c WHERE c.id in :categoryIds")
    List<Book> findAllByCategoriesId(@Param("categoryIds") List<Integer> categoryIds);

    /*List<Book> findAllByReaderDateOfBirthGreaterThan(LocalDate date);

    List<Book> findAllByReaderDateOfBirthLessThan(LocalDate date);

    List<Book> findALlByReaderDateOfBirthBetween(LocalDate start, LocalDate end);*/
}
