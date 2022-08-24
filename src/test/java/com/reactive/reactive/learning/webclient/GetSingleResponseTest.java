package com.reactive.reactive.learning.webclient;

import com.reactive.reactive.learning.ApplicationTests;
import com.reactive.reactive.learning.dto.ResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

public class GetSingleResponseTest extends ApplicationTests {

    @Autowired
    private WebClient webClient;

    @Test
    void blockTest() {
        var response = webClient.get()
                .uri("reactive/math/square/{number}", 5)
                .retrieve()
                .bodyToMono(ResponseDTO.class)
                .block();

        System.out.println(response);
    }

}
