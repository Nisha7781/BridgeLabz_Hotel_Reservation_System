package org.Hotel_reservation;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Hotel {
    private String name;
    private int rating;
    private Map<String, Integer> regularRates;
    private Map<String, Integer> rewardRates;

    public Hotel(String name, int rating) {
        this.name = name;
        this.rating = rating;
        this.regularRates = new HashMap<>();
        this.rewardRates = new HashMap<>();
    }

    public void setRegularRate(String dayType, int rate) {
        regularRates.put(dayType, rate);
    }

    public int getRegularRate(String dayType) {
        return regularRates.get(dayType);
    }

    public void setRewardRate(String dayType, int rate) {
        rewardRates.put(dayType, rate);
    }

    public int getRewardRate(String dayType) {
        return rewardRates.get(dayType);
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }
}