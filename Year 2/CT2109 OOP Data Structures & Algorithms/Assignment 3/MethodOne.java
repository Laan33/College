public class MethodOne implements MethodInterface {
    

    protected long oCount = 0; //operations counter

    
    public boolean method(String testString) {
        String revString = ""; //reverse string initialised 
        oCount++; // 1
        
        //starting at the end of testString we append reverse it's testString backwards
        for(int i = testString.length() - 1; i >= 0; i--) {  //2n 
            revString += testString.charAt(i); // 2n 
            oCount += 4; //2n + 2n -> O(n)
        }
        
        if(testString.equals(revString)) { //if both strings match 
            return true; //return we have a palindrome 
        }
        else { return false; } //else - false

    }

    public long getOperationsCount(){
        return this.oCount;
    }
    public void setOperationsCount(long oCount) {
        this.oCount = oCount;
    }
}
