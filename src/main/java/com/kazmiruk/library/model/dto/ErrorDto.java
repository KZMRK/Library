package com.kazmiruk.library.model.dto;

import java.time.LocalDateTime;

public record ErrorDto<T>(LocalDateTime timeStamp, int statusCode, T message) {
}
