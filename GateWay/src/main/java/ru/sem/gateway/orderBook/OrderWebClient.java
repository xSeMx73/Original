package ru.sem.gateway.orderBook;

import io.micrometer.common.util.internal.logging.InternalLogger;
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
import java.util.List;

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

    public Flux<OrderResponseDto> getAllOrders() {
        log.info("<--- GATEWAY OrderWebClient Получение всех заказов");
        return webClient
                .get()
                .uri(url + "/orders")
                .retrieve()
                .bodyToFlux(OrderResponseDto.class);
    }

    public Flux<OrderResponseDto> getClientsOrders() {
        log.info("<--- GATEWAY OrderWebClient Получение заказов клиентов");
        return webClient
                .get()
                .uri(url + "/orders/clientsOrders")
                .retrieve()
                .bodyToFlux(OrderResponseDto.class);
    }

    public Flux<OrderResponseDto> getSortByDealerOrders() {
        log.info("<--- GATEWAY OrderWebClient Получение отсортированных по поставщику заказов");
        return webClient
                .get()
                .uri(url + "/orders/sortByDealerOrders")
                .retrieve()
                .bodyToFlux(OrderResponseDto.class);
    }
}
