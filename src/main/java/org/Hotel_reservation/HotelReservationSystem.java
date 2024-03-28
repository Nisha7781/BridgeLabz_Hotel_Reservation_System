package org.Hotel_reservation;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class HotelReservationSystem
{
    private Map<String, Hotel> hotels;

    public HotelReservationSystem()
    {
        hotels = new HashMap<>();
    }

    public void addHotel(String name)
    {
        hotels.put(name, new Hotel(name));
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

    public String findCheapestHotel(LocalDate startDate, LocalDate endDate)
    {
        int minTotalRate = Integer.MAX_VALUE;
        String cheapestHotel = "";

        for (Map.Entry<String, Hotel> entry : hotels.entrySet())
        {
            int totalRate = calculateTotalRate(entry.getValue(), startDate, endDate);
            if (totalRate < minTotalRate) {
                minTotalRate = totalRate;
                cheapestHotel = entry.getKey();
            } else if (totalRate == minTotalRate && entry.getKey().compareTo(cheapestHotel) < 0) {
                cheapestHotel = entry.getKey();
            }
        }

        return cheapestHotel + ", Total Rates: $" + minTotalRate;
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