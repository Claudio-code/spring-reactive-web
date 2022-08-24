package com.reactive.reactive.learning.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@ToString
@NoArgsConstructor
public class ResponseDTO {
    private final Date date = new Date();
    @Setter
    private int output;

    public ResponseDTO(int output) {
        this.output = output;
    }
}
