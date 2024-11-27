package ru.sem.gateway.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;
import ru.sem.clientbase.client.dto.ClientResponseDto;

@Component
public class ClientWebClient {

    private final WebClient webClient;

    public String url;

    public ClientWebClient(@Value("${clientBase.url:http://localhost:9090}") String url) {
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

    public ClientResponseDto getClient (String request) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString("http://clientBase:9090/client");
        uriBuilder.queryParam("uri", request);
        return webClient
                .get()
                .uri(uriBuilder.build().toUri())
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().is5xxServerError()) {
                        return Mono.error(new RuntimeException("Server Error"));
                    } else if (clientResponse.statusCode().is4xxClientError()) {
                        return Mono.error(new RuntimeException("Client Error"));
                    } else {
                        return clientResponse.bodyToMono(ClientResponseDto.class);
                    }
                })
                .block();
    }

}
