package ru.sem.orderbook.order.dto.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.sem.orderbook.order.dto.OrderResponseDto;
import ru.sem.orderbook.order.model.Order;

@RequiredArgsConstructor
@Component
public class OrderToOrderResponseDtoConverter implements Converter<Order, OrderResponseDto> {


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
                .isDelivered(source.getIsDelivered())
                .build();
    }
}
