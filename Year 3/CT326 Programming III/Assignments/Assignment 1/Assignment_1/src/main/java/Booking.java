import java.time.*;
import java.time.temporal.TemporalAdjusters;


public class Booking {

    String vehicleReg;
    double bookingID;
    private static double nextBookingID = 11111111; // Initialize with the minimum ID

    private final TestCentre testCenter;
    private LocalDate bookingDate;
    private LocalTime bookingTime;
    //plus 7 days and the next monday at 9:30

    private static NCTBookingSlotWebservice webService = testCentre ->
            LocalDateTime.now().plusDays(7).with(TemporalAdjusters.next(DayOfWeek.MONDAY)).with(LocalTime.of(9, 30));

    //Booking constructor with no date or time
    public Booking(String vehicleReg, TestCentre testCentre) {
        setWebService(webService);

        //checking if vehicle registration is null
        if (vehicleReg == null) {
            throw new IllegalArgumentException("Vehicle registration cannot be null");
        }
        this.vehicleReg = vehicleReg;
        //checking if test centre is null
        if (testCentre == null) {
            throw new IllegalArgumentException("Test centre cannot be null");
        }
        this.testCenter = testCentre;

        //setting booking date and time with web service
        LocalDateTime bookingDateTime = webService.getBookingDateTime(testCentre);
        //This is a messy way, but I had date and time as separate variables as I had taken that from brief
        this.bookingDate = bookingDateTime.toLocalDate();
        this.bookingTime = bookingDateTime.toLocalTime();

        bookingID = getNextBookingID();
    }

    public Booking(String vehicleReg, TestCentre testCentre, LocalDate bookingDate, LocalTime bookingTime) {
        //checking if vehicle registration is null
        if (vehicleReg == null) {
            throw new IllegalArgumentException("Vehicle registration cannot be null");
        }
        this.vehicleReg = vehicleReg;
        //checking if test centre is null
        if (testCentre == null) {
            throw new IllegalArgumentException("Test centre cannot be null");
        }
        this.testCenter = testCentre;

        //checking if booking date or time is in the past
        setBookingDateTime(bookingDate, bookingTime);

        bookingID = getNextBookingID();
    }

    // Method to get the next unique booking ID
    private static synchronized double getNextBookingID() {
        double uniqueID = nextBookingID;
        // Increment the nextBookingID for the next booking
        nextBookingID++;
        return uniqueID;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public LocalTime getBookingTime() {
        return bookingTime;
    }

    public void setVehicleReg(String vehicleReg) {
        if (vehicleReg == null) {
            throw new IllegalArgumentException("Vehicle registration cannot be null");
        } else if (vehicleReg.isEmpty()) {
            throw new IllegalArgumentException("Vehicle registration cannot be empty");
        }
        this.vehicleReg = vehicleReg;
    }

    public void setWebService(NCTBookingSlotWebservice webService) {
        Booking.webService = webService;
    }

    public String getVehicleReg() {
        return vehicleReg;
    }

    public void setBookingDateTime(LocalDate bookingDate, LocalTime bookingTime) {
        //checking if booking date or time is in the past
        if (bookingDate == null) {
            throw new IllegalArgumentException("Booking date cannot be null");
        } else if (bookingDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Booking date cannot be in the past");
        }

        try {
            this.bookingDate = bookingDate;
        } catch (DateTimeException e) {
            throw new IllegalArgumentException("Booking date is not valid");
        }

        if (bookingTime == null) {
            throw new IllegalArgumentException("Booking time cannot be null");
        } else if (bookingDate.isEqual(LocalDate.now()) && bookingTime.isBefore(LocalTime.now())) {
            throw new IllegalArgumentException("Booking time cannot be in the past");
        }

        try {
            this.bookingTime = bookingTime;
        } catch (DateTimeException e) {
            throw new IllegalArgumentException("Booking time is not valid");
        }
    }

    public String getTestCentre() {
        return testCenter.toString();
    }

    public double getBookingID() {
        return bookingID;
    }

    @Override
    public String toString() {
        return "Booking ID: " + bookingID + "\n Registration Number: " + vehicleReg + "\n" + testCenter.toString() +
                "\nDate & Time: On" + bookingDate.getDayOfWeek().toString() + ", " + bookingDate.toString() + " at " + bookingTime.toString();
    }
}
