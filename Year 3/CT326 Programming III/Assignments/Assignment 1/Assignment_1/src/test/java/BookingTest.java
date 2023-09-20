import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.temporal.TemporalAdjusters;

import static org.junit.jupiter.api.Assertions.*;

public class BookingTest {
    Booking booking;
    TestCentre testCentre;

    @BeforeEach
    void setUp() {
        testCentre = new TestCentre("Ballinasloe", "Unit 9, Pollboy Industrial Estate,\n" + "Ballinasloe, Galway H53 NW94");
        booking = new Booking("09-G-13921", testCentre, LocalDate.of(2024, 1, 12), LocalTime.of(12, 30));
    }

    @AfterEach
    void tearDown() {
        booking = null;
        testCentre = null;
    }

    @Test
    void testValidDateWithValidBooking() {
        assertEquals(booking.getBookingDate(), LocalDate.of(2024, 1, 12));
    }

    @Test
    void testInValidPastDateWithValidBooking() {
        booking = null;
        assertThrows(Exception.class, () -> booking = new Booking("09-G-13921", testCentre, LocalDate.of(1999, 1, 12), LocalTime.of(12, 30)));
    }
    @Test
    void testInValidDateWithValidBooking() {
        try {
            booking = null;
            booking = new Booking("09-G-13921", testCentre, LocalDate.of(19299, 101, -1), LocalTime.of(12, 30));
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThrows(Exception.class, () -> booking = new Booking("09-G-13921", testCentre, LocalDate.of(19299, 101, -1), LocalTime.of(12, 30)));
    }

    @Test
    void testTimeWithValidBooking() {
        assertEquals(booking.getBookingTime(), LocalTime.of(12, 30));
    }

    @Test
    void testInValidTimeWithValidBooking() {
        try {
            booking = null;
            assertThrows(Exception.class, () -> booking = new Booking("09-G-13921", testCentre, LocalDate.of(2024, 1, 12), LocalTime.of(47, 79)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testInValidPastTimeWithValidBooking() {
        try {
            booking = null;
            assertThrows(Exception.class, () -> booking = new Booking("09-G-13921", testCentre, LocalDate.now(), LocalTime.of(0, 0)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testDateWithValidBookingNoDate() {
        booking = null;
        booking = new Booking("09-G-13921", testCentre);

        assertEquals(booking.getBookingDate(), LocalDate.now().plusDays(7).with(TemporalAdjusters.next(DayOfWeek.MONDAY)));
    }

    @Test
    void testTimeWithValidBookingNoDate() {
        booking = null;
        booking = new Booking("09-G-13921", testCentre);

        assertEquals(booking.getBookingTime(), LocalTime.of(9, 30));
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
            assertThrows(Exception.class, () -> booking.setVehicleReg(null));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            booking.setVehicleReg("");
            assertThrows(Exception.class, () -> booking.setVehicleReg(null));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testQueryVehicleReg() {
        assertEquals(booking.getVehicleReg(), "09-G-13921");
        booking.setVehicleReg("10-D-2841");
        assertEquals(booking.getVehicleReg(), "10-D-2841");
    }

    @Test
    void testQueryTestCentre() {
        assertEquals(booking.getTestCentre().toString(), testCentre.toString());
    }

    @Test
    void testCentreNullName() {
        assertThrows(IllegalArgumentException.class, () -> new TestCentre(null, "Address1"));
    }

    @Test
    void testCentreNullAddress() {
        assertThrows(IllegalArgumentException.class, () -> new TestCentre("TestCentre1", null));
    }

    @Test
    void testUniqueBookingID() {
        // Create multiple Booking objects
        Booking booking1 = new Booking("Vehicle1", new TestCentre("TestCentre1", "Address1"));
        Booking booking2 = new Booking("Vehicle2", new TestCentre("TestCentre2", "Address2"));
        Booking booking3 = new Booking("Vehicle3", new TestCentre("TestCentre3", "Address3"));

        // Check if bookingIDs are unique
        assertNotEquals(booking1.getBookingID(), booking2.getBookingID());
        assertNotEquals(booking1.getBookingID(), booking3.getBookingID());
        assertNotEquals(booking2.getBookingID(), booking3.getBookingID());
    }

    @Test
    void testBookingToString() {
        assertEquals(booking.toString(), "Booking ID: " + booking.getBookingID() +
                "\n Registration Number: " + booking.getVehicleReg() +
                "\n" + booking.getTestCentre() + "\nDate & Time: On" +
                booking.getBookingDate().getDayOfWeek().toString() + ", " + booking.getBookingDate().toString() +
                " at " + booking.getBookingTime().toString());
    }

}


