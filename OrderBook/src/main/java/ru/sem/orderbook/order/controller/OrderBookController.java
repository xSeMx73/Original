package ru.sem.orderbook.order.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sem.orderbook.order.dto.OrderDto;
import ru.sem.orderbook.order.dto.OrderResponseDto;
import ru.sem.orderbook.order.service.OrderBookService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/orders")
public class OrderBookController {

    private final OrderBookService orderBookService;

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderDto orderDto) {
     log.info("<---OrderBooK ORDER CONTROLLER Попытка добавления заказа {}", orderDto);
     OrderResponseDto orderResponseDto = orderBookService.createOrder(orderDto);
     log.info("<---OrderBooK ORDER CONTROLLER заказ добавлен {}", orderResponseDto);
     return ResponseEntity.status(HttpStatus.CREATED).body(orderResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getOrders() {
        log.info("<---OrderBooK ORDER CONTROLLER Попытка получения заказов");
        List<OrderResponseDto> orders = orderBookService.getOrders();
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }
}
