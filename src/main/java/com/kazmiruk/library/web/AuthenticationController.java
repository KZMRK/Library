package com.kazmiruk.library.web;

import com.kazmiruk.library.dto.AuthenticationRequest;
import com.kazmiruk.library.dto.AuthenticationResponse;
import com.kazmiruk.library.dto.RegisterRequest;
import com.kazmiruk.library.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    /* Ability to login new users; */
    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest request
    ) {
        try {
            return ResponseEntity.ok(service.register(request));
        } catch (RuntimeException e) {
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /* Ability to authorize the user in the application; */
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
