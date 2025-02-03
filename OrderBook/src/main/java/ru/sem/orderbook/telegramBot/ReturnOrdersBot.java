package ru.sem.orderbook.telegramBot;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import org.telegram.telegrambots.client.jetty.JettyTelegramClient;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot;
import ru.sem.orderbook.orderMonitor.model.UsersChatId;
import ru.sem.orderbook.orderMonitor.repository.ChatIDRepository;



@RequiredArgsConstructor
@Component
public class ReturnOrdersBot implements SpringLongPollingBot, LongPollingSingleThreadUpdateConsumer {


    private final ChatIDRepository chatIDRepository;
    private final TelegramClient telegramClient = new JettyTelegramClient(getBotToken());


    @Override
    public String getBotToken() {
        return "8101350886:AAF8qK74uvxQ-_03kGHh6VNvUDrVoRD_QgI";
    }

    @Override
    public LongPollingUpdateConsumer getUpdatesConsumer() {
        return this;
    }


    @Override
    public void consume(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            long chat_id = update.getMessage().getChatId();
            UsersChatId usersChatId = new UsersChatId();
            usersChatId.setChatId(chat_id);
            usersChatId.setUserLogin(update.getMessage().getFrom().getUserName());
            usersChatId.setUserName(update.getMessage().getFrom().getFirstName());
            chatIDRepository.save(usersChatId);
            SendMessage message = SendMessage
                    .builder()
                    .chatId(chat_id)
                    .text("Привет " + update.getMessage().getFrom().getFirstName() +
                            "! Ты подключился к сервису мониторинга возвратов.")
                    .build();
            try {
                telegramClient.execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessageName(String manager,
                            Long chatId) {
        SendMessage message = SendMessage
                .builder()
                .chatId(chatId)
                .text(manager + " Вам нужно в течении двух дней вернуть следующие " +
                        "товары или соответствующе отметить их в сервисе")
                .build();
        try {
            telegramClient.execute(message); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String info,
                            String dealer,
                            String productName,
                            String article,
                            Long chatId) {
        SendMessage message = SendMessage // Create a message object
                .builder()
                .chatId(chatId)
                .text("Клиент: " + info + "\n" +
                        "Поставщик: " + dealer + "\n" +
                        "Название товара: " + productName + "\n" +
                        "Артикул: " + article)
                .build();
        try {
            telegramClient.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}