package com.kazmiruk.library.model.exception;

import com.kazmiruk.library.type.ErrorMessageType;

public class NotFoundException extends ApplicationException {

    public NotFoundException(ErrorMessageType message, Number id) {
        super(message, id);
    }

}
