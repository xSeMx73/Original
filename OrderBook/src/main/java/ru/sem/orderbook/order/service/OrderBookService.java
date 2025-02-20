package ru.sem.orderbook.order.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.sem.orderbook.order.dto.OrderDto;
import ru.sem.orderbook.order.dto.OrderResponseDto;
import ru.sem.orderbook.order.model.Order;
import ru.sem.orderbook.order.repository.OrderBookRepository;
import ru.sem.orderbook.order.service.orderBuilder.OrderBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderBookService {

    @Qualifier("mvcConversionService")
    private final ConversionService converter;
    private final OrderBuilder orderBuilder;
    private final OrderBookRepository orderRepository;
    private final RestTemplate restTemplate = new RestTemplate();


    public OrderResponseDto createOrder(OrderDto orderDto) {
    OrderDto tempOrderDto = orderBuilder.disBuilder(orderDto);
        Order order = orderRepository
                .save(Objects.requireNonNull(converter.convert(tempOrderDto, Order.class)));
        return converter.convert(order, OrderResponseDto.class);
    }

    public List<OrderResponseDto> getAllOrders() {
        List<Order> orders = orderRepository.findAllOrders();
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

    @Scheduled(fixedRate = 600000)
    private void autoSetDelivered() {
        long startTime = System.currentTimeMillis();
        int countCheckOrders = 0;
        List<Order> ordersForUpdate = orderRepository.findOrdersForUpdateDelivery()
                .stream()
                .filter(o -> o.getCreateTime().isAfter(LocalDateTime.now().minusMonths(1)))
                .toList();
        for(Order order : ordersForUpdate) {
           Integer oldCount = getPartCount(order.getArticle(), order.getBrand(), dateBuilder(order.getCreateTime()));
           Integer newCount = getPartCount(order.getArticle(), order.getBrand(),
                   dateBuilder(LocalDateTime.now()));
           countCheckOrders++;
           if (oldCount != null && newCount != null) {
             if (newCount > oldCount) {
               order.setIsDelivered(true);
               orderRepository.save(order);
             }
           }
        }
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        log.info("<---!!! Время выполнения обновления статуса ордеров {} ms", duration);
        log.info("<---!!! Обработано записей {}", countCheckOrders);
    }

    public String dateBuilder(LocalDateTime date) {
        return date.toString()
                .replaceAll("-","")
                .replaceAll("T","")
                .replaceAll(" ","")
                .replaceAll(":","")
                .substring(0, 14);
    }

    public Integer getPartCount(String article, String brand, String date) {
        String url = "http://87.76.0.58/Service/hs/original/remains";
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                .queryParam("Article", article)
                .queryParam("Brand", brand)
                .queryParam("Date", date);

        try {
            Thread.sleep(100);
            String response = restTemplate.getForObject(builder.toUriString(), String.class);
            if (response != null) {
                return Integer.valueOf(response.trim());
            } else return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
