package ru.sem.orderbook.order.service.orderBuilder;

import org.springframework.stereotype.Service;
import ru.sem.orderbook.order.dto.OrderDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class ArmtekOrderBuilder {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public OrderDto builder(OrderDto order) {
        String[] splitArticle = order.getArticle().split(" {3}");
        order.setDealer("Армтек " + buildDialer(splitArticle));
        order.setDeliveryTime(buildDeliveryDate(splitArticle));
        return order;
    }

    private String buildDialer(String[] splitArticle) {
        if(splitArticle[2].contains("Заказ")) {
            return "ПАРТНЕРКА " + splitArticle[splitArticle.length - 1] ;
        }
        return splitArticle[splitArticle.length - 1];
    }

    private LocalDate buildDeliveryDate(String[] splitArticle) {
        String[] splitDate = splitArticle[3].split(" ");
        return LocalDate.parse(splitDate[0] + "." + LocalDate.now().getYear(), formatter);
    }
}
