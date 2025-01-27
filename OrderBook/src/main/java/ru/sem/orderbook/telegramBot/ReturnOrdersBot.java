package ru.sem.orderbook.telegramBot;


import org.springframework.stereotype.Component;


import org.telegram.telegrambots.client.jetty.JettyTelegramClient;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot;

@Component
public class ReturnOrdersBot implements SpringLongPollingBot, LongPollingSingleThreadUpdateConsumer {

    long chatId;

    private final TelegramClient telegramClient;

    public ReturnOrdersBot() {
        telegramClient = new JettyTelegramClient(getBotToken());
    }


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
        // Мы проверяем, есть ли в обновлении сообщение, а в сообщении - текст
        if (update.hasMessage() && update.getMessage().hasText()) {
            // Установите переменные
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();
            chatId = chat_id;
            SendMessage message = SendMessage // Create a message object
                    .builder()
                    .chatId(chat_id)
                    .text(message_text)
                    .build();
            try {
                telegramClient.execute(message); // Sending our message object to user
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String message) {
        SendMessage message1 = SendMessage // Create a message object
                .builder()
                .chatId(chatId)
                .text(message)
                .build();
        try {
            telegramClient.execute(message1); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}