package com.reactive.reactive.learning.webclient;

import com.reactive.reactive.learning.ApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.test.StepVerifier;

public class QueryParamsTest extends ApplicationTests {

    @Autowired
    private WebClient webClient;

    @Test
    void queryParamsTest() {
        final var queryString = "http://localhost:8080/jobs/search?count={count}&page={page}";
        final var uri = UriComponentsBuilder
                .fromUriString(queryString)
                .build(10, 20);
        final var integerFlux = webClient
                .get()
                .uri(uri)
                .retrieve()
                .bodyToFlux(Integer.class)
                .doOnNext(System.out::println);

        StepVerifier.create(integerFlux)
                .expectNextCount(2)
                .verifyComplete();
    }

}
