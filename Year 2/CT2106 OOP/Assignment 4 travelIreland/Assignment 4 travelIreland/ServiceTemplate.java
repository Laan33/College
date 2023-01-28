

import java.util.ArrayList;

//boiler plate class for all the bus companys to start off with - arrayList of their routes and proscessing bookings
public abstract class ServiceTemplate 
{   
    public ArrayList<Journey> journeys = new ArrayList<>();  //arrayList with all of our journeys 
    
    public String toString(int k) { //toString method to print all of our journeys and their details out 
        String tempOut = "";
        
        tempOut += "Company Name: " + journeys.get(k).cName;
        tempOut += "\nJourney ID: " + journeys.get(k).journeyID;
        tempOut += "\nOrigin: " + journeys.get(k).origin;
        tempOut += "\nDestination: " + journeys.get(k).destination;
        
        tempOut += "\nDeparture Date: " + journeys.get(k).dDate;
        tempOut += "\nDeparture Time: " + journeys.get(k).dTime;
        tempOut += "\nArrival Date: " + journeys.get(k).aDate;
        tempOut += "\nArrival Time: " + journeys.get(k).aTime;
        
        tempOut += "\nTicket price: " + journeys.get(k).ticketCost + " per passenger";
        tempOut += "\nAvailable seats: " + journeys.get(k).seatsEmpty;
        
        return tempOut;
    }
    
    public boolean makeBooking(Booking booking) {
        if(booking.getNumPassengers() <= booking.getJourney().getSeats()) { //if there are adequate seats available
            //Message for how many seats there are
            System.out.println("There is: " + booking.getJourney().getSeats() + " seats, for: " + booking.getNumPassengers() + " passengers, in booking: " + booking.getJourney().getID());
            //Setting the seats in on the booked journey to reflect that there has been a booking
            booking.getJourney().setSeats(booking.getJourney().getSeats() - booking.getNumPassengers());     
            //Success so returns true
            return true;
        }
        
        else {
            //Message for how many seats there are
            System.out.println("There is: " + booking.getJourney().getSeats() + " seats, for: " + booking.getNumPassengers() + " passengers, in booking: " + booking.getJourney().getID());
            //Failure so the boolean is false
            return false; 
        }
    }
    
    
    public String getAllJourneys() { //printing all the journeys in the company
        String tempOut = "";        
        for(int j = 0; j < journeys.size(); j++) {
            tempOut += toString(j) + "\n\n\n";
        }
        return tempOut; //concatenated string with all journeys 
    }
    
    public String getJourneyPrint(int ID) { //printing out a certain journey's details
        String tempOut = "No journey found"; //placeholder error in case the journey is not found.
        for(int i = 0; i < journeys.size(); i++) {
            if(journeys.get(i).getID() == ID) {
                tempOut = toString(i) + "\n\n";
            }      
        }
        return tempOut;
    }
    
    public Journey getJourney(int ID) { //method to return a specific journey using it's ID
        for(int i = 0; i < journeys.size(); i++) {
            if(journeys.get(i).getID() == ID) {
                return journeys.get(i);
            }      
        }
        return null; //if not found null will be returned
    }
    
    
}
