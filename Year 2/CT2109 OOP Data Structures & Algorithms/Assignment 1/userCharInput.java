import java.util.Scanner;  // Import the Scanner class

public class userCharInput {

    static char alphabetArray[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
                                  'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
                                  's', 't', 'u', 'v', 'w', 'x', 'y', 'z'}; //alphabet array :)
    long startTime = 0;
    timings timer = new timings();  //creating timing object


    public void inputField() {
        
        
        try (Scanner myObj = new Scanner(System.in)) {
            System.out.println("\nGame started, you'll be timed after this input!.");
            System.out.println("Run through alphabet forwards or backwards? Enter F or B: ");

            String direction = myObj.nextLine();  // Read user input
            

            direction = direction.toUpperCase(); //sanatising input, in case the user is lazy - like me!
            startTime = timer.timeCalculationStart(); //calling timings to start

            switch(direction) {
                case "F": //if F we have a forwards game method called
                    iterateForwards();  
                    break;  
                
                case "B": //likewise, B calls a backwards method
                    iterateBackwards();
                    break;

                default: //else let user know input is invalid - wish them a good day
                    System.out.println("Invalid, must be F or B to start. \nGood luck - try again later!");
                    System.exit(0);
            }

        } catch (Exception e) {
            System.exit(0);
        }

    }

    public void iterateForwards() {
        try (Scanner charObjScanner = new Scanner(System.in)) { //try a scanner 
            char c = '.'; //initialise char c.
            
            System.out.println("\nAll inputs must be lower case, hit enter between letters");
            System.out.println("\nPlease type: " + alphabetArray[0]); //letting user know what to start off with

            for (int i = 0; i < alphabetArray.length; i++) { // for length of alphabet we'll iterate across it
                while (!(alphabetArray[i] == c)) { //while c is not the same as current array element
                    c = charObjScanner.next().charAt(0); //we scan a new character in
                    //if it was charObjScanner.next(".").charAt(0), it would crash the program when more than one char entered
                }            
                if (i < alphabetArray.length - 1){ //if statement to make sure we don't access array elements that don't exist
                System.out.println("Well done, now type: " + alphabetArray[i + 1]);     
                }       
            }
            System.out.println("\nYou completed it, well done!");

            timer.timeCalculationEnd(startTime);  //once game is finished we stop the timer
            //it also outputs the total time to user 
        }            
        catch (Exception e) { System.exit(0); } //catching errors with try and catch        
    }

    public void iterateBackwards() {
        try (Scanner charObjScanner = new Scanner(System.in)) { //same as above, new scanner and char
            char c = '.';

            System.out.println("\nAll inputs must be lower case, \nPlease type: " + alphabetArray[alphabetArray.length - 1]); //type last element of array to start

            for (int i = alphabetArray.length - 1; i >= 0; i--) { //starting at end of array, we'll descend across it to the start.             
                while (!(alphabetArray[i] == c)) {
                    c = charObjScanner.next().charAt(0);                    
                }  
                if (i > 0){ //making sure not to go past the index of array, we're not in python so we can go -1 on arrays
                    System.out.println("Well done, now type: " + alphabetArray[i - 1]);     
                }                                  
            }

            System.out.println("\nYou completed it, well done!");

            timer.timeCalculationEnd(startTime);  //once game is finished we stop the timer
            //it also outputs the total time to user 
        }   
        catch (Exception e) { System.exit(0);  } //catch again        
    }
}