package com.kazmiruk.library.controller;

import com.kazmiruk.library.model.dto.RegisterDto;
import com.kazmiruk.library.service.RegistrationService;
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
            @RequestBody @Valid RegisterDto registerDto
    ) {
        registrationService.register(registerDto);
    }

}
