
/* For Homework #4 you will consider a variety of cache configurations.
 * For each cache you configure & simulate, you will maintain the statistics
 * for that cache with an instance of THIS class.
 */
public class CacheStats {

    long total_accesses;
    long total_hits;
    double hit_rate;
    int num_loads;
    int num_stores;
    int num_wb_bytes;
    int num_misses;
    
    // you will need to modify this file in order to properly 
    // calculate these two sums
    int total_bytes_transferred_wb; // write-back
    int total_bytes_transferred_wt; // write-thru
    
    public CacheStats() {
	total_accesses = 0;
	total_hits = 0;
	hit_rate = 0.0;
	total_bytes_transferred_wb = 0;
	total_bytes_transferred_wt = 0;  
	num_loads = 0;
	num_stores = 0;
	num_wb_bytes = 0;
	num_misses = 0;
    }
    
    public long totalMisses() {
	return total_accesses - total_hits;
    }
    
    public void updateStat(boolean is_a_hit) {
    	if (is_a_hit)
	    total_hits++;
    	total_accesses++;
    }	

    public void incrementLoads(){
	num_loads++;
    }

    public void incrementStores(){
	num_stores++;
    }

    public void incrementWB(int inc){
	num_wb_bytes = num_wb_bytes + inc;
    } 
    
    public void incrementMisses(int inc){
	num_misses = num_misses + inc;
    } 
    
    // could do this in the previous method, but that's a lot of extra divides...
    public void calculateRates(){
    	hit_rate = total_hits/(double)total_accesses;
	total_bytes_transferred_wt = 4*num_stores + num_misses;
	total_bytes_transferred_wb = num_wb_bytes + num_misses;
    }       
    
    public void print() {
    	System.out.format("Cache Hit Rate = %.2f%% (Mis-rate: %.2f%%)\n", 
			  hit_rate*100.0, (1-hit_rate)*100.0);
    	System.out.format("Total number of bytes transferred write-back: %d\n", total_bytes_transferred_wb);
    	System.out.format("Total number of bytes transferred write-thru: %d\n", total_bytes_transferred_wt);
    }
}
