
/**
 * Write a description of class Booking here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Booking
{
    
    
    Journey journey;//journey object for the booking
    int numPassengers = 1; //minimum one passenger
        
    public Booking(Journey journey, int numPas)
    {
        if(journey != null) { //if null isn't passed through we assign the passed journey object to the local journey object
            this.journey = journey;
        }
        else { //error if journey is null
            System.out.print("Error in trip entered - no trip with that ID, exiting.");
            System.exit(0);
        }
        numPassengers = numPas; //setting variable numPassengers for numPassengers method
        //originally was setting the new allocation of seats on this line but it was too early in the process to amend it.   
    }

    public void confirmBooking(boolean bookingConfirm) {//boolean passed in from makeBooking with success or failure
        if(bookingConfirm) //Printing out sucessful booking
            {
            System.out.println("\nBooking Confirmed!");
            System.out.println("==========================================");
            System.out.println("Number of passengers: " + getNumPassengers());
            System.out.println("Trip Details:");
            System.out.println("Departure: " + getJourney().getOrigin() + " Arrival at: " + getJourney().getDestination());     
            System.out.println("Total Cost: €" + getTotal());
            System.out.println("==========================================");     
        }
        else { //Error message
            System.out.println("\nBooking Failed! - Check booking request.\n\n");
        }
    }
        
    // Getter methods
    public Journey getJourney() {
        return journey;
    }
    
    public int getNumPassengers() {
        return numPassengers;
    }
    
    public double getTotal() {
        return getNumPassengers() * journey.getCost();
    }
}
