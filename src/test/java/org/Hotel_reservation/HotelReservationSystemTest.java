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
        assertEquals(110, rs.getRegularRate("Lakewood", "weekday"));


        rs.addHotel("Bridgewood");
        rs.addRegularRate("Bridgewood", "weekday", 160);
        assertEquals(160, rs.getRegularRate("Bridgewood", "weekday"));


        rs.addHotel("Ridgewood");
        rs.addRegularRate("Ridgewood", "weekday", 220);
        assertEquals(220, rs.getRegularRate("Ridgewood", "weekday"));
    }
}
