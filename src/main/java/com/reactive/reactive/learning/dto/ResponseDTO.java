package com.reactive.reactive.learning.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@ToString
public class ResponseDTO {
    private Date date = new Date();
    @Setter
    private int output;

    public ResponseDTO(int output) {
        this.output = output;
    }
}
