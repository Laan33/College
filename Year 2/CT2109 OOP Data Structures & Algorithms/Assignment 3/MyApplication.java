import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class MyApplication {

    public MyApplication() {

    }

    public static long testPalindromeMethod(MethodInterface method, int testNum) {
        int decimalPalindromes = 0, binaryPalindromes = 0, bothPalindomes = 0; // counts for amount of palindromes for
                                                                               // Decimal, Binary & both for the same
                                                                               // num
        boolean isDecPal, isBinPal = false;
        long timerStart = System.currentTimeMillis(); //timing how long to do testNum palindrome checks

        for (int i = 0; i <= testNum; i++) {
            isDecPal = false;
            isBinPal = false;
            String testDecString = Integer.toString(i); //dec string
            String testBinString = decimalToBinary(testDecString); //bin string

            if (method.method(testDecString)) {
                decimalPalindromes++;
                isDecPal = true;
            }

            if (method.method(testBinString)) {
                binaryPalindromes++;
                isBinPal = true;
            }

            if (isDecPal && isBinPal) { //if both are palindromes then we have a match
                bothPalindomes++;
            }
        }
        long timerStop = System.currentTimeMillis(); //stop timing

        //Comment this out if you are doing excel automation
        System.out.println("Time taken in milliseconds: " + (timerStop - timerStart) + "ms");
        System.out.println("\nDecimal Palindrome Count: " + decimalPalindromes + "\tBinary Palindrome Count: "
                + binaryPalindromes);
        System.out.println("Both decimal & binary palindrome count: " + bothPalindomes);         
        System.out.println("\nOperations count: " + method.getOperationsCount());
        System.out.println("\n--------------------------------------");
        

        return (method.getOperationsCount()); //operation counter for testNum numbers 

    }

    public static String decimalToBinary(String decimalString) { //using java inbuilt functions cut down on time taken as manually doing it can add 5 seconds over a million numbers :)
        int decimal = Integer.parseInt(decimalString); 
        String binaryString = Integer.toBinaryString(decimal);

        return binaryString;
    }

    public static void testAutomation(MethodInterface method) { //automation for excel numbers
        long[] operations = {0, 0, 0, 0, 0, 0}; //array to count operation count
        for (int i = 0; i <= 250000; i += 50000) { //up to a quarter of a million we check in 50,000 increments
            /*
            long averageTime = 0; //average time
            for (int j = 0; j < 4; j++) { //we check 4 times to get an average - this average was used to get the average time. Not needed for operations as it is the same each time
                averageTime += testPalindromeMethod(method, i);
            }           
             */ 
            operations[i / 50000] = testPalindromeMethod(method, i);
        }

        for (int i = 0; i <= 250000; i += 50000) {
            System.out.println("Length " + i + " requires: " + operations[i / 50000] + " operations");
        }

    }

    public static void main(String[] args) {
        int testIterations = 1000000;
        MethodInterface methodOne = new MethodOne();
        MethodInterface methodTwo = new MethodTwo();
        MethodInterface methodThree = new MethodThree();
        MethodInterface methodFour = new MethodFour();

        /*  // test the methods work if you like
        System.out.println("Enter a sample to test: ");
        Scanner s = new Scanner(System.in);
        String userString = s.nextLine();
        System.out.println("M1: " + methodOne.method(userString) + " M2: " + methodTwo.method(userString) + " M3: "
                + methodThree.method(userString) + " M4: " + methodFour.method(userString));
         */

        
        System.out.println("\nMethod One\n");
        testPalindromeMethod(methodOne, testIterations);
        System.out.println("\nMethod Two\n");
        testPalindromeMethod(methodTwo, testIterations);
        System.out.println("\nMethod Three\n");
        testPalindromeMethod(methodThree, testIterations);
        System.out.println("\nMethod Four\n");
        testPalindromeMethod(methodFour, testIterations);

        // automation of tests for excel - comment out testPalindromeMethod prints if
        // you use these
        /*
        System.out.println("\nMethod One\n");
        testAutomation(methodOne);
        System.out.println("\nMethod Two\n");
        testAutomation(methodTwo);
        System.out.println("\nMethod Three\n");
        testAutomation(methodThree);
        System.out.println("\nMethod Four\n");
        testAutomation(methodFour);
        */
    }
}
