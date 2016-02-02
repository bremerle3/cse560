import java.lang.String;
import java.util.zip.GZIPInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * This is one of two files you will modify to complete Homework #1a.
 * The places you need to change, complete, or correct things are clearly labeled.
 * You are free to make changes as needed to THIS FILE AND Uop.java ONLY
 */

public class Simulator
{    
    public String testTrace;
    
    // don't touch! you'll be sorry....
    public BufferedReader traceReader = null;
    
    // Program Stat Variables Go Here -- you'll need more than these two
    public long totalUops = 0;
    public long totalMops = 0;
    
    // THIS is why your homework is in Java and not C. You're welcome.

    // Question 1B
    public FrequencyHistogram UopsPerMopHistogram = new FrequencyHistogram("Micro Ops Per Macrop Op", "mops consisting of", "uops");
    
    // Question 2B
    public FrequencyHistogram BytesPerMopHistogram = new FrequencyHistogram("Bytes Per Macrop Op", "mops of length", "bytes"); 
    
    // Question 3A
    public FrequencyHistogram BitsPerTargetHistogram = new FrequencyHistogram("Bits Per Target", "targets requiring", "bits"); 
    
    // Question 4A
    public FrequencyHistogram InsnTypeFrequencyHistogram = new FrequencyHistogram("Frequency of Each Uop Type", "uops of type", ""); 
    
    
    /*
     * Constructor for the simulator.
     * @param traceName the name of the trace file you wish to simulate. in .gz format
     */
    public Simulator(String traceName) throws IOException {
	
	testTrace = traceName;
	String tracePath = "../traces/"+testTrace;
	// ugly, but JAVA IO is not the point
	traceReader = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream(tracePath))));
    }
    
    
    /*
     * Goes through the trace line by line (i.e., instruction by instruction), examining
     * each instruction in the trace. 1 line = 1 micro-op (micro-op = uop)
     */
    public void processTrace(boolean verbose_f) throws IOException {
        String line;
        
        while (true) {
            line = traceReader.readLine();
            if (line == null) {
                break;
            }
            
            Uop currUop = new Uop(line);
            
            // if you want to see what's going on (per loop iteration), call this fn with verbose_f = true 
            // you'll see each instruction printed to the screen. NOT recommended for long traces!
            if (verbose_f)
            	System.out.format("%s\n", line);
	    
            // each line of the trace is a micro-op, so increment each time
            totalUops++;

            // the first micro-op in the macro-op indicates a new macro-op has begun
            // this is identified by microOpCount == 1
            if (currUop.microOpCount == 1) {
            	totalMops++;		                	
            }
            
            // There are several histograms that you need to populate for this homework assignment.
            // TO DO: call increment at the right time with the right argument.
            // This is the only call you need to get the data you're looking for. 
            // These lines are provided to show you the *syntax* of the calls. 
            // They are not semantically correct -- you should not call each of these lines for 
            // every uop (as it currently does) and you should not only increment the "size 1" bucket each time.
            
            // Question 1B
            UopsPerMopHistogram.increment(1);
           
            // Question 2B
            BytesPerMopHistogram.increment(1);
            
            // Question 3A
            BitsPerTargetHistogram.increment(1);

            // Question 4A This line IS correct and does not need to me moved or modified, 
            // BUT the currUop.type is NOT correctly set. Fix this in your Uop constructor inside Uop.java
            InsnTypeFrequencyHistogram.increment(currUop.type.value);
        }
        
     }
     
    public void printHeader() {
	
	HW1aHelper.printHeader("i-forgot-to-replace-this-string-with-my-wustlkey", testTrace);
    }
    
    public void printTraceStats() {
	
    	System.out.format("Processed %d trace lines.\n", totalUops);
    	System.out.format("Num Uops (micro-ops): %d\n", totalUops);
    	System.out.format("Num Mops (macro-ops): %d\n", totalMops);

    }
    
    public void printHW1aQuestions() {
	
    	HW1aHelper.print1A(0.0);
    	HW1aHelper.print1B(UopsPerMopHistogram);
    	HW1aHelper.print2A(0.0);    	
    	HW1aHelper.print2B(BytesPerMopHistogram); 
    	HW1aHelper.print3A(BitsPerTargetHistogram);  
    	HW1aHelper.print4A(InsnTypeFrequencyHistogram); 
    	HW1aHelper.print6A(0.0);

    }
    
     
}
