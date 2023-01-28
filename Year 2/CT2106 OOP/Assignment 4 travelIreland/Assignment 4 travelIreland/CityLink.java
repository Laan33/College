
public class CityLink extends ServiceTemplate //CityLink as a company with routes extending off ServiceTemplate
{
    public CityLink() {
        //creating our routes  
        Journey line06 = new Journey("City Link", "Galway", "Limerick", "04/11/2022", "04/11/2022", "05:30", "06:40", 25529, 30, 16.50);
        Journey line72 = new Journey("City Link", "Galway", "Tipperary Town", "07/11/2022", "07/11/2022", "22:30", "01:10", 84012, 15, 15.80);
        Journey line10 = new Journey("City Link", "Bray", "Belfast", "12/11/2022", "12/11/2022", "13:20", "16:40", 94872, 05, 12.50);
        
        //adding our routes to the journeys array
        journeys.add(line06);
        journeys.add(line72);
        journeys.add(line10);
    }
}
