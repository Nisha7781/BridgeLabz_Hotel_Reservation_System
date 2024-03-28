package org.Hotel_reservation;
import java.util.HashMap;
import java.util.Map;

public class Hotel
{
    private String name;
    private Map<String, Integer> regularRates;

    public Hotel(String name)
    {
        this.name = name;
        this.regularRates = new HashMap<>();
    }

    public void setRegularRate(String dayType, int rate)
    {
        regularRates.put(dayType, rate);
    }

    public int getRegularRate(String dayType)
    {
        return regularRates.get(dayType);
    }

    public String getName()
    {
        return name;
    }
}
