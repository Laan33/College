public class MethodTwo implements MethodInterface {
    

    protected long oCount = 0; //operations counter

    public boolean method(String testString) {
        for(int i = 0; i < testString.length() / 2; i++) { // 2n / 2
            if(testString.charAt(i) != testString.charAt(testString.length() - 1 - i)) { // 4n / 2
                oCount += 4;
                return false; //if each individual elements aren't the same - return a false 
            }
            // 2n / 2 - only runs half the array 
            oCount += 2; //(n / 2) = O(n)
        }
        oCount += 2; 
        return true; //else we have a palindrome
    }

    public long getOperationsCount(){
        return this.oCount;
    }
    public void setOperationsCount(long oCount) {
        this.oCount = oCount;
    }
}
