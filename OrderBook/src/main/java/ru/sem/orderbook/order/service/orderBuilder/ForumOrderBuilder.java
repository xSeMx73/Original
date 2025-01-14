package ru.sem.orderbook.order.service.orderBuilder;

import org.springframework.stereotype.Service;
import ru.sem.orderbook.order.dto.OrderDto;

import java.time.LocalDate;


@Service
public class ForumOrderBuilder {

    public OrderDto builder(OrderDto order) {
        String[] splitArticle = order.getArticle().split(" {3}");
        order.setDealer("Форум " + buildDialer(splitArticle));
        order.setDeliveryTime(buildDeliveryDate(splitArticle));
        return order;
    }

    private String buildDialer(String[] article) {
        if (article[2].contains("Заказ")) {
            return "ПАРТНЕРКА";
        }
        return article[article.length - 1];
    }

    private LocalDate buildDeliveryDate(String[] stringDate) {
        String[] splitDate = stringDate[3].split(" ");
        if (splitDate[0].length() == 1) {
            return LocalDate.now().plusDays(Integer.parseInt(splitDate[0]));
        }
        else return LocalDate.now().plusDays(1);
    }
}

