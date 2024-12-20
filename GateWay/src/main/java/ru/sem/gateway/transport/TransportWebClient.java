package ru.sem.gateway.transport;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class TransportWebClient {

    public String url;

   private final WebClient webClient;

    public TransportWebClient(@Value("${clientBase.url:http://192.168.1.201:8080}") String url) {
        this.url = url;
        webClient = WebClient.create(url);
    }


    public TransportDto addTransport(TransportDto transportData, Long id) {
        log.info("<---GATEWAY TransportWebClient Добавление транспорта {}, клиенту с ID: {}", transportData, id);

        return webClient
                .post()
                .uri(url + "/transport")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(transportData))
                .header("id", String.valueOf(id))
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().is5xxServerError()) {
                        return Mono.error(new RuntimeException("Server Error"));
                    } else if (clientResponse.statusCode().is4xxClientError()) {
                        return Mono.error(new RuntimeException("Client Error"));
                    } else {
                        return clientResponse.bodyToMono(TransportDto.class);
                    }
                })
                .block();
    }

    public void deleteTransport(Long id) {
        log.info("<---GATEWAY TransportWebClient Удаление транспорта с запросом {}", url + "/transport/" + id);
        webClient
                .delete()
                .uri(url + "/transport")
                .header("id", String.valueOf(id))
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
