public class postfixStringCalculator {
    
    public double postfixResult(String inputString){ //input string of postfix expression
        char chr = '.';
        double result = 0.0;
        ArrayStack operandStack = new ArrayStack();        

        for(int i = 0; i < inputString.length(); i++) { //for postfix input string length
            chr = inputString.charAt(i); //assign current char to chr variable

            if(Character.isDigit(chr)) { //if a digit, we push it to the stack
                operandStack.push((double)Character.getNumericValue(chr)); //pushed as Number object as a double 
            }

            else {  //otherwise, as an operator
                double temp1 = (double)operandStack.pop(); //assign our two temporary variables as the top two numbers off the stack,
                double temp2 = (double)operandStack.pop(); //to be used in calculations 
                
                switch(chr) { //depending on the operator we do the following maths operation
                    case '^': //power of
                        result = Math.pow(temp2, temp1);
                        break;
                    case '/': //division
                        result = temp2 / temp1; 
                        break;
                    case '*': //multiplication
                        result = temp2 * temp1; 
                        break;
                    case '+': //addition
                        result = temp2 + temp1; 
                        break;
                    case '-': //subtraction
                        result = temp2 - temp1; 
                        break;                    
                } 
                System.out.println(temp2 + " " + chr + " " + temp1 + " = " + result); //prinitng results to the console
                
                operandStack.push((double)result); //push the result to the stack 
            }
        }
        result = (Double)operandStack.pop(); //only item on stack should be the result, which we pop

        return result; //return result
    }
    
}