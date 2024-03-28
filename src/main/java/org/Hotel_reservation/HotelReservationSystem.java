package org.Hotel_reservation;

import java.time.*;
import java.util.*;

public class HotelReservationSystem
{
    private Map<String, Hotel> hotels;

    public HotelReservationSystem()
    {
        hotels = new HashMap<>();
    }

    public void addHotel(String name, int rating)
    {
        hotels.put(name, new Hotel(name, rating));
    }

    public void addRate(String hotelName, String dayType, int rate)
    {
        Hotel hotel = hotels.get(hotelName);
        if (hotel != null) {
            hotel.setRate(dayType, rate);
        } else {
            System.out.println("Hotel " + hotelName + " not found.");
        }
    }

    public int getRate(String hotelName, String dayType)
    {
        Hotel hotel = hotels.get(hotelName);
        if (hotel != null) {
            return hotel.getRate(dayType);
        } else {
            System.out.println("Hotel " + hotelName + " not found.");
            return -1;
        }
    }

    public String findCheapestBestRatedHotel(LocalDate startDate, LocalDate endDate)
    {
        int minTotalRate = Integer.MAX_VALUE;
        int maxRating = Integer.MIN_VALUE;
        String cheapestBestRatedHotel = "";

        for (Map.Entry<String, Hotel> entry : hotels.entrySet()) {
            int totalRate = calculateTotalRate(entry.getValue(), startDate, endDate);
            int rating = entry.getValue().getRating();
            if (totalRate < minTotalRate || (totalRate == minTotalRate && rating > maxRating)) {
                minTotalRate = totalRate;
                maxRating = rating;
                cheapestBestRatedHotel = entry.getKey();
            }
        }

        return cheapestBestRatedHotel + ", Rating: " + maxRating + " and Total Rates: $" + minTotalRate;
    }

    private int calculateTotalRate(Hotel hotel, LocalDate startDate, LocalDate endDate)
    {
        int totalRate = 0;
        LocalDate date = startDate;
        while (!date.isAfter(endDate))
        {
            String dayType = getDayType(date);
            totalRate += hotel.getRate(dayType);
            date = date.plusDays(1);
        }
        return totalRate;
    }

    private String getDayType(LocalDate date)
    {
        return date.getDayOfWeek().getValue() >= DayOfWeek.SATURDAY.getValue() ? "weekend" : "weekday";
    }
}