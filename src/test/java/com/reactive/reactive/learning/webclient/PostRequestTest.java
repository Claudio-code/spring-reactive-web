package com.reactive.reactive.learning.webclient;

import com.reactive.reactive.learning.ApplicationTests;
import com.reactive.reactive.learning.dto.MultiplyRequestDTO;
import com.reactive.reactive.learning.dto.ResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.test.StepVerifier;

public class PostRequestTest extends ApplicationTests {

    @Autowired
    private WebClient webClient;

    @Test
    void stepVerifierFluxTest() {
        var responseDTOMono = webClient.post()
                .uri("reactive/math")
                .bodyValue(buildMultiplyRequestDTO(5, 2))
                .retrieve()
                .bodyToMono(ResponseDTO.class);

        StepVerifier.create(responseDTOMono)
                .expectNextCount(1)
                .verifyComplete();
    }

    private MultiplyRequestDTO buildMultiplyRequestDTO(int a, int b) {
        return new MultiplyRequestDTO(a, b);
    }

}
