package ru.sem.orderbook.order.service.orderBuilder;

import org.springframework.stereotype.Service;
import ru.sem.orderbook.order.dto.OrderDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class TrackMotorsOrderBuilder {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public OrderDto builder(OrderDto order) {
        String[] splitArticle = order.getArticle().split(" {3}");
        order.setDealer("Тракмоторс " + buildDealer(splitArticle));
        order.setDeliveryTime(buildDeliveryDate(splitArticle));
        return order;
    }

    private LocalDate buildDeliveryDate(String[] splitArticle) {
        String[] splitDate = splitArticle[3].split(" ");
       // if (splitDate.length == 4) {

       // }

            return LocalDate.parse(splitDate[0] + "." + LocalDate.now().getYear(), formatter);
    }

    private String buildDealer(String[] splitArticle) {
        if (splitArticle.length == 4) {
            return splitArticle[splitArticle.length - 1];
        }
        return "ПАРТНЕРКА" + splitArticle[splitArticle.length - 1];
    }


}
