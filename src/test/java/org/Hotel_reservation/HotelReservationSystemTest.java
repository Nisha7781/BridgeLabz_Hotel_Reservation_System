package org.Hotel_reservation;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class HotelReservationSystemTest
{

    @Test
    public void testRegularRates()
    {
        HotelReservationSystem rs = new HotelReservationSystem();

        rs.addHotel("Lakewood", 3);
        rs.addRate("Lakewood", "weekday", 110);
        rs.addRate("Lakewood", "weekend", 90);
        assertEquals(110, rs.getRate("Lakewood", "weekday"));
        assertEquals(90, rs.getRate("Lakewood", "weekend"));

        rs.addHotel("Bridgewood", 4);
        rs.addRate("Bridgewood", "weekday", 150);
        rs.addRate("Bridgewood", "weekend", 50);
        assertEquals(150, rs.getRate("Bridgewood", "weekday"));
        assertEquals(50, rs.getRate("Bridgewood", "weekend"));

        rs.addHotel("Ridgewood", 5);
        rs.addRate("Ridgewood", "weekday", 220);
        rs.addRate("Ridgewood", "weekend", 150);
        assertEquals(220, rs.getRate("Ridgewood", "weekday"));
        assertEquals(150, rs.getRate("Ridgewood", "weekend"));
    }

    @Test
    public void testFindCheapestHotel()
    {
        HotelReservationSystem rs = new HotelReservationSystem();

        rs.addHotel("Lakewood", 3);
        rs.addRate("Lakewood", "weekday", 110);
        rs.addRate("Lakewood", "weekend", 90);

        rs.addHotel("Bridgewood", 4);
        rs.addRate("Bridgewood", "weekday", 150);
        rs.addRate("Bridgewood", "weekend", 50);

        rs.addHotel("Ridgewood", 5);
        rs.addRate("Ridgewood", "weekday", 220);
        rs.addRate("Ridgewood", "weekend", 150);

        assertEquals("Bridgewood and Lakewood with Total Rates: $200", rs.findCheapestHotel(LocalDate.of(2020, 9, 11), LocalDate.of(2020, 9, 12)));
    }
}