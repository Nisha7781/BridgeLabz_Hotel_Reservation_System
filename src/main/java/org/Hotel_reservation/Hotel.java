package org.Hotel_reservation;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Hotel {
    private String name;
    private int rating;
    private Map<String, Integer> rates;

    public Hotel(String name, int rating) {
        this.name = name;
        this.rating = rating;
        this.rates = new HashMap<>();
    }

    public void setRate(String dayType, int rate) {
        rates.put(dayType, rate);
    }

    public int getRate(String dayType) {
        return rates.get(dayType);
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }
}

