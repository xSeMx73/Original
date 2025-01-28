package ru.sem.orderbook.orderMonitor.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.sem.orderbook.orderMonitor.model.PendOrder;
import ru.sem.orderbook.orderMonitor.repository.ChatIDRepository;
import ru.sem.orderbook.orderMonitor.repository.PendOrderRepository;
import ru.sem.orderbook.telegramBot.ReturnOrdersBot;

import java.time.LocalDate;
import java.util.*;

@RequiredArgsConstructor
@Service
public class SenderForReturn {

    private final PendOrderRepository pendOrderRepository;
    private final ChatIDRepository chatIDRepository;
    private final ReturnOrdersBot bot;


 //   @PostConstruct
    @Scheduled(cron = "0 0 12 * * ?")
    public void scheduleSender() {
        List<PendOrder> pendOrders = pendOrderRepository.findAllWhereReasonIsNull();
        pendOrders = pendOrders.stream().filter(p -> LocalDate.now()
                .isAfter(p.getReturnData().minusDays(3))).toList();
        List<String> names = chatIDRepository.findAllNames();
        for (String name : names) {
            Long chatId = chatIDRepository.findByUserName(name);
 //           Long chatId = 631399799L;
            bot.sendMessageName(name, chatId);
            List<PendOrder> temp = pendOrders.stream()
                    .filter(p -> p.getManager().equals(name))
                    .toList();
            for (PendOrder orderToSend : temp) {
                bot.sendMessage(orderToSend.getInfo(),
                        orderToSend.getDealer(),
                        orderToSend.getProductName(),
                        orderToSend.getArticle(), chatId);
            }
        }
    }
}
