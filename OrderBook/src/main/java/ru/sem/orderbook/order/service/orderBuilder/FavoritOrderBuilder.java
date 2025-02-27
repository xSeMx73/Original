package ru.sem.orderbook.order.service.orderBuilder;

import org.springframework.stereotype.Service;
import ru.sem.orderbook.order.dto.OrderDto;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
public class FavoritOrderBuilder {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public OrderDto builder(OrderDto order) {
        String[] splitArticle = order.getArticle().split(" {3}");
        order.setDealer("Фаворит" + buildDialer(splitArticle));
        order.setDeliveryTime(buildDeliveryDate(splitArticle));
        return order;
    }

    private String buildDialer(String[] article) {
        if (article.length == 5) {
            return " ПАРТНЕРКА";
        }
        return "";
    }

    private LocalDate buildDeliveryDate(String[] stringDate) {
       if (stringDate.length == 5) {
           return LocalDate.parse(stringDate[3], formatter);
       }
       if (LocalDate.now().getDayOfWeek() == DayOfWeek.SATURDAY ||
               LocalTime.now().isAfter(LocalTime.of(16, 30)) ) {
        return LocalDate.now().plusDays(2);
       }
       return LocalDate.now().plusDays(1);
    }

}
