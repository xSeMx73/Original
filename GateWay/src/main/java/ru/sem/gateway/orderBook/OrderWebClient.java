package ru.sem.gateway.orderBook;

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
import ru.sem.gateway.client.ClientDto;

import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class OrderWebClient {

    private final WebClient webClient;
    public String url;

    public OrderWebClient(@Value("${orderBook.url:http://192.168.1.135:8181}") String url) {
        this.url = url;
        webClient = WebClient.create(url);
    }



    public OrderResponseDto createOrder(OrderDto orderDto) {
        log.info("<--- GATEWAY OrderWebClient Создание заказа {}", orderDto);
        return webClient
                .post()
                .uri(url + "/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(orderDto))
                .exchangeToMono(orderResponse -> {
                    if (orderResponse.statusCode().is5xxServerError()) {
                        return Mono.error(new RuntimeException("Server Error"));
                    } else if (orderResponse.statusCode().is4xxClientError()) {
                        return Mono.error(new RuntimeException("Client Error"));
                    } else {
                        return orderResponse.bodyToMono(OrderResponseDto.class);
                    }
                })
                .block();
    }
}
