package com.reactive.reactive.learning.webclient;

import com.reactive.reactive.learning.ApplicationTests;
import com.reactive.reactive.learning.dto.ResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.test.StepVerifier;

public class GetMultiResponseTest extends ApplicationTests {

    @Autowired
    private WebClient webClient;

    @Test
    void stepVerifierFluxTest() {
        var responseDTOFlux = webClient.get()
                .uri("reactive/math/table/{number}", 5)
                .retrieve()
                .bodyToFlux(ResponseDTO.class)
                .doOnNext(System.out::println);

        StepVerifier.create(responseDTOFlux)
                .expectNextCount(10)
                .verifyComplete();
    }

    @Test
    void stepVerifierFluxStreamTest() {
        var responseDTOFlux = webClient.get()
                .uri("reactive/math/table/{number}/stream", 5)
                .retrieve()
                .bodyToFlux(ResponseDTO.class)
                .doOnNext(System.out::println);

        StepVerifier.create(responseDTOFlux)
                .expectNextCount(10)
                .verifyComplete();
    }
}
