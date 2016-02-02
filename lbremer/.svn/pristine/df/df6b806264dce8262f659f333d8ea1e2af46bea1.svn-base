import java.lang.String;
import java.util.zip.GZIPInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Simulator
{    

    public String testTrace = "sjeng-10M"; // default

    // cache stuff
    public CacheStats cacheStats = null;
    public Cache myCache = null;

    // print options, by default off
    public boolean verbose_f = false;
    public boolean debug_f = false;

    // optionally limit the simulation to the first N insns
    // do not break this functionality when you complete the code!
    public boolean insnLimit_f = false;
    public int uopLimit = 0;
	
    // don't touch! you'll be sorry....
    public BufferedReader traceReader = null;
	
    // Program Stats
    public long totalUops = 0;
    
    /*
     * Simple constructor for the simulator.
     */
    public Simulator() {
	cacheStats = new CacheStats();
    }

    /*
     * set up the trace
     */
    public void initTrace() throws IOException {
	
	String tracePath = "../traces/"+testTrace+".trace.gz";
	// ugly, but JAVA IO is not the point
	traceReader = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream(tracePath))));
    }

    /*
     * Goes through the trace line by line (i.e., instruction by instruction) and 
     * simulates the program being executed on a processor.
     * 
     * This method is currently stripped down. It will just read in each instruction 
     * one at a time, and does not access the cache. 
     */
    public void processTrace() throws IOException  {
        String line = "";    
        Uop currUop = null;
        
	initTrace();

	if (debug_f)
	    myCache.printHeader();
        
        while (true) {
	    line = traceReader.readLine();
	    if (line == null) {
		break;
	    }
	    if (insnLimit_f && totalUops == uopLimit) {
		System.out.format("Reached insn limit of %d. Ending Simulation...\n", uopLimit);
		break;
	    }
	    currUop = new Uop(line);
	    totalUops++;
	    
	    // BEGIN WHERE YOU SHOULD MAKE CHANGES TO THIS FILE
	    
	    // Print this before you access the cache
	    if(currUop.isMem()){
		if (debug_f)
		    myCache.printCache();
		boolean hit_f = true;
		// access the cache
		boolean isCurrLoad = currUop.isLoad();
		boolean wasHit = myCache.access(currUop.addressForMemoryOp, isCurrLoad);
		// update the cache stats
		hit_f = wasHit;
		cacheStats.updateStat(hit_f);  //Hit/Miss statistics
		//Store/Load statistics
		if (currUop.isLoad()){
		    cacheStats.incrementLoads();
		}
		else if (currUop.isStore()){
		    cacheStats.incrementStores();
		}
		if(myCache.dirtyEvic_f){
		    cacheStats.incrementWB(myCache.blockSize);
		}
		if(!wasHit){
		    cacheStats.incrementMisses(myCache.blockSize);
		}



	    
	    	    
	    // Print this after you access the cache
	    if (debug_f) 
		System.out.format("%s\t%s\t%s\t%s\n", 
				  Long.toHexString(myCache.getBlockAddress(currUop.addressForMemoryOp)),
				  currUop.isLoad() ? "L" : "S", 
				  hit_f ? "hit" : "miss", 
				  hit_f ? "--" : myCache.dirtyEvic_f ? "dirty" : "clean");
	    
	    }
	    // END WHERE YOU SHOULD MAKE CHANGES TO THIS FILE
	    
            // prints the insn
	    if (verbose_f)
		System.out.format("%s\n", line);
	    
        }

    	cacheStats.calculateRates();
    }
    
    
    /*
     * Prints out basic stats + the hit/miss rates of the cache.
     */
    public void printTraceStats() {
	
    	System.out.format("Processed %d trace lines.\n", totalUops);
    	System.out.format("Num Uops (micro-ops): \t\t\t%d\n", totalUops);
	
        cacheStats.print(); 
	
    }

    public void printHeader() {
	if (!debug_f) {
	    System.out.format("HW 4 Printout for i-forgot-to-replace-this-string-with-my-wustlkey\n");
	    System.out.format("-----------------------------------------------------\n");
	}
    	System.out.format("Trace name  \t\t\t\t%s\n", testTrace);
	myCache.printConfig();
	System.out.format("Instruction Limit\t\t\t%s\n", insnLimit_f ? Integer.toString(uopLimit) : "none");	
    }
    
}
