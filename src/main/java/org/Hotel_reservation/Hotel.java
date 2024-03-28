package org.Hotel_reservation;
import java.util.HashMap;
import java.util.Map;

public class Hotel
{
    private String name;
    private Map<String, Integer> rates;

    public Hotel(String name)
    {
        this.name = name;
        this.rates = new HashMap<>();
    }

    public void setRate(String dayType, int rate)
    {
        rates.put(dayType, rate);
    }

    public int getRate(String dayType)
    {
        return rates.get(dayType);
    }

    public String getName()
    {
        return name;
    }
}




