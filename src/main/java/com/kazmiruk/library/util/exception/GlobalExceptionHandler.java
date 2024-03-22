package com.kazmiruk.library.util.exception;

import com.kazmiruk.library.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(
            MethodArgumentNotValidException exp
    ) {
        Map<String, List<String>> errors = new HashMap<>();

        exp.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            List<String> fieldErrors;
            if (errors.containsKey(fieldName)) {
                fieldErrors = errors.get(fieldName);
            } else {
                fieldErrors = new ArrayList<>();
            }
            fieldErrors.add(errorMessage);
            errors.put(fieldName, fieldErrors);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyExistAuthenticationException.class)
    public ResponseEntity<ErrorDto> handleUserAlreadyExistException(
            UserAlreadyExistAuthenticationException exp
    ) {
        ErrorDto errorDto = new ErrorDto(
                HttpStatus.BAD_REQUEST.value(),
                exp.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }
}
