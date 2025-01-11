package ru.sem.orderbook.order.service.orderBuilder;

import org.springframework.stereotype.Service;
import ru.sem.orderbook.order.dto.OrderDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Service
public class ComtransOrderBuilder {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public OrderDto builder(OrderDto order) {
        order.setDealer("Комтранс " + addDealer(order.getArticle()));
        order.setDeliveryTime(addDeliveryTime(order.getArticle()));
        return order;
    }

    private String addDealer(String article) {
        String[] splitArticle = article.split(" {3}");
        return splitArticle[splitArticle.length - 1];
    }

    private LocalDate addDeliveryTime(String stringDate) {
        String[] splitArticle = stringDate.split(" {3}");
         if (splitArticle[3].contains("завтра")) {
            return LocalDate.now().plusDays(1);
        } else {
             splitArticle = splitArticle[3].split(",");
             return LocalDate.parse(splitArticle[0], formatter);
         }
    }
}
