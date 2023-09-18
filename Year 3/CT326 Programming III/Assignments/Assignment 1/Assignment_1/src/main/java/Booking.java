import java.time;
import java.time.LocalDate;
import java.time.LocalTime;

public class Booking {

    String vehicleReg;
    TestCentre testCenter;
    LocalDate bookingDate;
    LocalTime bookingTime;


    public Booking(String vehicleReg, TestCentre testCentre) {

    }

    public Booking(String vehicleReg, TestCentre testCentre, LocalDate bookingDate, LocalTime bookingTime) {

    }

    public class TestCentre {
        private String centreName;
        private String centreAddress;

        public TestCentre(String centreName, String centreAddress) {
            this.centreName = centreName;
            this.centreAddress = centreAddress;
        }

        public String getCentreName() {
            return centreName;
        }

        public void setCentreName(String centreName) {
            this.centreName = centreName;
        }

        public String getCentreAddress() {
            return centreAddress;
        }

        public void setCentreAddress(String centreAddress) {
            this.centreAddress = centreAddress;
        }

        @Override
        public String toString() {
            return "Centre: " + centreName + " Address: " + centreAddress;
        }
    }
}
