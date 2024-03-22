package com.kazmiruk.library.web;

import com.kazmiruk.library.dto.RegisterRequest;
import com.kazmiruk.library.services.RegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/register")
@RequiredArgsConstructor
public class RegisterController {

    private final RegistrationService registrationService;

    @SneakyThrows
    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void register(
            @RequestBody @Valid RegisterRequest registerRequest
    ) {
        registrationService.register(registerRequest);
    }

}
