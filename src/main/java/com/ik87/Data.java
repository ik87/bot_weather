package com.ik87;

import java.util.List;

public class Data {
    public List<WeatherGeneral> list;
}

class WeatherGeneral {
    public String dt_txt;
    public General main;
    public List<Weather> weather;
    public Wind wind;

}

class General {
    public String temp;
    public String temp_min;
    public String temp_max;
    public String pressure;
    public String humidity;
}

class Weather {
   public String main;
   public String description;
}

class Wind {
    public String speed	;
    public String deg;
}