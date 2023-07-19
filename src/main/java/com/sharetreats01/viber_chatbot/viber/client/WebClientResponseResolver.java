package com.sharetreats01.viber_chatbot.viber.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class WebClientResponseResolver {
    public <T> T messageResolve(WebClient.ResponseSpec response, Class<T> className) {
        return handleError(handleHTTPStatus(response, className)).block();
    }

    private <T> Mono<T> handleHTTPStatus(WebClient.ResponseSpec response, Class<T> className) {
        return response
                .onStatus(HttpStatus::isError, clientResponse -> {
                            log.error("Error occurred: " + clientResponse.statusCode());
                            return clientResponse.createException().flatMap(Mono::error);
                        }
                )
                .bodyToMono(className);
    }

    private <T> Mono<T> handleError(Mono<T> response) {
        return response.onErrorResume(error -> {
            if (error instanceof WebClientResponseException) {
                return Mono.error(new RuntimeException(error.getMessage()));
            } else {
                return Mono.error(error);
            }
        });
    }
}
