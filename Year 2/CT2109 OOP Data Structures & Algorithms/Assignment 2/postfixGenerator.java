import javax.swing.JOptionPane;

public class postfixGenerator {
        

    public static void main(String[] args) {
        postfixGenerator runCalculator = new postfixGenerator(); 
        runCalculator.postFixCalculator(); //running my program
    }

    char[] specialCharacters = {'^', '/', '*', '+', '-', '(',')'}; //permitted characters outside of digits

    public void postFixCalculator() {
        expressionToPostfixAlgo algorithm = new expressionToPostfixAlgo(); //instanciating a new prefix to postfix algorithm 
        postfixStringCalculator stringCalculated = new postfixStringCalculator(); //instanciating calculator for evaluating postfix notation expressions

        String inputInfixExpression = JOptionPane.showInputDialog("Enter expression - Sample: '2*3-4'", null);//taking a new input from user
        
        char inputChar[] = inputInfixExpression.toCharArray(); //converting our string into chars in an array      

        while (!errorChecker(inputInfixExpression, inputChar)) { //while the string isn't passing conditions - alert user and input new string
            
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid expression with only operators or operands, \n(minimum 3 characters and maximum 20 characters)");    
            inputInfixExpression = JOptionPane.showInputDialog("Enter expression - Sample: '2*3-4'", null);
            inputChar = inputInfixExpression.toCharArray(); 
        }

        
        String postfixOutput = algorithm.stackManipulator(inputChar); //assigning postfix String to the output of our postfix calculator
        System.out.println("Postfix expression: " + postfixOutput + "\n");


        
        
        double result = stringCalculated.postfixResult(postfixOutput);//assigning calculations from the postfix expression 
        JOptionPane.showMessageDialog(null, "Result of the expression string: \n" + "Infix: " +  inputInfixExpression 
                                                            + "\nPostfix: " + postfixOutput + "\nResult: " + result);

        
    }

    public boolean errorChecker(String inputInfixExpression, char[] inputChar) { //passed in string, checking if it's valid
        if (inputInfixExpression.length() > 20 || inputInfixExpression.length() < 3) { //length checking for compliance
            return false;
        }

        for(int i = 0; i < inputInfixExpression.length() - 1; i++) { //going through all the whole input string / char array

            if(Character.isDigit(inputChar[i])) { //if the char is a digit
                //we will check for a following digit, if we are not at end of an array 
                if(  i != inputInfixExpression.length() && 
                    (Character.isDigit(inputChar[i]) && Character.isDigit(inputChar[i+1]) ) ){ 
                    return false; //if there are two digits beside each other, return false e.g. 93 is above 0-9
                }
            }  
            else if(Character.isAlphabetic(inputChar[i])) { //if character is alphabetic, return false
                return false;
            } 
            else if (!specialcharChecker(inputChar[i])) { //if the character is not in the special characters array, return false 
                return false;
            }            
        }
        return true; //return true if no invalid characters incurred
    }

    public boolean specialcharChecker(char charToCheck) {
        for (char elem : specialCharacters) { //for all the elements in specialCharacters, we check if our char is there, returning true if it is
            if (charToCheck == elem) {
                return true;
            }
        }
        return false; //if not there, return false
    }

}
