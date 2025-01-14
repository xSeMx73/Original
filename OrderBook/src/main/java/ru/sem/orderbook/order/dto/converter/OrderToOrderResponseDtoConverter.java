package ru.sem.orderbook.order.dto.converter;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.sem.orderbook.order.dto.OrderResponseDto;
import ru.sem.orderbook.order.model.Order;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Component
public class OrderToOrderResponseDtoConverter implements Converter<Order, OrderResponseDto> {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Override
    public OrderResponseDto convert(Order source) {
        return OrderResponseDto.builder()
                .id(source.getId())
                .brand(source.getBrand())
                .article(source.getArticle())
                .price(source.getPrice())
                .dealer(source.getDealer())
                .info(source.getInfo())
                .manager(source.getManager())
                .productName(source.getProductName())
                .quantity(source.getQuantity())
                .createTime(source.getCreateTime())
                .deliveryTime(source.getDeliveryTime())
                .build();
    }
}
