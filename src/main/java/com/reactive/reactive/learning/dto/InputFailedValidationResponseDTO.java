package com.reactive.reactive.learning.dto;

import lombok.Builder;

@Builder
public class InputFailedValidationResponseDTO {
    private int errorCode;
    private  int input;
    private String message;
}
