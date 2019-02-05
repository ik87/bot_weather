package com.ik87;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {
    private final String BOT_NAME = "WeatherIK87Bot"; //"BotWeatherIK87";
    private final String BOT_TOKEN = "621258285:AAFB-Kr8GLh8C8yUlseWUP3MmaAzaOFgAB4";
    @Override
    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            //To do
        } else if (update.hasMessage() && update.getMessage().hasLocation()) {
            SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
            //         .setChatId(update.getMessage().getChatId())
            //          .setText(update.getMessage().getText());

            WeatherHandler wh = new WeatherHandler();
            message = wh.getWeatherOnOneDay(update.getMessage());
            message.setChatId(update.getMessage().getChatId());
            try {
                execute(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
    public Bot(DefaultBotOptions options) {
        super(options);
    }
    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }
}
