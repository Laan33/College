public class MethodFour implements MethodInterface {
    

    protected long oCount = 0; //operations counter
    
    @Override
    public boolean method(String testString) {
        String reverseString = reverse(testString);
        return testString.equals(reverseString);
    }

    public String reverse(String testString) {
        //if string inputted is empty or length one - we know it's a palindrome - base case
        if (testString.length() <= 1) { //2 - checks if smaller than 1, and if equals to 1. 
            oCount += 2;
            return testString;
        }
        oCount += 2;
        //n - the method is making n recursive calls, and for each call, it is creating a new substring which is the input string length - 1
        String reverseSubStr = reverse(testString.substring(1)); // 2 - substring & initialising a string 
        oCount += 2;
        return reverseSubStr + testString.charAt(0);
    }


    public long getOperationsCount(){
        return this.oCount;
    }

    public void setOperationsCount(long oCount) {
        this.oCount = oCount;
    }
}
