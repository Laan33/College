public class MethodThree implements MethodInterface {
    

    protected long oCount = 0; //operations counter

    @Override
    public boolean method(String testString) {
        ArrayStack arrayStack = new ArrayStack(); // 1
        ArrayQueue arrayQueue = new ArrayQueue(); // 1
        oCount += 2;
        
        //arrayStack / queue yuck
        for(int i = 0; i < testString.length(); i++) {  //2n
            //Taking it as the push and enqueue is one operation
            arrayStack.push(testString.charAt(i)); // 3n - 3 operations in ArrayStack.push
            arrayQueue.enqueue(testString.charAt(i)); // 3n - 3 operations in ArrayQueue.enqueue
            oCount += 9;
        }

        while(!arrayStack.isEmpty() && !arrayQueue.isEmpty()){ // 2n - Singular operation in both methods
            //3n + 7 - Pop has 5 operations and dequeue has 3n + 3 operations (as it shifts the indexs)
            if(arrayStack.pop() != arrayQueue.dequeue()){ 
                for(int i = 0; i < arrayQueue.rear; i++) {
                    oCount += 3; //the 3n in arrayQueue
                }
                oCount += 7; //the remaining + 7 for pop and enqueue
                return false;
            }
            oCount += 3; //O(n) - 8n + 2
        }
        oCount += 2;

        return true;
    }
    
    public long getOperationsCount(){
        return this.oCount;
    }
    public void setOperationsCount(long oCount) {
        this.oCount = oCount;
    }
}
