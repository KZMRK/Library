package com.kazmiruk.library.repositories;

import com.kazmiruk.library.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
