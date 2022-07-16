package com.reactive.reactive.learning.config;

import com.reactive.reactive.learning.dto.MultiplyRequestDTO;
import com.reactive.reactive.learning.dto.ResponseDTO;
import com.reactive.reactive.learning.service.ReactiveMathService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class RequestHandler {
    private ReactiveMathService reactiveMathService;

    public Mono<ServerResponse> squareHandler(ServerRequest serverRequest) {
        final var input = getInputInteger(serverRequest);
        final var responseMono = reactiveMathService.findSquare(input);
        return ServerResponse
                .ok()
                .body(responseMono, ResponseDTO.class);
    }

    public Mono<ServerResponse> multiplicationTable(ServerRequest serverRequest) {
        final var input = getInputInteger(serverRequest);
        final var responseFlux = reactiveMathService.multiplicationTable(input);
        return ServerResponse
                .ok()
                .body(responseFlux, ResponseDTO.class);
    }

    public Mono<ServerResponse> multiplicationTableStream(ServerRequest serverRequest) {
        final var input = getInputInteger(serverRequest);
        final var responseFlux = reactiveMathService.multiplicationTable(input);
        return ServerResponse
                .ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(responseFlux, ResponseDTO.class);
    }

    public Mono<ServerResponse> multiply(ServerRequest serverRequest) {
        final var multiplyRequestDTO = serverRequest.bodyToMono(MultiplyRequestDTO.class);
        final var responseMono = reactiveMathService.multiply(multiplyRequestDTO);
        return ServerResponse
                .ok()
                .body(responseMono, ResponseDTO.class);
    }

    private Integer getInputInteger(ServerRequest serverRequest) {
        final var inputServerRequest = serverRequest.pathVariable("input");
        return Integer.parseInt(inputServerRequest);
    }
}
