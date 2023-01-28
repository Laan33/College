public class timings {
    
    public long timeCalculationStart() {
        long startTime = System.currentTimeMillis(); //setting the starting time
        return startTime; //returning the time program started at
    }
    
    public void timeCalculationEnd(long startTime) {
        long timeElapsed = System.currentTimeMillis() - startTime;//system time minus the previous start time
        long totalElapsedSeconds = timeElapsed / 1000; //milliseconds into seconds
        
        //letting user know total time taken (in seconds, could easily implement milli if required using the modulous of totalElapsedSeconds)
        System.out.println("Time taken to complete: " + totalElapsedSeconds + " seconds"); 
    }


}
