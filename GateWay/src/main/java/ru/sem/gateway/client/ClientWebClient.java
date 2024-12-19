package ru.sem.gateway.client;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import ru.sem.clientbase.client.dto.ClientResponseDto;

import java.nio.charset.StandardCharsets;

@Getter
@Component
public class ClientWebClient {

    private final WebClient webClient;

    public String url;

    public ClientWebClient(@Value("${clientBase.url:http://192.168.1.201:8080}") String url) {
        this.url = url;
        webClient = WebClient.create(url);
    }

    public ClientDto createClient(ClientDto clientDto) {
        System.out.println("Мы в createClient в webclient");

        return webClient
                .post()
                .uri(url + "/clients")
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
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(url + "/clients");
        uriBuilder.queryParam("query", encodedRequest);

        System.out.println("Мы в getClient в webClient");
        System.out.println(uriBuilder.build().toUri());

        return webClient
                .get()
                .uri(uriBuilder.build().toUri())
                .retrieve()
                .bodyToFlux(ClientResponseDto.class);
    }

    public ClientResponseDto getClientForId(Long id) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(url + "/clients/id/" + id);

        System.out.println("Мы в getClientForId в webClient");
        System.out.println(uriBuilder.build().toUri());

        return webClient
                .get()
                .uri(uriBuilder.build().toUri())
                .retrieve()
                .bodyToMono(ClientResponseDto.class)
                .block();
    }
}
