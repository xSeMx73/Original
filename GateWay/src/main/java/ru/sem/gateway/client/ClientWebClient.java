package ru.sem.gateway.client;

import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Component
public class ClientWebClient {

    private final WebClient webClient;
    public String url;

    public ClientWebClient(@Value("${clientBase.url:http://192.168.1.201:8080}") String url) {
        this.url = url;
        webClient = WebClient.create(url);
    }

    public ClientDto createClient(ClientDto clientDto) {

        log.info("<--- GATEWAY ClientWebClient Добавление клиента {}", clientDto);
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
        log.info("<--- GATEWAY ClientWebClient Получение клиента по запросу {}", request);
        String encodedRequest = UriUtils.encodeQuery(request, StandardCharsets.UTF_8);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(url + "/clients");
        uriBuilder.queryParam("query", encodedRequest);

        return webClient
                .get()
                .uri(uriBuilder.build().toUri())
                .retrieve()
                .bodyToFlux(ClientResponseDto.class);
    }

    public ClientResponseDto getClientForId(Long id) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(url + "/clients/id/" + id);

        log.info("<--- GATEWAY ClientWebClient Получение клиента по ID: {}", id);

        return webClient
                .get()
                .uri(uriBuilder.build().toUri())
                .retrieve()
                .bodyToMono(ClientResponseDto.class)
                .block();
    }

    public void deleteClient(Long clientId) {
        log.info("<--- GATEWAY ClientWebClient Удаление клиента по ID: {}", clientId);
        webClient
                .delete()
                .uri(url + "/clients")
                .header("clientId", String.valueOf(clientId))
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    public ClientDto updateClient(ClientDto clientDto) {
        log.info("<--- GATEWAY ClientWebClient Обновление клиента {}", clientDto);

       return webClient
                .patch()
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
}
