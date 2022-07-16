package com.reactive.reactive.learning.controller;

import com.reactive.reactive.learning.dto.InputFailedValidationResponseDTO;
import com.reactive.reactive.learning.exception.InputValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ReactiveValidationHandler {

    @ExceptionHandler(InputValidationException.class)
    public ResponseEntity<InputFailedValidationResponseDTO> handleException(
            InputValidationException inputValidationException) {
        var response = InputFailedValidationResponseDTO
                .builder()
                .errorCode(inputValidationException.getErrorCode())
                .input(inputValidationException.getInput())
                .message(inputValidationException.getMessage())
                .build();
        return ResponseEntity.badRequest().body(response);
    }
}
