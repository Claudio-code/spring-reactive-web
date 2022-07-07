package com.reactive.reactive.learning.exception;

import lombok.Getter;

@Getter
public class InputValidationException extends RuntimeException {
    private static final String MESSAGE = "allowed range is 10 - 20";
    private final int errorCode = 100;
    private final int input;

    public InputValidationException(int input) {
        super(MESSAGE);
        this.input = input;
    }
}
