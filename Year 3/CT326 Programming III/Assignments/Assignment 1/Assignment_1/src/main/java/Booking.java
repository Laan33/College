import java.time.*;


public class Booking {

    String vehicleReg;
    double bookingID;
    TestCentre testCenter;
    LocalDate bookingDate;
    LocalTime bookingTime;


    public Booking(String vehicleReg, TestCentre testCentre) {

        bookingID = setBookingID();
    }

    public Booking(String vehicleReg, TestCentre testCentre, LocalDate bookingDate, LocalTime bookingTime) {


        bookingID = setBookingID();


    }

    public TestCentre getTestCentre() {
        return testCenter;
    }

    public double setBookingID() {
        //randomly generate a booking ID is between 11111111 and 99999999
        return Math.floor(Math.random() * (99999999 - 11111111) + 11111111);
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public LocalTime getBookingTime() {
        return bookingTime;
    }

    public void setVehicleReg(String vehicleReg) {
        this.vehicleReg = vehicleReg;
    }

    public interface NCTBookingSlotWebservice {
        public LocalDateTime getBookingDateTime(TestCentre testCentre);
    }
}
