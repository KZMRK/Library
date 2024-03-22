package com.kazmiruk.library.util.exception;

import javax.naming.AuthenticationException;

public class UserAlreadyExistAuthenticationException extends AuthenticationException {
    public UserAlreadyExistAuthenticationException(String message) {
        super(message);
    }
}
