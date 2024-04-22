package com.kazmiruk.library.model.exception;

import com.kazmiruk.library.type.ErrorMessageType;

public class ApplicationException extends RuntimeException {

    public ApplicationException(ErrorMessageType messageType, Object... args) {
        super(messageType.getMessage().formatted(args));
    }
}
