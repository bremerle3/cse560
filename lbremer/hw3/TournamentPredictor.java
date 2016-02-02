/*
 * Your Tournament Branch Predictor goes here.
 * 
 * 
 */

public class TournamentPredictor extends BranchPredictor {

    public int nEntriesChooser = 0;
    public int nEntriesBM = 0;
    public int nEntriesGS = 0;
    public TwoBitSatCtr[] bmHistoryTable = null;
    public TwoBitSatCtr[] gsHistoryTable = null;
    public TwoBitSatCtr[] chooserTable = null;
    public NBitHistoryRegister gs_historyRegister;
    public boolean resultGS;
    public boolean resultBM;
    public boolean result;


    public TournamentPredictor(int bm_tableSizeInBits, 
			       int gs_tableSizeInBits, int gs_historyLengthInBits, 
				  int chooserTableSizeInBits) {

	//Initialize BM table
	nEntriesBM = (int)Math.pow(2.0, bm_tableSizeInBits);
	bmHistoryTable = new TwoBitSatCtr[nEntriesBM];
	
	for (int i = 0; i < nEntriesBM; i++) {
	    bmHistoryTable[i] = new TwoBitSatCtr();
	    bmHistoryTable[i].setChar(0, 'N');
	    bmHistoryTable[i].setChar(1, 'n');
	    bmHistoryTable[i].setChar(2, 't');
	    bmHistoryTable[i].setChar(3, 'T');
	}

	//Initialize GS table
	nEntriesGS = (int)Math.pow(2.0, gs_tableSizeInBits);
	gsHistoryTable = new TwoBitSatCtr[nEntriesGS];
	
	for (int i = 0; i < nEntriesGS; i++) {
	    gsHistoryTable[i] = new TwoBitSatCtr();
	    gsHistoryTable[i].setChar(0, 'N');
	    gsHistoryTable[i].setChar(1, 'n');
	    gsHistoryTable[i].setChar(2, 't');
	    gsHistoryTable[i].setChar(3, 'T');
	}

	//Initialize history register
	gs_historyRegister = new NBitHistoryRegister(gs_historyLengthInBits);

	//Initialize choose table
	nEntriesChooser = (int)Math.pow(2.0, chooserTableSizeInBits);
	chooserTable = new TwoBitSatCtr[nEntriesChooser];
	
	for (int i = 0; i < nEntriesChooser; i++) {
	    chooserTable[i] = new TwoBitSatCtr();
	    chooserTable[i].setChar(0, 'B');
	    chooserTable[i].setChar(1, 'b');
	    chooserTable[i].setChar(2, 'g');
	    chooserTable[i].setChar(3, 'G');
	}

    }


    
    
    /* Given a PC, return true if this branch predictor predicts that the branch is taken.
     * Return false if this branch predictor predicts that the branch is not taken.
     */
    boolean predictIfTaken(long PC) {


	//Get GS index and prediction
	long addrGS = (PC^gs_historyRegister.register) % (long)nEntriesGS;
	resultGS = gsHistoryTable[(int)addrGS].isTrue();

	//resultGS = false;

	//Get BM index and prediction
	int addrBM = (int)PC % nEntriesBM;
	resultBM = bmHistoryTable[addrBM].isTrue();

	//Decide which one to use
	//	boolean result;
	int addrChooser = (int)PC % nEntriesChooser;
	if(chooserTable[addrChooser].isTrue()){
	    result = resultGS;
	}
	else 
	    result = resultBM;

 	return result;
    }

    /* Now you are given the PC and told whether the branch was taken or not.
     * Use this information to update your predictor.
     */
    void update(long PC, boolean wasTaken) {
	//Update GS
	long addrGS = (PC^gs_historyRegister.register) % (long)nEntriesGS;
	if (wasTaken){
	    gsHistoryTable[(int)addrGS].inc();}
	else{
	    gsHistoryTable[(int)addrGS].dec();}

	gs_historyRegister.update(wasTaken);

	//Update BM
	int addrBM = (int)PC % nEntriesBM;
	if (wasTaken){
	    bmHistoryTable[addrBM].inc();}
	else{
	    bmHistoryTable[addrBM].dec();}

	int addrChooser = (int)PC % nEntriesChooser;
	if (resultGS != resultBM){
	    if (resultGS == wasTaken){  //Increment for GS
		chooserTable[addrChooser].inc();}
	    else if (resultBM == wasTaken){
		chooserTable[addrChooser].dec();} //Decrement for BM
	}
    }

    void printHeader() {
	//	System.out.format("PC\tChooseTable\tBimodTable\tGshareTable\t\tHist\tPred\tOutcome\tResult\t\tnIncorrect\n");
	System.out.format("PC\tBimodTable\tGShareTable\t\tHist\tChooseTable\tPred\tOutcome\tResult\t\tnIncorrect\n");
	//	System.out.format("PC\t
    }

    /* 
     * Technically, you don't have to implement this. It's here in case you want to
     * create the debugging outputs.
     */
    void printState() {


	//BM
	System.out.format("\t");
	for (int i = 0; i < nEntriesBM; i++) {
	    System.out.format("%c", bmHistoryTable[i].getChar());
	}

	//GS
	System.out.format("\t");
	for (int i = 0; i < nEntriesGS; i++) {
	    System.out.format("%c", gsHistoryTable[i].getChar());
	}
	System.out.format("\t");
	gs_historyRegister.print();

//GS
	System.out.format("\t");
	for (int i = 0; i < nEntriesChooser; i++) {
	    System.out.format("%c", chooserTable[i].getChar());
	}
    
	System.out.format("\t");
	System.out.format("%b", resultGS);

	System.out.format("\t");
	System.out.format("%b", resultBM);



    }


}
