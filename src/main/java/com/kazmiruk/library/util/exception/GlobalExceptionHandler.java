package com.kazmiruk.library.util.exception;

import com.kazmiruk.library.model.dto.ErrorDto;
import com.kazmiruk.library.model.exception.BadRequestException;
import com.kazmiruk.library.model.exception.NotFoundException;
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
    public ResponseEntity<ErrorDto<Map<String, List<String>>>> handleValidationErrors(
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

        ErrorDto<Map<String, List<String>>> errorDto = new ErrorDto<>(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                errors
        );

        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorDto<String>> handleBadRequestException(
            BadRequestException exp
    ) {
        ErrorDto<String> errorDto = new ErrorDto<>(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                exp.getMessage()
        );
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDto<String>> handleNotFoundException(
            NotFoundException exp
    ) {
        ErrorDto<String> errorDto = new ErrorDto<>(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                exp.getMessage()
        );
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }



}
