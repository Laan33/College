
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.*;

public class BookingTest {
    Booking booking;
    TestCentre testCentre;

    @BeforeEach
    void setUp() throws Exception {
        testCentre = new TestCentre("Ballinasloe", "Unit 9, Pollboy Industrial Estate,\n" + "Ballinasloe, Galway H53 NW94");
        booking = new Booking("09-G-13921", testCentre, LocalDate.of(2024, 1, 12), LocalTime.of(12, 30));
    }

    @AfterEach
    void tearDown() throws Exception {
        booking = null;
        testCentre = null;
    }

    /*NCTBookingSlotWebservice webService = new NCTBookingSlotWebservice() {
        @Override
        public LocalDateTime getBookingDateTime(TestCentre testCentre) {
            return LocalDateTime.of(2026, 2, 12, 12, 30);
        }
    };*/

    @Test
    void testValidDateWithValidBooking() {
        assertEquals(booking.getBookingDate(), LocalDate.of(2024, 1, 12));
    }

    @Test
    void testInValidPastDateWithValidBooking() {
        booking = null;
        assertThrows(Exception.class, () -> {
            booking = new Booking("09-G-13921", testCentre, LocalDate.of(1999, 1, 12), LocalTime.of(12, 30));
        });
    }
    @Test
    void testInValidDateWithValidBooking() {
        try {
            booking = null;
            booking = new Booking("09-G-13921", testCentre, LocalDate.of(19299, 101, -1), LocalTime.of(12, 30));
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThrows(Exception.class, () -> {
            booking = new Booking("09-G-13921", testCentre, LocalDate.of(19299, 101, -1), LocalTime.of(12, 30));
        });
    }

    @Test
    void testTimeWithValidBooking() {
        assertEquals(booking.getBookingTime(), LocalTime.of(12, 30));
    }

    @Test
    void testInValidTimeWithValidBooking() {
        try {
            booking = null;
            assertThrows(Exception.class, () -> {
                booking = new Booking("09-G-13921", testCentre, LocalDate.of(2024, 1, 12), LocalTime.of(47, 79));
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testInValidPastTimeWithValidBooking() {
        try {
            booking = null;
            assertThrows(Exception.class, () -> {
                booking = new Booking("09-G-13921", testCentre, LocalDate.now(), LocalTime.of(0, 0));
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testDateWithValidBookingNoDate() {
        booking = null;
        booking = new Booking("09-G-13921", testCentre);

        assertEquals(booking.getBookingDate(), LocalDate.of(2026, 2, 12));
    }

    @Test
    void testTimeWithValidBookingNoDate() {
        booking = null;
        booking = new Booking("09-G-13921", testCentre);

        assertEquals(booking.getBookingTime(), LocalTime.of(12, 30));
    }

    @Test
    void testMakeValidVehicleRegEdit() {
        try {
            booking.setVehicleReg("10-D-2841");
            assertEquals(booking.vehicleReg, "10-D-2841");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testMakeInvalidVehicleRegEdit() {
        try {
            booking.setVehicleReg(null);
            assertThrows(Exception.class, () -> {
                booking.setVehicleReg(null);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testQueryVehicleReg() {
        assertEquals(booking.getVehicleReg(), "09-G-13921");
    }

}


