package org.Hotel_reservation;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HotelReservationSystemTest {

    @Test
    public void testRegularRates() {
        HotelReservationSystem rs = new HotelReservationSystem();

        rs.addHotel("Lakewood", 3);
        rs.addRegularRate("Lakewood", "weekday", 110);
        rs.addRegularRate("Lakewood", "weekend", 90);
        assertEquals(110, rs.getRegularRate("Lakewood", "weekday"));
        assertEquals(90, rs.getRegularRate("Lakewood", "weekend"));

        rs.addHotel("Bridgewood", 4);
        rs.addRegularRate("Bridgewood", "weekday", 150);
        rs.addRegularRate("Bridgewood", "weekend", 50);
        assertEquals(150, rs.getRegularRate("Bridgewood", "weekday"));
        assertEquals(50, rs.getRegularRate("Bridgewood", "weekend"));

        rs.addHotel("Ridgewood", 5);
        rs.addRegularRate("Ridgewood", "weekday", 220);
        rs.addRegularRate("Ridgewood", "weekend", 150);
        assertEquals(220, rs.getRegularRate("Ridgewood", "weekday"));
        assertEquals(150, rs.getRegularRate("Ridgewood", "weekend"));
    }

    @Test
    public void testRewardRates() {
        HotelReservationSystem rs = new HotelReservationSystem();

        rs.addHotel("Lakewood", 3);
        rs.addRewardRate("Lakewood", "weekday", 80);
        rs.addRewardRate("Lakewood", "weekend", 80);
        assertEquals(80, rs.getRewardRate("Lakewood", "weekday"));
        assertEquals(80, rs.getRewardRate("Lakewood", "weekend"));

        rs.addHotel("Bridgewood", 4);
        rs.addRewardRate("Bridgewood", "weekday", 110);
        rs.addRewardRate("Bridgewood", "weekend", 50);
        assertEquals(110, rs.getRewardRate("Bridgewood", "weekday"));
        assertEquals(50, rs.getRewardRate("Bridgewood", "weekend"));

        rs.addHotel("Ridgewood", 5);
        rs.addRewardRate("Ridgewood", "weekday", 100);
        rs.addRewardRate("Ridgewood", "weekend", 40);
        assertEquals(100, rs.getRewardRate("Ridgewood", "weekday"));
        assertEquals(40, rs.getRewardRate("Ridgewood", "weekend"));
    }

    @Test
    public void testFindCheapestBestRatedHotelForRegularCustomer() {
        HotelReservationSystem rs = new HotelReservationSystem();

        rs.addHotel("Lakewood", 3);
        rs.addRegularRate("Lakewood", "weekday", 110);
        rs.addRegularRate("Lakewood", "weekend", 90);

        rs.addHotel("Bridgewood", 4);
        rs.addRegularRate("Bridgewood", "weekday", 150);
        rs.addRegularRate("Bridgewood", "weekend", 50);

        rs.addHotel("Ridgewood", 5);
        rs.addRegularRate("Ridgewood", "weekday", 220);
        rs.addRegularRate("Ridgewood", "weekend", 150);

        assertEquals("Bridgewood, Rating: 4 and Total Rates: $200", rs.findCheapestBestRatedHotelForRegularCustomer(LocalDate.of(2020, 9, 11), LocalDate.of(2020, 9, 12)));
    }

    @Test
    public void testFindCheapestBestRatedHotelForRewardCustomer() {
        HotelReservationSystem rs = new HotelReservationSystem();

        rs.addHotel("Lakewood", 3);
        rs.addRewardRate("Lakewood", "weekday", 80);
        rs.addRewardRate("Lakewood", "weekend", 80);

        rs.addHotel("Bridgewood", 4);
        rs.addRewardRate("Bridgewood", "weekday", 110);
        rs.addRewardRate("Bridgewood", "weekend", 50);

        rs.addHotel("Ridgewood", 5);
        rs.addRewardRate("Ridgewood", "weekday", 100);
        rs.addRewardRate("Ridgewood", "weekend", 40);

        assertEquals("Ridgewood, Rating: 5 and Total Rates: $140", rs.findCheapestBestRatedHotelForRewardCustomer(LocalDate.of(2020, 9, 11), LocalDate.of(2020, 9, 12)));
    }

    @Test
    public void testInvalidCustomerType() {
        HotelReservationSystem rs = new HotelReservationSystem();
        rs.addHotel("Lakewood", 3);
        rs.addRegularRate("Lakewood", "weekday", 110);
        rs.addRegularRate("Lakewood", "weekend", 90);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            rs.validateAndFindCheapestBestRatedHotel(LocalDate.of(2020, 9, 11), LocalDate.of(2020, 9, 12), "invalid");
        });

        String expectedMessage = "Invalid customer type: Please specify 'regular' or 'reward'.";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testInvalidDateRange() {
        HotelReservationSystem rs = new HotelReservationSystem();
        rs.addHotel("Lakewood", 3);
        rs.addRegularRate("Lakewood", "weekday", 110);
        rs.addRegularRate("Lakewood", "weekend", 90);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            rs.validateAndFindCheapestBestRatedHotel(LocalDate.of(2020, 9, 12), LocalDate.of(2020, 9, 11), "regular");
        });

        String expectedMessage = "Invalid date range: End date must be after start date.";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
}