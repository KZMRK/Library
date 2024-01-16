package com.kazmiruk.library.controllers;

import com.kazmiruk.library.entities.User;
import com.kazmiruk.library.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    /* Ability to view users who are late in
     returning books (a book can be borrowed for
     a max of one month) */
    @GetMapping("/lateReturn")
    public ResponseEntity<?> getUsersLateReturnBook() {
        List<User> users = userService.getUsersLateReturnBook();
        return ResponseEntity.ok(users);
    }

    /* Ability to view users who have not
    borrowed any of the books in the last year;*/
    @GetMapping("/notBorrowLastYear")
    public ResponseEntity<?> getUsersHaveNotBorrowBookLastYear() {
        List<User> users = userService.getUsersHaveNotBorrowBookLastYear();
        return ResponseEntity.ok(users);
    }
}
