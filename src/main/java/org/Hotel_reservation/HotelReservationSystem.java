package org.Hotel_reservation;

import java.time.*;
import java.util.*;

public class HotelReservationSystem {
    private Map<String, Hotel> hotels;

    public HotelReservationSystem() {
        hotels = new HashMap<>();
    }

    public void addHotel(String name, int rating) {
        hotels.put(name, new Hotel(name, rating));
    }

    public void addRate(String hotelName, String dayType, int rate) {
        Hotel hotel = hotels.get(hotelName);
        if (hotel != null) {
            hotel.setRate(dayType, rate);
        } else {
            System.out.println("Hotel " + hotelName + " not found.");
        }
    }

    public int getTotalRate(Hotel hotel, LocalDate startDate, LocalDate endDate) {
        int totalRate = 0;
        LocalDate date = startDate;
        while (!date.isAfter(endDate)) {
            String dayType = getDayType(date);
            totalRate += hotel.getRate(dayType);
            date = date.plusDays(1);
        }
        return totalRate;
    }

    public String findBestRatedHotel(LocalDate startDate, LocalDate endDate) {
        int maxRating = Integer.MIN_VALUE;
        String bestRatedHotel = "";
        int totalRates = Integer.MAX_VALUE;

        for (Map.Entry<String, Hotel> entry : hotels.entrySet())
        {
            Hotel hotel = entry.getValue();
            int rating = hotel.getRating();
            int totalRate = getTotalRate(hotel, startDate, endDate);

            if (rating > maxRating || (rating == maxRating && totalRate < totalRates))
            {
                maxRating = rating;
                totalRates = totalRate;
                bestRatedHotel = hotel.getName();
            }
        }

        return bestRatedHotel + " & Total Rates $" + totalRates;
    }

    private String getDayType(LocalDate date)
    {
        return date.getDayOfWeek().getValue() >= DayOfWeek.SATURDAY.getValue() ? "weekend" : "weekday";
    }
}
