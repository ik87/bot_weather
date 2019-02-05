package com.ik87;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;


public class WeatherHandler {
    public SendMessage getWeatherOnOneDay(Message message) {
        String lat = message.getLocation().getLatitude().toString();
        String lon = message.getLocation().getLongitude().toString();
        String weatherMessages = null;
        HttpHandler httpHandler = new HttpHandler();
        String http = weatherSite(lat, lon);
        try {
            weatherMessages = httpHandler.getHTML(http.toString());
        } catch (Exception e) {
            e.getStackTrace();
        }
        Data data = handlerJson(weatherMessages);
        return makeMessage(data);

    }

    private Data handlerJson(String rawMessage) {
        SendMessage message = new SendMessage();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Data data = gson.fromJson(rawMessage, Data.class);
        return data;
    }

    private String weatherSite(String lat, String lon) {
        StringBuilder http = new StringBuilder();
        http.append("http://api.openweathermap.org/data/2.5/forecast?lat=");
        http.append(lat);
        http.append("&lon=");
        http.append(lon);
        http.append("&lang=ru&units=metric&APPID=3ad54740fd37f3f14a3a32a09f09cd25");
        return http.toString();
    }

    private SendMessage makeMessage(Data data) {
        SendMessage message = new SendMessage();
        if (data != null) {
            WeatherGeneral wg = data.list.get(0);
            if (data != null && data.list != null) {
                StringBuilder text = new StringBuilder();
                text.append("Погода на: ");
                text.append(wg.dt_txt);
                text.append("\n");
                text.append(wg.weather.get(0).description);
                text.append("\nветер:  ");
                text.append(wg.wind.speed);
                text.append(" м/с ");
                text.append(" ");
                text.append(wg.wind.deg);
                text.append('\u00B0');
                text.append("\nтемп:  ");
                text.append(wg.main.temp);
                text.append(" ");
                text.append('\u00B0');
                text.append("C");

                message.setText(text.toString());
            }
        }
        return message;
    }
    /*
    private String degreeToAsimut(String degree) {
        Integer deg = Integer.getInteger(degree);
        String asimut = null;
        if(deg > 330 && deg < 360 || deg > 0 && deg < 330) {

        } else if (deg > 330 && deg < 360 || deg > 0 && deg < 330)
    }
    */
}
//https://samples.openweathermap.org/data/2.5/weather?lat=35&lon=139&lang=ru&units=metric&appid=12
//http://api.openweathermap.org/data/2.5/forecast?lat=35&lon=139&lang=ru&units=metric&APPID=3ad54740fd37f3f14a3a32a09f09cd25