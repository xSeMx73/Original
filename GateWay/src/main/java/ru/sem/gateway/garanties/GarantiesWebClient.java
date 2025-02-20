package ru.sem.gateway.garanties;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

    public GarantRequestDto getGarantiesById(int id) {
        log.info("<--- GATEWAY WEBCLIENT Запрос рекламаций с id {}", id);
        return webClient
                .get()
                .uri(url + "/garanties/" + id)
                .retrieve()
                .bodyToMono(GarantRequestDto.class)
                .block();
    }

    public GarantRequestDto updateGarantRequest(GarantRequestDto request) {
        log.info("<--- GATEWAY WEBCLIENT Обновление рекламации {}", request);
        return webClient
                .patch()
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

    public byte[] createZakaz(Long id, Long normHours, Long price, String job) {
        log.info("<--- GATEWAY WEBCLIENT Запрос заказ-наряда по рекламации с id {}", id);
        return webClient
                .get()
                .uri(url + "/garanties/zakaz/" + id + "/"+ normHours + "/" + price)
                .header("job", job)
                .retrieve()
                .bodyToMono(byte[].class)
                .block();
    }

    public byte[] createDefekt(Long id) {
        log.info("<--- GATEWAY WEBCLIENT Запрос акта-дефектовки по рекламации с id {}", id);
        return webClient
                .get()
                .uri(url + "/garanties/defekt/" + id)
                .retrieve()
                .bodyToMono(byte[].class)
                .block();
    }
}
