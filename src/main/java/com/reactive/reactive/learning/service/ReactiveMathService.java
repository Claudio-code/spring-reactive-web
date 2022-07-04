package com.reactive.reactive.learning.service;

import com.reactive.reactive.learning.dto.ResponseDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class ReactiveMathService {
    public Mono<ResponseDTO> findSquare(int input) {

        return Mono.fromSupplier(() -> input * input)
                .map(ResponseDTO::new);
    }

    public Flux<ResponseDTO> multiplicationTable(int input) {
        return Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(item -> System.out.println("reactive-math-service processing :" + item))
                .map(ResponseDTO::new);
    }
}
