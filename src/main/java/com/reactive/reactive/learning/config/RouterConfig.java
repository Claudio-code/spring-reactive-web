package com.reactive.reactive.learning.config;

import com.reactive.reactive.learning.dto.InputFailedValidationResponseDTO;
import com.reactive.reactive.learning.exception.InputValidationException;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@AllArgsConstructor
@Configuration
public class RouterConfig {
    private RequestHandler requestHandler;

    @Bean
    public RouterFunction<ServerResponse> pathDefaultRouterFunction() {
        return RouterFunctions
                .route()
                .path("router", this::serverResponseRouterFunction)
                .build();
    }

    private RouterFunction<ServerResponse> serverResponseRouterFunction() {
        return RouterFunctions
                .route()
                .GET("square/{input}", requestHandler::squareHandler)
                .GET("table/{input}", requestHandler::multiplicationTable)
                .GET("table/{input}/stream", requestHandler::multiplicationTableStream)
                .POST("multiply", requestHandler::multiply)
                .onError(InputValidationException.class, exceptionHandler())
                .build();
    }

    private BiFunction<Throwable, ServerRequest, Mono<ServerResponse>> exceptionHandler() {
        return (err, req) -> {
            final var exception = (InputValidationException) err;
            final var inputFailedValidationResponseDTO = InputFailedValidationResponseDTO
                    .builder()
                    .input(exception.getInput())
                    .message(exception.getMessage())
                    .errorCode(exception.getErrorCode())
                    .build();
            return ServerResponse
                    .badRequest()
                    .bodyValue(inputFailedValidationResponseDTO);
        };
    }
}
