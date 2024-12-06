package ru.sem.clientbase.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ConditionsNotMetException extends RuntimeException {
    public ConditionsNotMetException(String message) {
        super(message);
    }
}
