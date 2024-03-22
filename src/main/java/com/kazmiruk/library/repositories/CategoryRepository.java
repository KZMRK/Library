package com.kazmiruk.library.repositories;

import com.kazmiruk.library.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Short> {
    Optional<Category> findByName(String name);
}
