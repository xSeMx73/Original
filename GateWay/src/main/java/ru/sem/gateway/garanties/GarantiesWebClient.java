package ru.sem.gateway.garanties;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.sem.gateway.orderBook.OrderResponseDto;

import java.util.List;

@Slf4j
@Service
public class GarantiesWebClient {

    private final WebClient webClient;
    String url;

    public GarantiesWebClient(@Value("${garantiesService.url:http://192.168.1.135:8282}") String url) {
        this.url = url;
        webClient = WebClient.create(url);
    }

    public GarantRequestDto createGarantRequest(GarantRequestDto request) {
        log.info("<--- GATEWAY WEBCLIENT Создание рекламации {}", request);
        return webClient
                .post()
                .uri(url + "/garanties")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(request))
                .exchangeToMono(orderResponse -> {
                    if (orderResponse.statusCode().is5xxServerError()) {
                        return Mono.error(new RuntimeException("Server Error"));
                    } else if (orderResponse.statusCode().is4xxClientError()) {
                        return Mono.error(new RuntimeException("Client Error"));
                    } else {
                        return orderResponse.bodyToMono(GarantRequestDto.class);
                    }
                })
                .block();
    }

    public Flux<GarantResponseDto> getGarantResponseList() {
        log.info("<--- GATEWAY WEBCLIENT Запрос рекламаций");
        return webClient
                .get()
                .uri(url + "/garanties")
                .retrieve()
                .bodyToFlux(GarantResponseDto.class);
    }
}
