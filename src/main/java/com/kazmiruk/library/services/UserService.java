package com.kazmiruk.library.services;

import com.kazmiruk.library.entities.User;
import com.kazmiruk.library.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getUsersLateReturnBook() {
        List<User> readers = userRepository.findByBooksIsNotNull();
        return readers.stream()
                .filter(reader ->
                        reader.getBooks().stream()
                                .anyMatch(book -> {
                                    Period period = Period.between(
                                            book.getBorrowedAt(),
                                            LocalDate.now()
                                    );
                                    int monthPassed = period.getYears() * 12 + period.getMonths();
                                    System.out.println(monthPassed);
                                    return monthPassed >= 1;
                                })
                ).toList();
    }
}
