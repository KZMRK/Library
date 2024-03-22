package com.kazmiruk.library.dto;

import java.time.LocalDateTime;

public record ErrorDto(int status, String message, LocalDateTime timeStamp) {
}
