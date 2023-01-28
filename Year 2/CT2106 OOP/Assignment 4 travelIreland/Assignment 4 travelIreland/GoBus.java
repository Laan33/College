
public class GoBus extends ServiceTemplate //GoBus as a company with routes extending off ServiceTemplate
{
    public GoBus() {
        //creating our routes  
        Journey line13 = new Journey("Go Bus", "Galway", "Cork", "05/11/2022", "05/11/2022", "08:30", "11:20", 54762, 32, 17.50);
        Journey line35 = new Journey("Go Bus", "Galway", "Thurles", "09/11/2022", "09/11/2022", "17:30", "20:10", 25675, 10, 17.50);
        Journey line77 = new Journey("Go Bus", "Longford Town", "Dublin", "12/11/2022", "12/11/2022", "11:20", "14:40", 86774, 8, 14.50);
        
        //adding our routes to the journeys array
        journeys.add(line13);
        journeys.add(line35);
        journeys.add(line77);
    }
}
