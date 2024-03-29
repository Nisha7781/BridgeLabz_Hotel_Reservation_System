package org.Hotel_reservation;

import java.time.*;
import java.util.*;

public class HotelReservationSystem {
    private Map<String, Hotel> hotels;

    public HotelReservationSystem()
    {
        hotels = new HashMap<>();
    }

    public void addHotel(String name, int rating)
    {
        hotels.put(name, new Hotel(name, rating));
    }

    public void addRegularRate(String hotelName, String dayType, int rate)
    {
        Hotel hotel = hotels.get(hotelName);
        if (hotel != null)
        {
            hotel.setRegularRate(dayType, rate);
        } else {
            System.out.println("Hotel " + hotelName + " not found.");
        }
    }

    public void addRewardRate(String hotelName, String dayType, int rate) {
        Hotel hotel = hotels.get(hotelName);
        if (hotel != null) {
            hotel.setRewardRate(dayType, rate);
        } else {
            System.out.println("Hotel " + hotelName + " not found.");
        }
    }

    public int getRegularRate(String hotelName, String dayType) {
        Hotel hotel = hotels.get(hotelName);
        if (hotel != null) {
            return hotel.getRegularRate(dayType);
        } else {
            System.out.println("Hotel " + hotelName + " not found.");
            return -1;
        }
    }

    public int getRewardRate(String hotelName, String dayType) {
        Hotel hotel = hotels.get(hotelName);
        if (hotel != null) {
            return hotel.getRewardRate(dayType);
        } else {
            System.out.println("Hotel " + hotelName + " not found.");
            return -1;
        }
    }

    public String findCheapestBestRatedHotelForRegularCustomer(LocalDate startDate, LocalDate endDate)
    {
        Optional<String> cheapestBestRatedHotel = hotels.entrySet().stream()
                .map(entry -> {
                    int totalRate = calculateTotalRegularRate(entry.getValue(), startDate, endDate);
                    return Map.entry(entry.getKey(), totalRate);
                })
                .min(Comparator.comparingInt(Map.Entry::getValue))
                .map(Map.Entry::getKey);

        if (cheapestBestRatedHotel.isPresent())
        {
            String hotelName = cheapestBestRatedHotel.get();
            int rating = hotels.get(hotelName).getRating();
            int totalRate = calculateTotalRegularRate(hotels.get(hotelName), startDate, endDate);
            return hotelName + ", Rating: " + rating + " and Total Rates: $" + totalRate;
        } else {
            return "No hotels found.";
        }
    }

    public String findCheapestBestRatedHotelForRewardCustomer(LocalDate startDate, LocalDate endDate) {
        Optional<String> cheapestBestRatedHotel = hotels.entrySet().stream()
                .map(entry -> {
                    int totalRate = calculateTotalRewardRate(entry.getValue(), startDate, endDate);
                    return Map.entry(entry.getKey(), totalRate);
                })
                .min(Comparator.comparingInt(Map.Entry::getValue))
                .map(Map.Entry::getKey);

        if (cheapestBestRatedHotel.isPresent()) {
            String hotelName = cheapestBestRatedHotel.get();
            int rating = hotels.get(hotelName).getRating();
            int totalRate = calculateTotalRewardRate(hotels.get(hotelName), startDate, endDate);
            return hotelName + ", Rating: " + rating + " and Total Rates: $" + totalRate;
        } else {
            return "No hotels found.";
        }
    }

    private int calculateTotalRegularRate(Hotel hotel, LocalDate startDate, LocalDate endDate) {
        int totalRate = 0;
        LocalDate date = startDate;
        while (!date.isAfter(endDate)) {
            String dayType = getDayType(date);
            totalRate += hotel.getRegularRate(dayType);
            date = date.plusDays(1);
        }
        return totalRate;
    }

    private int calculateTotalRewardRate(Hotel hotel, LocalDate startDate, LocalDate endDate) {
        int totalRate = 0;
        LocalDate date = startDate;
        while (!date.isAfter(endDate)) {
            String dayType = getDayType(date);
            totalRate += hotel.getRewardRate(dayType);
            date = date.plusDays(1);
        }
        return totalRate;
    }

    public String validateAndFindCheapestBestRatedHotel(LocalDate startDate, LocalDate endDate, String customerType) throws IllegalArgumentException {
        if (startDate == null || endDate == null || customerType == null) {
            throw new IllegalArgumentException("Invalid input: Dates and customer type cannot be null.");
        }

        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Invalid date range: End date must be after start date.");
        }

        if (!customerType.equalsIgnoreCase("regular") && !customerType.equalsIgnoreCase("reward")) {
            throw new IllegalArgumentException("Invalid customer type: Please specify 'regular' or 'reward'.");
        }

        if (customerType.equalsIgnoreCase("regular")) {
            return findCheapestBestRatedHotelForRegularCustomer(startDate, endDate);
        } else {
            return findCheapestBestRatedHotelForRewardCustomer(startDate, endDate);
        }
    }

    private String getDayType(LocalDate date)
    {
        return date.getDayOfWeek().getValue() >= DayOfWeek.SATURDAY.getValue() ? "weekend" : "weekday";
    }
}