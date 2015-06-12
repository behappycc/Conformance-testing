package cgcdb;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BandLTE_PTCRB implements Comparable<BandLTE_PTCRB>{
	private String c002_LTE;
    private int PTCRBLTEc006;
        
    private String[] PTCRBTestBandAll;
    private String[] PTCRBTestBandSingle;
    private String[] PTCRBTestBandInterRatSingle;
    private String[] PTCRBTestBandInterRatAll;
    
    private int[] SupportFreq = {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    private String[] SupportFreq1 = {"FDD 01", "FDD 02", "FDD 03", "FDD 04", "FDD 05", "FDD 06",
            "FDD 07", "FDD 08", "FDD 09", "FDD 10", "FDD 11", "FDD 12",
            "FDD 13", "FDD 14", "FDD 15", "FDD 16", "FDD 17", "FDD 18",
            "FDD 19", "FDD 20", "FDD 21", "FDD 22", "FDD 23", "FDD 24",
            "FDD 25", "FDD 26", "FDD 27", "FDD 28", "FDD 29", "FDD 30"};
    
    private String firstPriorityBand = "FDD 04";
    
    private List ListBandLTE;
    
    private BandLTE_PTCRB tempAll;
    private BandLTE_PTCRB tempSingle;
    private BandLTE_PTCRB tempInterRatSingle;
    private BandLTE_PTCRB tempInterRatAll;
    
    BandLTE_PTCRB(){
    	
    }
    
    BandLTE_PTCRB(String c002_LTE, int PTCRBLTEc006){
        this.c002_LTE = c002_LTE;
        this.PTCRBLTEc006 = PTCRBLTEc006;
    }
    
    @Override
    public String toString(){
        return String.format("BandLTE_PTCRB(%s, %d)", c002_LTE, PTCRBLTEc006);
    }
    
    @Override
    public int compareTo(BandLTE_PTCRB other){
        return this.PTCRBLTEc006 - other.PTCRBLTEc006;
    }
    
    public void newBand(){
        ListBandLTE = Arrays.asList(
                new BandLTE_PTCRB("FDD 01", 5000),
                new BandLTE_PTCRB("FDD 02", 60),
                new BandLTE_PTCRB("FDD 03", 5000),
                new BandLTE_PTCRB("FDD 04", 10),
                new BandLTE_PTCRB("FDD 05", 70),
                new BandLTE_PTCRB("FDD 06", 5000),
                new BandLTE_PTCRB("FDD 07", 80),
                new BandLTE_PTCRB("FDD 08", 5000),
                new BandLTE_PTCRB("FDD 09", 5000),
                new BandLTE_PTCRB("FDD 10", 5000),
                new BandLTE_PTCRB("FDD 11", 5000),
                new BandLTE_PTCRB("FDD 12", 90),
                new BandLTE_PTCRB("FDD 13", 100),
                new BandLTE_PTCRB("FDD 14", 30),
                new BandLTE_PTCRB("FDD 15", 5000),
                new BandLTE_PTCRB("FDD 16", 5000),
                new BandLTE_PTCRB("FDD 17", 20),
                new BandLTE_PTCRB("FDD 18", 5000),
                new BandLTE_PTCRB("FDD 19", 5000),
                new BandLTE_PTCRB("FDD 20", 5000),
                new BandLTE_PTCRB("FDD 21", 5000),
                new BandLTE_PTCRB("FDD 22", 5000),
                new BandLTE_PTCRB("FDD 23", 5000),
                new BandLTE_PTCRB("FDD 24", 50),
                new BandLTE_PTCRB("FDD 25", 40),
                new BandLTE_PTCRB("FDD 26", 5000),
                new BandLTE_PTCRB("FDD 27", 5000),
                new BandLTE_PTCRB("FDD 28", 5000),
                new BandLTE_PTCRB("FDD 29", 5000),
                new BandLTE_PTCRB("FDD 30", 5000));
        Collections.sort(ListBandLTE);
        System.out.println(ListBandLTE);
    }
    
    public String[] PTCRBAll(){
        PTCRBTestBandAll = new String[30];
        for(int i=0; i<30; i++){
            if(SupportFreq[i]==1){
                tempAll = (BandLTE_PTCRB)ListBandLTE.get(i);
                PTCRBTestBandAll[i] = tempAll.c002_LTE;
            }           
        }
        for(int j=0; j<30; j++){
            if(PTCRBTestBandAll[j] != null)
            System.out.printf("%s\n", PTCRBTestBandAll[j]);
        }
    return PTCRBTestBandAll;
    }
    
    public String[] PTCRBSingle(){
        PTCRBTestBandSingle = new String[30];
        for(int i=0; i<30; i++){
            tempSingle = (BandLTE_PTCRB)ListBandLTE.get(i);
            if(SupportFreq[i]==1){
                PTCRBTestBandSingle[i] = tempSingle.c002_LTE;
                break;
            }
            else
            {
                PTCRBTestBandSingle[i] = null;
            }
        }
        for(int j=0; j<30; j++){
            if(PTCRBTestBandSingle[j] != null )
            System.out.printf("%s\n", PTCRBTestBandSingle[j]);
        }
        return PTCRBTestBandSingle;
    }
    
    public String[] PTCRBInterRatSingle(){
        PTCRBTestBandInterRatSingle = new String[30];
        for(int i=0;i<30;i++){
            tempInterRatSingle = (BandLTE_PTCRB)ListBandLTE.get(i);
            if(SupportFreq[i] == 1){
                PTCRBTestBandInterRatSingle[i] =firstPriorityBand + ", " + SupportFreq1[i];
            }
            else{
                PTCRBTestBandInterRatSingle[i] = null;
            }
        }
        for(int j=0; j<30; j++){
            if(PTCRBTestBandInterRatSingle[j] != null)
            System.out.printf("%s\n", PTCRBTestBandInterRatSingle[j]);
        }
        return PTCRBTestBandInterRatSingle;
    }
    
    public String[] PTCRBInterRatAll(){
        PTCRBTestBandInterRatAll = new String[900];
        int k = 0;
        for(int i=0;i<30;i++){
            tempInterRatAll = (BandLTE_PTCRB)ListBandLTE.get(i);
            if(SupportFreq[i] == 1){
                for(int j=0;j<30; j++ ){
                    PTCRBTestBandInterRatAll[k] = SupportFreq1[j] + ", " + SupportFreq1[i];
                    k = k + 1;
                }           
            }
            else{
                PTCRBTestBandInterRatAll[i] = "x";
            }
        }
        for(int j=0; j<900; j++){
            if(PTCRBTestBandInterRatAll[j] != null)
            System.out.printf("%s\n", PTCRBTestBandInterRatAll[j]);
        }
        return  PTCRBTestBandInterRatAll;
    }
}
