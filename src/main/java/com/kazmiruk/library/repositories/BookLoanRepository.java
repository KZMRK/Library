package com.kazmiruk.library.repositories;

import com.kazmiruk.library.entities.Book;
import com.kazmiruk.library.entities.BookLoan;
import com.kazmiruk.library.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookLoanRepository extends JpaRepository<BookLoan,Integer> {
    @Query(
            "SELECT bl.book, COUNT(bl.book) as loanCount " +
            "FROM  BookLoan bl WHERE bl.borrowAt >= :lastYear " +
            "GROUP BY bl.book " +
            "ORDER BY loanCount DESC LIMIT 5"
    )
    List<Book> findTop5BooksLastYear(@Param("lastYear")LocalDate lastYear);

    @Query("SELECT DISTINCT bl.reader FROM BookLoan bl WHERE bl.borrowAt > :lastYear")
    List<User> findDistinctReaderIdsByBorrowAtAfter(@Param("lastYear") LocalDate lastYear);
}
