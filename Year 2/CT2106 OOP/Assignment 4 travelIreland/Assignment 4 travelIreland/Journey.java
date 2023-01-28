

public class Journey
{
    String cName, origin, destination, dDate, aDate, dTime, aTime;
    int journeyID, seatsEmpty;
    double ticketCost;
    
    //Our fields and setting them to the journey object
    public Journey (String cName, String origin, String destination, String dDate, String aDate, String dTime, String aTime, int journeyID, int seatsEmpty, double ticketCost) {
        this.cName = cName;
        this.origin = origin;
        this.destination = destination;
        this.dDate = dDate;
        this.aDate = aDate;
        this.dTime = dTime;
        this.aTime = aTime;
        
        this.journeyID = journeyID;
        this.seatsEmpty = seatsEmpty;
        
        this.ticketCost = ticketCost;    
    }
    
    
    //Setting the vacant seats
    public void setSeats(int eSeats) {
        seatsEmpty = eSeats;
    }    
    
    //Getting our various fields
    public int getID() {
        return journeyID;
    }
    
    public int getSeats() {
        return seatsEmpty;
    }
    
    public double getCost() {
        return ticketCost;
    }
    
    public String getOrigin() {
        return origin;
    }
    
    public String getDestination() {
        return destination;
    }
    
    
    
    
}
