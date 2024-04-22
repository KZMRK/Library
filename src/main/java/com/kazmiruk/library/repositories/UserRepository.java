package com.kazmiruk.library.repositories;

import com.kazmiruk.library.model.entities.User;
import com.kazmiruk.library.type.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findAllByRole(RoleType role);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("SELECT rb.reader FROM ReaderBook rb WHERE rb.returnAt < CURRENT DATE")
    List<User> findWithReturnBookTermExpired();

    @Query("SELECT r FROM User r WHERE (r.role = RoleType.ROLE_READER) " +
            "AND r NOT IN (SELECT DISTINCT rb.reader FROM ReaderBook rb)")
    List<User> findWithoutBooks();
}
