package ru.sem.orderbook.order.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
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
    public ResponseEntity<List<OrderResponseDto>> getAllOrders() {
        log.info("<---OrderBooK ORDER CONTROLLER Попытка получения всех заказов");
        List<OrderResponseDto> orders = orderBookService.getAllOrders();
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }

    @GetMapping("/clientsOrders")
    public ResponseEntity<List<OrderResponseDto>> getClientsOrders() {
        log.info("<---OrderBooK ORDER CONTROLLER Попытка получения заказов клиентов");
        List<OrderResponseDto> clientsOrders = orderBookService.getClientsOrders();
        return ResponseEntity.status(HttpStatus.OK).body(clientsOrders);
    }

    @GetMapping("/sortByDealerOrders")
    public ResponseEntity<List<OrderResponseDto>> getSortByDealerOrders() {
        log.info("<---OrderBooK ORDER CONTROLLER Попытка получения отсортированных по поставщику заказов");
        List<OrderResponseDto> sortOrders = orderBookService.getSortByDealerOrders();
        return ResponseEntity.status(HttpStatus.OK).body(sortOrders);
    }
}
