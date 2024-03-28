package org.Hotel_reservation;

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

    public void addRegularRate(String hotelName, String dayType, int rate)
    {
        Hotel hotel = hotels.get(hotelName);
        if (hotel != null) {
            hotel.setRegularRate(dayType, rate);
        } else {
            System.out.println("Hotel " + hotelName + " not found.");
        }
    }

    public int getRegularRate(String hotelName, String dayType)
    {
        Hotel hotel = hotels.get(hotelName);
        if (hotel != null) {
            return hotel.getRegularRate(dayType);
        } else {
            System.out.println("Hotel " + hotelName + " not found.");
            return -1;
        }
    }
}
