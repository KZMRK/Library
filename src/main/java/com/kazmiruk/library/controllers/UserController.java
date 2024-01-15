package com.kazmiruk.library.controllers;

import com.kazmiruk.library.entities.User;
import com.kazmiruk.library.repositories.UserRepository;
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

    @GetMapping("/lateReturn")
    public ResponseEntity<?> getUsersLateReturnBook() {
        List<User> users = userService.getUsersLateReturnBook();
        return ResponseEntity.ok(users);
    }
}
