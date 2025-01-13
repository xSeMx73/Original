package ru.sem.orderbook.order.service.orderBuilder;

import org.springframework.stereotype.Service;
import ru.sem.orderbook.order.dto.OrderDto;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
        LocalDateTime now = LocalDateTime.now();
        String[] splitDate = splitArticle[3].split(" ");
        if (splitDate.length == 4) {
            if (splitArticle[4].contains("Самара")) {
                if (now.getDayOfWeek() != DayOfWeek.SATURDAY
                        && LocalTime.now().isBefore(LocalTime.of(18, 0))
                        && (now.getDayOfWeek() != DayOfWeek.SUNDAY
                        && LocalTime.now().isBefore(LocalTime.of(18, 0)))) {
                    return LocalDate.now().plusDays(1);
                }
            } else if (splitArticle[4].contains("Москва")) {
                if (now.getDayOfWeek() != DayOfWeek.SATURDAY
                        && LocalTime.now().isBefore(LocalTime.of(17, 0))
                        && (now.getDayOfWeek() != DayOfWeek.SUNDAY
                        && LocalTime.now().isBefore(LocalTime.of(13, 0)))) {
                    return LocalDate.now().plusDays(1);
                }
            } else if (splitArticle[4].contains("Нижний Новгород")) {
                return LocalDate.now().plusMonths(1);
            }
            else {return LocalDate.now().plusDays(2);}
        }
            return LocalDate.parse(splitDate[0] + "." + LocalDate.now().getYear(), formatter);
    }

    private String buildDealer(String[] splitArticle) {
        if (splitArticle.length == 4) {
            return splitArticle[splitArticle.length - 1];
        }
        return "ПАРТНЕРКА" + splitArticle[splitArticle.length - 1];
    }


}
