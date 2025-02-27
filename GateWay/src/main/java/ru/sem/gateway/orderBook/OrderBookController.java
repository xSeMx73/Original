package ru.sem.gateway.orderBook;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;


@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:9090"})
@RequestMapping(path = "/orders")
public class OrderBookController {

    private final OrderWebClient orderWebClient;

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderDto orderDto) {
     log.info("<---GATEWAY ORDER CONTROLLER Попытка добавления заказа {}", orderDto);
     OrderResponseDto orderResponseDto = orderWebClient.createOrder(orderDto);
     log.info("<---GATEWAY ORDER CONTROLLER заказ добавлен {}", orderResponseDto);
     return ResponseEntity.status(HttpStatus.CREATED).body(orderResponseDto);
    }

    @GetMapping
    public Flux<OrderResponseDto> getAllOrders() {
        log.info("<---GATEWAY ORDER CONTROLLER Попытка получения всех заказов");
        return orderWebClient.getAllOrders();
    }

    @GetMapping("/clientsOrders")
    public Flux<OrderResponseDto> getClientsOrders() {
        log.info("<---GATEWAY ORDER CONTROLLER Попытка получения заказов клиентов");
        return orderWebClient.getClientsOrders();
    }

    @GetMapping("/sortByDealerOrders")
    public Flux<OrderResponseDto> getSortByDealerOrders() {
        log.info("<---GATEWAY ORDER CONTROLLER Попытка получения отсортированных по поставщику заказов");
        return orderWebClient.getSortByDealerOrders();
    }
}
