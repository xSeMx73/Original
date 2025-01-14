package ru.sem.orderbook.order.service.orderBuilder;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sem.orderbook.order.dto.OrderDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RequiredArgsConstructor
@Service
public class OrderBuilder {

   private final ComtransOrderBuilder comtrans;
   private final OmegaOrderBuilder omega;
   private final ArmtekOrderBuilder armtek;
   private final TrackMotorsOrderBuilder trackMotors;
   private final ForumOrderBuilder forum;
   private final FavoritOrderBuilder favorit;
   private final AutostelsOrderBuilder autostels;


    public OrderDto disBuilder(OrderDto orderDto) {
        if (orderDto.getDealer().contains("наш склад")) {
            orderDto.setDealer("наш склад " + setCity(orderDto.getArticle()));
            orderDto.setDeliveryTime(LocalDate.now());
            return simpleBuilder(orderDto);
        } if (orderDto.getDealer().contains("Комтранс")) {
            orderDto =  comtrans.builder(orderDto);
            return simpleBuilder(orderDto);
        } if (orderDto.getDealer().contains("Омега")) {
            orderDto = omega.builder(orderDto);
            return simpleBuilder(orderDto);
        } if (orderDto.getDealer().contains("Армтек")) {
            orderDto = armtek.builder(orderDto);
            return simpleBuilder(orderDto);
        } if (orderDto.getDealer().contains("ТракМоторс")) {
            orderDto = trackMotors.builder(orderDto);
            return simpleBuilder(orderDto);
        } if (orderDto.getDealer().contains("Форум")) {
            orderDto = forum.builder(orderDto);
            return simpleBuilder(orderDto);
        } if (orderDto.getDealer().contains("Фаворит")) {
            orderDto = favorit.builder(orderDto);
            return simpleBuilder(orderDto);
        } if (orderDto.getDealer().contains("Автостелс")) {
            orderDto = autostels.builder(orderDto);
            return simpleBuilder(orderDto);
        }

        return orderDto;
    }

    String setCity (String fullString) {
        Pattern pattern = Pattern.compile("(г\\.\\S+)");
        Matcher matcher = pattern.matcher(fullString);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return "";
        }
    }

    String setArticleAndBrand(String article,int index) {
        String[] words = article.split(" {3}");
        return words[index];
    }

    String priceFormat(String sourcePrice) {
      String result =  sourcePrice.replace(',', '.');
        result = result.replaceAll("\\u00A0", "");
        result = String.valueOf(PriceBuilder.buildPrice(result));
         return result;
    }

    OrderDto simpleBuilder(OrderDto orderDto) {
        orderDto.setPrice(priceFormat(orderDto.getPrice()));
        orderDto.setBrand(setArticleAndBrand(orderDto.getArticle(), 1));
        orderDto.setArticle(setArticleAndBrand(orderDto.getArticle(), 0));
        return orderDto;
    }

    }

