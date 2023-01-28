
/**
 * 
 * @Cathal
 * @V1.2
 */
public class AnimalTest
{
    // instance variables - replace the example below with your own
       /**
     * Constructor for objects of class AnimalTest
     */
    public static void main(String[] args)
    {
       AnimalTest test = new AnimalTest();
       test.test1(); // calls the method holding our first test scenario
       test.test2(); // calls the method holding our second test scenario
        
    } 

    public void test1()
    {
        System.out.println("\n----------------- \n");
        System.out.println("First test case\n");
        System.out.println("Just an array showing our various leaf animals and their attriubtes they've inherited.");
        System.out.println("\n----------------- \n");
        
        Animal[] animals = new Animal[4];
        
        animals[0] = new Canary("Damon");
        animals[1] = new Ostrich("Graham");
        animals[2] = new Shark("Alex");
        animals[3] = new Trout("David");
        
        for (int i = 0; i < animals.length; i++) {
            System.out.print(animals[i]); //printing out the animals toString methods 
        }
    }
    
    public void test2() {
        System.out.println("\n\n----------------- \n");
        System.out.println("Second test case\n");
        System.out.println("Using arrays to compare all of our animal objects");
        System.out.println("\nEmploying the equals method to compare them.");
        System.out.println("\n----------------- \n");
        
        // New animal objects
        Animal[] animals = new Animal[11]; //an array holding lots of the animals with three pairs / matches
        animals[0] = new Trout("Evan");
        animals[1] = new Canary("Roisin");
        animals[2] = new Canary("Ciara");
        animals[3] = new Trout("Rob");
        animals[4] = new Shark("Bob");
        animals[5] = new Ostrich("Finn");
        animals[6] = new Shark("Bob");
        animals[7] = new Ostrich("Bill");
        animals[8] = new Canary("Jeb");
        animals[9] = new Canary("Jeb");
        animals[10] = new Ostrich("Bill");
        
         

        for (int j = 0; j < animals.length; j++) { // Goes through all animal objects comparing them
            System.out.printf("\nComparing using %s the %s ", animals[j].getName(), animals[j].getInstanceName() );
            int y = j; // y = j, as it there's no point backtracking on yourself checking already checked animals
            for (int i = 0; i < animals.length; i++) {
                if (animals[j].equals(animals[i]) && j != i && !(i < j)) { // Comparing the animal[j] to all of the other animals between positions j + 1 and 9 - the rest of the conditions is to stop double counting
                    // Printing out a match
                    System.out.printf("\n%s the %s with the position %d in animal array matched with %s the %s with the position %d \n", animals[j].getName(), animals[j].getInstanceName(), j, animals[i].getName(), animals[i].getInstanceName(), i); 
                } /*  
                  ** For if the two objects don't match - not using it as it fills up the screen with spam
                  else {
                  System.out.printf("%s the %s in position %d doesn't match %s the %s in position %d\n", animals[j].getName(), animals[j].getInstanceName(), j, animals[i].getName(), animals[i].getInstanceName(), i);
                }
                  */
                
            }
        }
    }
}
