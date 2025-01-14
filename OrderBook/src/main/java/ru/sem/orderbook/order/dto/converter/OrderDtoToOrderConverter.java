package ru.sem.orderbook.order.dto.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.sem.orderbook.order.dto.OrderDto;
import ru.sem.orderbook.order.model.Order;

@RequiredArgsConstructor
@Component
public class OrderDtoToOrderConverter implements Converter<OrderDto, Order> {

    @Override
    public Order convert(OrderDto source) {
        Order order = new Order();
        order.setArticle(source.getArticle());
        order.setInfo(source.getInfo());
        order.setBrand(source.getBrand());
        order.setDealer(source.getDealer());
        order.setManager(source.getManager());
        order.setPrice(Double.valueOf(source.getPrice()));
        order.setQuantity(source.getQuantity());
        order.setProductName(source.getProductName());
        order.setDeliveryTime(source.getDeliveryTime());
        return order;
    }
}