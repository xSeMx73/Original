package ru.sem.orderbook.orderMonitor.model.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.sem.orderbook.order.model.Order;
import ru.sem.orderbook.orderMonitor.model.PendOrder;

@RequiredArgsConstructor
@Component
public class OrderToPendOrderConverter implements Converter<Order, PendOrder> {

    @Override
    public PendOrder convert(Order source) {
        PendOrder pendOrder = new PendOrder();
        pendOrder.setManager(source.getManager());
        pendOrder.setInfo(source.getInfo());
        pendOrder.setPrice(source.getPrice());
        pendOrder.setArticle(source.getArticle());
        pendOrder.setInputData(source.getDeliveryTime());
        pendOrder.setProductName(source.getProductName());
        pendOrder.setBrand(source.getBrand());
        pendOrder.setQuantity(source.getQuantity());
        pendOrder.setReturnData(source.getDeliveryTime().plusDays(14));
        return pendOrder;
    }
}
