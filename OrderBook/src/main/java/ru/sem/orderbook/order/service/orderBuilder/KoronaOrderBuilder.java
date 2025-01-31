package ru.sem.orderbook.order.service.orderBuilder;

import org.springframework.stereotype.Service;
import ru.sem.orderbook.order.dto.OrderDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class KoronaOrderBuilder {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public OrderDto builder(OrderDto order) {
        String[] splitArticle = order.getArticle().split(" {3}");
        order.setDealer("Корона " + buildDealer(splitArticle));
        order.setDeliveryTime(buildDeliveryDate(splitArticle));
        return order;
    }

    private String buildDealer(String[] splitArticle) {
        if (splitArticle.length == 5) {
            return splitArticle[4];
        }
        return "ПАРТНЕРКА";
    }

    private LocalDate buildDeliveryDate(String[] stringDate) {
        String[] splitDelivery = stringDate[3].split(" ");
        return LocalDate.parse(splitDelivery[0] + "." + LocalDate.now().getYear(), formatter);
    }
}
