package org.Hotel_reservation;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HotelReservationSystemTest {
    @Test
    public void testFindBestRatedHotel() {
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

        assertEquals("Ridgewood & Total Rates $370", rs.findBestRatedHotel(LocalDate.of(2020, 9, 11), LocalDate.of(2020, 9, 12)));
    }
}
