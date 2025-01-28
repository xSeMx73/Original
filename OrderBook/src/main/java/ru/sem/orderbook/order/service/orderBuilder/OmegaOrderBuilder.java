package ru.sem.orderbook.order.service.orderBuilder;

import org.springframework.stereotype.Service;
import ru.sem.orderbook.order.dto.OrderDto;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Service
public class OmegaOrderBuilder {

    public OrderDto builder(OrderDto order) {
        String[] splitArticle = order.getArticle().split(" {3}");
        order.setDealer("Омега " + buildDealer(splitArticle));
        order.setDeliveryTime(buildDeliveryTime(splitArticle));
        return order;
    }

    private String buildDealer(String[] splitArticle) {
        if (splitArticle.length == 4) {
            return splitArticle[3];
        }
        return "ПАРТНЕРКА";
    }

    private LocalDate buildDeliveryTime(String[] splitArticle) throws NumberFormatException, IllegalArgumentException {
        LocalDateTime now = LocalDateTime.now();
        if(splitArticle.length == 4) {
            if(splitArticle[3].contains("Казань")) {
                if(now.isBefore(LocalDateTime.of(
                                LocalDate.now(), LocalTime.of(19,30)))
                && now.getDayOfWeek() != DayOfWeek.SATURDAY) {
                    return LocalDate.now().plusDays(1);
                } else {
                return LocalDate.now().plusDays(2);
            }
        } else  {
                return LocalDate.now().plusDays(2);
            }
        }
        String[] plusDays = splitArticle[3].split(" ");
        return LocalDate.now().plusDays(Long.parseLong(plusDays[0]));
    }
}
