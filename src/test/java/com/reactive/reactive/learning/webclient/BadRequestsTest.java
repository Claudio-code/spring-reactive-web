package com.reactive.reactive.learning.webclient;

import com.reactive.reactive.learning.ApplicationTests;
import com.reactive.reactive.learning.dto.InputFailedValidationResponseDTO;
import com.reactive.reactive.learning.dto.ResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class BadRequestsTest extends ApplicationTests {

    @Autowired
    private WebClient webClient;

    @Test
    void badRequestTest() {
        final var responseMono = webClient
                .get()
                .uri("reactive/math/table/{number}/throw", 5)
                .retrieve()
                .bodyToMono(ResponseDTO.class)
                .doOnNext(System.out::println)
                .doOnError(err -> System.out.println(err.getMessage()));

        StepVerifier.create(responseMono)
                .verifyError(WebClientResponseException.NotFound.class);
    }

    @Test
    void badRequestExchangeTest() {
        // exchange = retrieve + additional info http status code

        final var responseMono = webClient
                .get()
                .uri("reactive/math/square/{number}/throw", 5)
                .exchangeToMono(this::exchange)
                .doOnNext(System.out::println)
                .doOnError(err -> System.out.println(err.getMessage()));

        StepVerifier.create(responseMono)
                .expectNextCount(1)
                .verifyComplete();
    }

    private Mono<Object> exchange(ClientResponse clientResponse) {
        if (clientResponse.rawStatusCode() == HttpStatus.NOT_FOUND.value()) {
            return clientResponse.bodyToMono(InputFailedValidationResponseDTO.class);
        }
        return clientResponse.bodyToMono(ResponseDTO.class);
    }

}
