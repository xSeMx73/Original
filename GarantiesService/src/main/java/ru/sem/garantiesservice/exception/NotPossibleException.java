package ru.sem.garantiesservice.exception;

import lombok.Getter;

@Getter
public class NotPossibleException extends RuntimeException {

    public NotPossibleException(final String message) {
        super(message);
    }
}
