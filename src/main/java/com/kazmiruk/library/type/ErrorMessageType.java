package com.kazmiruk.library.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorMessageType {

    NOT_FOUND("Resource with id %d not found");

    private final String message;

}
