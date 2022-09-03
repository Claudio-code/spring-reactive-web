package com.reactive.reactive.learning.service;

import com.reactive.reactive.learning.dto.ResponseDTO;
import com.reactive.reactive.learning.util.SleepUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class MathService {
    public ResponseDTO findSquare(final int input) {
        return new ResponseDTO(input * input);
    }

    public List<ResponseDTO> multiplicationTable(final int input) {
        return IntStream.rangeClosed(1, 10)
                .peek(SleepUtil::sleepSeconds)
                .peek(item -> System.out.println("math-service processing :" + item))
                .mapToObj(item -> new ResponseDTO(item * input))
                .toList();
    }
}
