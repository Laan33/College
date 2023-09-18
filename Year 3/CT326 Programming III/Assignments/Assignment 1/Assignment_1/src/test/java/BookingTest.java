
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookingTest {
    @BeforeEach
    void setUp() throws Exception {
    }
    @Test
    void testMakeAValidBooking() {
        Booking booking;
        Booking.TestCentre testCentre;
        testCentre = new Booking.TestCentre("Ballinasloe", "Unit 9, Pollboy Industrial Estate,\n" +
                "Ballinasloe, Galway H53 NW94");
        try {
            booking = new Booking("09-G-13921", testCentre);
            assertTrue(testCentre.equals(booking.getTestCentre()));

        } catch (InvalidBookingException e) {
            e.printStackTrace();
        }
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
