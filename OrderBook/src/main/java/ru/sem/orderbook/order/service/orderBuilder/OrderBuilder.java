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

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

   private final ComtransOrderBuilder comtrans;


    public OrderDto disBuilder(OrderDto orderDto) {
        if(orderDto.getDealer().contains("наш склад")) {
            orderDto.setDealer("наш склад " + setCity(orderDto.getArticle()));
        }
        if(orderDto.getDealer().contains("Комтранс")) {
            orderDto =  comtrans.builder(orderDto);
        }
        if(orderDto.getDealer().contains("Омега")) {
            orderDto.setDealer("Омега " + setArticleAndBrand(orderDto.getArticle(), 9));
        }
        orderDto.setPrice(priceFormat(orderDto.getPrice()));
        orderDto.setBrand(setArticleAndBrand(orderDto.getArticle(), 1));
        orderDto.setArticle(setArticleAndBrand(orderDto.getArticle(), 0));
        orderDto.setCreateTime(LocalDateTime.now());

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
         return result;
    }

    }

