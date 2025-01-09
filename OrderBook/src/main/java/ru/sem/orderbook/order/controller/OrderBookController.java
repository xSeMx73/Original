package ru.sem.orderbook.order.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sem.orderbook.order.dto.OrderDto;
import ru.sem.orderbook.order.dto.OrderResponseDto;
import ru.sem.orderbook.order.service.OrderBookService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/orders")
@CrossOrigin(origins = "http://192.168.1.201:8181")
public class OrderBookController {

    private final OrderBookService orderBookService;

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderDto orderDto) {
     log.info("<---OrderBooK ORDER CONTROLLER Попытка добавления заказа {}", orderDto);
     OrderResponseDto orderResponseDto = orderBookService.createOrder(orderDto);
     log.info("<---OrderBooK ORDER CONTROLLER заказ добавлен {}", orderResponseDto);
     return ResponseEntity.status(HttpStatus.CREATED).body(orderResponseDto);
    }
}
