package ru.sem.orderbook.order.service.orderBuilder;

import org.springframework.stereotype.Service;
import ru.sem.orderbook.order.dto.OrderDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class AutostelsOrderBuilder {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public OrderDto builder(OrderDto order) {

        String[] splitArticle = order.getArticle().split(" {3}");
        order.setDealer("Автостелс");
        order.setDeliveryTime(buildDeliveryDate(splitArticle));
        return order;
    }


    private LocalDate buildDeliveryDate(String[] stringDate) {
        String[] splitDelivery = stringDate[3].split(" ");
        if (splitDelivery.length < 3) {
            return LocalDate.now().plusDays(Long.parseLong(splitDelivery[0]));
        }
        return LocalDate.now()
                .plusDays((int) Math.ceil((double) Long.parseLong(
                        splitDelivery[0] + splitDelivery[2]) / 2));
    }
}
