package ru.sem.gateway.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import ru.sem.clientbase.client.dto.ClientResponseDto;

import java.nio.charset.StandardCharsets;


import static ru.sem.Config.BASE_URL;

@Component
public class ClientWebClient {

    private final WebClient webClient;

    public String url;

    public ClientWebClient(@Value("${clientBase.url:" + BASE_URL + ":8080}") String url) {
        this.url = url;
        webClient = WebClient.create(url);
    }

    public ClientDto createClient(ClientDto clientDto) {
        return webClient
                .post()
                .uri("/client")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(clientDto))
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().is5xxServerError()) {
                        return Mono.error(new RuntimeException("Server Error"));
                    } else if (clientResponse.statusCode().is4xxClientError()) {
                        return Mono.error(new RuntimeException("Client Error"));
                    } else {
                        return clientResponse.bodyToMono(ClientDto.class);
                    }
                })
                .block();
    }

    public Flux<ClientResponseDto> getClient(String request) {

        String encodedRequest = UriUtils.encodeQuery(request, StandardCharsets.UTF_8);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(BASE_URL + ":8080/clients");
        uriBuilder.queryParam("query", encodedRequest);  // Используем закодированную строку запроса

        System.out.println("Мы в getClient в webcliente");
        System.out.println(uriBuilder.build().toUri());

        return webClient.get()
                .uri(uriBuilder.build().toUri())
                .retrieve() // Используйте retrieve() вместо exchangeToFlux()
                .bodyToFlux(ClientResponseDto.class);
    }

}
