public class expressionToPostfixAlgo {
    
    public String stackManipulator(char[] infixChars) {
        String postfixOutput = ""; //output string 
        ArrayStack postfixStack = new ArrayStack(); //instanciating a stack to use
        char currentChar, topOfStack = '0';

        for(int i = 0; i < infixChars.length; i++) { //through the whole infixChars array
            currentChar = infixChars[i]; //assign the current char to a variable

            switch (currentChar) {
                case '+':
                case '-':
                case '*':
                case '/':
                case '^': //if operator found 
                    if(!postfixStack.isEmpty()){ //if statment to stop us accessing array if its empty
                        topOfStack = (char)postfixStack.top(); //top of stack char stored
                    }    
                    
                    //while the precedence of the current operator is the same or less of the operand currently in the stack, 
                    //we output everything to the output string, after we push current operator
                    while(precedenceCalc((char)currentChar) <= precedenceCalc(topOfStack) && !postfixStack.isEmpty()) {
                        postfixOutput += postfixStack.pop(); 
                    }
                    postfixStack.push(currentChar);
                    break;
                
                case '(': //if (, we push 
                    postfixStack.push(currentChar);
                    break;
                    

                case ')': //if )
                    //while stack isn't empty, and we haven't encountered (, we print it all to output & pop
                    while(!(postfixStack.isEmpty() || (char)postfixStack.top() == '(' ) ) {
                        postfixOutput += postfixStack.pop();
                    } //once we hit (, we stop outputting to string, and discard the (
                    postfixStack.pop(); //discarding the '(' from the stack
                break;

                default: //we have done sanatation on string input, we now assume all remaining characters is a singular digit
                    postfixOutput += currentChar; //putting it to output
                    break;           
            }
        }

        while(!postfixStack.isEmpty()) { //once the string is finished, we pop the rest of stack to output
            postfixOutput += postfixStack.pop();
            
        }
        
        return postfixOutput; //return the output
    }


    
    
    public int precedenceCalc(char operator) {
        switch(operator) { //switch based on the operator passed in 
            case '^': //Power of (^) returns highest precedence 
                return 3;
                

            case '*': //Multiplication or division (* or /) returns next highest precedence 
            case '/':
                return 2;
                

            case '+': //Addition or subtraction (+ or -) returns lowest precedence 
            case '-':
                return 1;
                
            default: //else we return a lower precednce for digits etc.
               return -1;            
        }         
    }
}
