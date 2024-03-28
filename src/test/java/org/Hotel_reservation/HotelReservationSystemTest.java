package org.Hotel_reservation;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HotelReservationSystemTest
{

    @Test
    public void testRegularRates()
    {
        HotelReservationSystem rs = new HotelReservationSystem();

        rs.addHotel("Lakewood");
        rs.addRegularRate("Lakewood", "weekday", 110);
        rs.addRegularRate("Lakewood", "weekend", 90);
        assertEquals(110, rs.getRegularRate("Lakewood", "weekday"));
        assertEquals(90, rs.getRegularRate("Lakewood", "weekend"));

        rs.addHotel("Bridgewood");
        rs.addRegularRate("Bridgewood", "weekday", 160);
        rs.addRegularRate("Bridgewood", "weekend", 60);
        assertEquals(160, rs.getRegularRate("Bridgewood", "weekday"));
        assertEquals(60, rs.getRegularRate("Bridgewood", "weekend"));

        rs.addHotel("Ridgewood");
        rs.addRegularRate("Ridgewood", "weekday", 220);
        rs.addRegularRate("Ridgewood", "weekend", 150);
        assertEquals(220, rs.getRegularRate("Ridgewood", "weekday"));
        assertEquals(150, rs.getRegularRate("Ridgewood", "weekend"));
    }
}
