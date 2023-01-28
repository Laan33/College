
public class Travel_Ireland //our main class which holds the 2 test cases 
{
    public static void main(String args[])
    {
        //Populates default journey objects we have stored.
        BusEireann be = new BusEireann();
        CityLink cl = new CityLink();
        GoBus gb = new GoBus();
        
        //Printing journey object data
        System.out.println(be.getAllJourneys());
        System.out.println(cl.getAllJourneys());
        System.out.println(gb.getAllJourneys());
        
        Travel_Ireland test = new Travel_Ireland();
        test.group1(be, cl, gb); //Calls the method for the first group booking and passes in the bus company objects
        test.group2(be, cl, gb); //Calls the method for the second group booking and passes in the bus company objects
                
        //Printing the requested booked journeys showing the amended seat number if successful.
        System.out.println(cl.getJourneyPrint(84012));
        System.out.println(gb.getJourneyPrint(54762));
    }
    
    public void group1(BusEireann be, CityLink cl, GoBus gb) { //group one with a valid amount of passengers 
        //Selecting a certain journey by using the journeyID. Returns null if object doesn't exist.
        Journey selectedJourney = cl.getJourney(84012);      

        Booking booking = new Booking(selectedJourney, 10); //using the selected journey for a booking with 10 passengers
        booking.confirmBooking(cl.makeBooking(booking)); //trying to confirm booking - sends confirmation with the price and other details
    }
    
    public void group2(BusEireann be, CityLink cl, GoBus gb) { //group two with an invalid amount of passengers 
        //Selecting a certain journey by using the journeyID. Returns null if object doesn't exist.
        Journey selectedJourney = gb.getJourney(54762);      

        Booking booking = new Booking(selectedJourney, 90); //using the selected journey for a booking with 90 passengers
        booking.confirmBooking(gb.makeBooking(booking)); //trying to confirm booking - sends error as bus doesn't have 90 free seats
    }
}


