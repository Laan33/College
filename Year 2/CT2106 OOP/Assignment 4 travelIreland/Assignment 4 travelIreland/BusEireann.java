
public class BusEireann extends ServiceTemplate //bus eireann as a company with routes extending off ServiceTemplate
{
    public BusEireann() {
        //creating our routes  
        Journey line52 = new Journey("Bus Eireann", "Galway", "Cork", "04/11/2022", "04/11/2022", "06:30", "09:20", 36490, 24, 16.50);
        Journey line45 = new Journey("Bus Eireann", "Galway", "Derry", "06/11/2022", "07/11/2022", "22:30", "01:10", 84012, 15, 19.50);
        Journey line89 = new Journey("Bus Eireann", "Wicklow Town", "Dingle", "12/11/2022", "12/11/2022", "14:20", "17:40", 94872, 05, 11.50);
        
        //adding our routes to the journeys array
        journeys.add(line52);
        journeys.add(line45);
        journeys.add(line89);
    }
}
