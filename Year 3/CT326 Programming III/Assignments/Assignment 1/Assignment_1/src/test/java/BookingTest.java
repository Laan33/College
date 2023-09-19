
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    void testValidDateWithValidBooking() {
        assertEquals(booking.getBookingDate(), LocalDate.of(2024, 1, 12));
    }

    @Test
    void testInValidPastDateWithValidBooking() {
        booking = null;
        booking = new Booking("09-G-13921", testCentre, LocalDate.of(1999, 1, 12), LocalTime.of(12, 30));

        assertEquals(booking.getBookingDate(), LocalDate.of(2024, 1, 12));
    }
    @Test
    void testInValidDateWithValidBooking() {
        try {
            booking = null;
            booking = new Booking("09-G-13921", testCentre, LocalDate.of(19299, 101, -1), LocalTime.of(12, 30));
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(booking.getBookingDate(), LocalDate.of(2024, 1, 12));
    }

    @Test
    void testTimeWithValidBooking() {
        assertEquals(booking.getBookingTime(), LocalTime.of(12, 30));
    }

    @Test
    void testInValidTimeWithValidBooking() {
        try {
            booking = null;
            booking = new Booking("09-G-13921", testCentre, LocalDate.of(2024, 1, 12), LocalTime.of(47, 79));
            assertEquals(booking.getBookingTime(), LocalTime.of(12, 30));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testDateWithValidBookingNoDate() {
        booking = null;

        testCentre = new TestCentre("Ballinasloe", "Unit 9, Pollboy Industrial Estate,\n" + "Ballinasloe, Galway H53 NW94");

        booking = new Booking("09-G-13921", testCentre);

        assertEquals(booking.getBookingDate(), LocalDate.now());

    }

    @Test
    void testMakeValidVehicleRegEdit() {
        try {
            booking.setVehicleReg("10-D-2841");
            assertEquals(booking.vehicleReg, "10-D-2841");
        } catch (Exception e) {
            e.printStackTrace();
        };
    }

    @Test
    void testMakeInvalidVehicleRegEdit() {
        try {
            booking.setVehicleReg(null);
            assertEquals(booking.vehicleReg, "10-D-2841");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}

class AccountTest {
    @BeforeEach
    void setUp() throws Exception {
    }
    @Test
    void testMakeAValidDeposit() {
        Account account;
        try {
            account = new Account(123, 0);
            account.makeDeposit(500.0);
            assertTrue(account.getBalance() == 500.0);
            account.makeDeposit(10.0);
            assertTrue(account.getBalance() == 510.0);
        } catch (NegativeBalanceException e) {
            e.printStackTrace();
        }
    }
    @Test
    void testMakeInvalidDeposit_negativeAmount() {
        Account account;
        try {
            account = new Account(123, 0);
            double prevBalance = account.getBalance();
            account.makeDeposit(-500.0);
            assertTrue(account.getBalance() == prevBalance);
        } catch (NegativeBalanceException e) {
            e.printStackTrace();
        }
    }
    @Test
    void testValidWithdrawal() {
        try {
            Account account = new Account(123, 0);
            account.makeDeposit(50);
            account.makeWithdrawal(10);
            assertTrue(account.getBalance() == 40);
        } catch (NegativeBalanceException e ) {
            e.printStackTrace();
        } catch (InsufficientFundsException e) {
            e.printStackTrace();
        }
    }
    @Test
    void testInvalidWithdrawal_insufficientFunds() {
        try {
            Account account = new Account(123, 0);
            assertThrows(InsufficientFundsException.class, () -> {
                account.makeWithdrawal(300);
            });
        } catch (NegativeBalanceException e) {
            e.printStackTrace();
        }
    }
}
