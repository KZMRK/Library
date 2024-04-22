package com.kazmiruk.library.controller;

import com.kazmiruk.library.model.dto.ReaderDto;
import com.kazmiruk.library.service.ReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/readers")
@RequiredArgsConstructor
public class ReaderController {

    private final ReaderService readerService;

    @GetMapping
    public ResponseEntity<Set<ReaderDto>> getAllReaders() {
        Set<ReaderDto> readerRequests = readerService.getAllUsers();
        return ResponseEntity.ok(readerRequests);
    }

    @GetMapping("/term-expired")
    public ResponseEntity<Set<ReaderDto>> getReadersWithReturnBookTermExpired() {
        Set<ReaderDto> readerRequests = readerService.getReadersWithReturnBookTermExpired();
        return ResponseEntity.ok(readerRequests);
    }

    @GetMapping("/without-books")
    public ResponseEntity<Set<ReaderDto>> getReadersWithoutBooks() {
        Set<ReaderDto> readerRequest = readerService.getReadersWithoutBooks();
        return ResponseEntity.ok(readerRequest);
    }
}
