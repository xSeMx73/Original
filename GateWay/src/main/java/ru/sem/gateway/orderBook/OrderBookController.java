package ru.sem.gateway.orderBook;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/orders")
@CrossOrigin(origins = "http://192.168.1.135:8080")
public class OrderBookController {

    private final OrderWebClient orderWebClient;

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderDto orderDto) {
     log.info("<---GATEWAY ORDER CONTROLLER Попытка добавления заказа {}", orderDto);
     OrderResponseDto orderResponseDto = orderWebClient.createOrder(orderDto);
     log.info("<---GATEWAY ORDER CONTROLLER заказ добавлен {}", orderResponseDto);
     return ResponseEntity.status(HttpStatus.CREATED).body(orderResponseDto);
    }
}
