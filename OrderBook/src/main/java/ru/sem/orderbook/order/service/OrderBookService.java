package ru.sem.orderbook.order.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import ru.sem.orderbook.order.dto.OrderDto;
import ru.sem.orderbook.order.dto.OrderResponseDto;
import ru.sem.orderbook.order.model.Order;
import ru.sem.orderbook.order.repository.OrderBookRepository;
import ru.sem.orderbook.order.service.orderBuilder.OrderBuilder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderBookService {

    private final OrderBuilder orderBuilder;
    private final OrderBookRepository orderRepository;

    @Qualifier("mvcConversionService")
    private final ConversionService converter;

    public OrderResponseDto createOrder(OrderDto orderDto) {
    OrderDto tempOrderDto = orderBuilder.disBuilder(orderDto);
        Order order = orderRepository
                .save(Objects.requireNonNull(converter.convert(tempOrderDto, Order.class)));
        return converter.convert(order, OrderResponseDto.class);
    }

    public List<OrderResponseDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll().reversed();
      return orders.stream().map(order -> converter.convert(order, OrderResponseDto.class))
              .collect(Collectors.toList());
    }

    public List<OrderResponseDto> getClientsOrders() {
        List<Order> clientsOrders = orderRepository.findClientsOrders();

        return clientsOrders.stream().map(order -> converter.convert(order, OrderResponseDto.class))
                .collect(Collectors.toList());
    }

    public List<OrderResponseDto> getSortByDealerOrders() {
        List<Order> sortOrders = orderRepository.findSortByDealerOrders();
        return sortOrders.stream().map(order -> converter.convert(order, OrderResponseDto.class))
                .collect(Collectors.toList());
    }
}
