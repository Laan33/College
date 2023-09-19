import java.time.*;


public class Booking {

    String vehicleReg;
    double bookingID;
    TestCentre testCenter;
    LocalDate bookingDate;
    LocalTime bookingTime;
    private static NCTBookingSlotWebservice webService = new NCTBookingSlotWebservice() {
        @Override
        public LocalDateTime getBookingDateTime(TestCentre testCentre) {
            return LocalDateTime.of(2026, 2, 12, 12, 30);
        }
    };


    public Booking(String vehicleReg, TestCentre testCentre) {
        setWebService(webService);

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
        System.out.println(bookingDateTime + "hi");
        //This is a messy way but I had date and time as separate variables
        this.bookingDate = bookingDateTime.toLocalDate();
        this.bookingTime = bookingDateTime.toLocalTime();

        bookingID = setBookingID();
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

/*
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;*/

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
        if (vehicleReg == null) {
            throw new IllegalArgumentException("Vehicle registration cannot be null");
        }
        this.vehicleReg = vehicleReg;
    }

    public void setWebService(NCTBookingSlotWebservice webService) {
        this.webService = webService;
    }

    public String getVehicleReg() {
        return vehicleReg;
    }

    public boolean setBookingDateTime(LocalDate bookingDate, LocalTime bookingTime) {
        //checking if booking date or time is in the past
        if (bookingDate == null) {
            throw new IllegalArgumentException("Booking date cannot be null");
        } else if (bookingDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Booking date cannot be in the past");
        }

        try {
            this.bookingDate = bookingDate;
            // Attempt to create a valid date; if it's invalid, this will throw an exception
            //LocalDate testDate = LocalDate.of(bookingDate.getYear(), bookingDate.getMonth(), bookingDate.getDayOfMonth());
        } catch (DateTimeException e) {
            throw new IllegalArgumentException("Booking date is not valid");
        }

        if (bookingTime == null) {
            throw new IllegalArgumentException("Booking time cannot be null");
        } else if (bookingDate.isEqual(LocalDate.now()) && bookingTime.isBefore(LocalTime.now())) {
            throw new IllegalArgumentException("Booking time cannot be in the past");
        }


        this.bookingTime = bookingTime;
        return true;
    }
}
