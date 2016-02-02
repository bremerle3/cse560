/*
 * Your GShare Branch Predictor goes here.
 * 
 * 
 */



public class GSharePredictor extends BranchPredictor {

    public int nEntries = 1;
    public TwoBitSatCtr[] branchHistoryTable = null;
    public NBitHistoryRegister historyRegister;

    public int current_index=1;

    
    public GSharePredictor(int tableSizeInBits, int historyLengthInBits) {
    /* 
     * nBits tells you how many entries there are in the table.
     * 2^n bits = nEntries
     */
	nEntries = (int)Math.pow(2.0, tableSizeInBits);
	branchHistoryTable = new TwoBitSatCtr[nEntries];

	for (int i = 0; i < nEntries; i++) {
	    branchHistoryTable[i] = new TwoBitSatCtr();
	    branchHistoryTable[i].setChar(0, 'N');
	    branchHistoryTable[i].setChar(1, 'n');
	    branchHistoryTable[i].setChar(2, 't');
	    branchHistoryTable[i].setChar(3, 'T');
	}

	historyRegister = new NBitHistoryRegister(historyLengthInBits);

    }

    /* Given a PC, return true if this branch predictor predicts that the branch is taken.
     * Return false if this branch predictor predicts that the branch is not taken.
     */
    boolean predictIfTaken(long PC) {
	//	long addr = PC % ((long)nEntries^(long)historyRegister.register);
	long addr = (PC^historyRegister.register) % (long)nEntries;
	//long addr = PC % (long)nEntries;
	//int addr = ((int)PC % nEntries);
	    //^historyRegister.register;
	current_index = (int)addr;
	boolean result = branchHistoryTable[current_index].isTrue();
	return result;
    }

    /* Now you are given the PC and told whether the branch was taken or not.
     * Use this information to update your predictor.
     */
    void update(long PC, boolean wasTaken) {
	//	long addr = PC % ((long)nEntries^(long)historyRegister.register);
	long addr = (PC^historyRegister.register) % (long)nEntries;
	//long addr = PC % (long)nEntries;
	if (wasTaken){
	    branchHistoryTable[(int)addr].inc();}
	else{
	    branchHistoryTable[(int)addr].dec();}
	historyRegister.update(wasTaken);
    }

    void printHeader() {
    	//System.out.format("TableState\t\tHist\tPC\tOutcome\tPred\tResult\tnIncorrect\n");
	System.out.format("PC\tTableState\t\tHist\tPred\tOutcome\t  Result\tnIncorrect\n");
    }

    /* 
     * Technically, you don't have to implement this. It's here in case you want to
     * create the debugging outputs.
     */
    void printState() {
	
	System.out.format("\t");
	for (int i = 0; i < nEntries; i++) {
	    System.out.format("%c", branchHistoryTable[i].getChar());
	}
	System.out.format("\t");
	historyRegister.print();

	System.out.format("\t");
	System.out.format("%d", current_index);
	System.out.format("\t");
	System.out.format("%d", branchHistoryTable.length);
    }


}
