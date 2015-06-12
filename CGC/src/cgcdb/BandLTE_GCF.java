package cgcdb;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BandLTE_GCF implements Comparable<BandLTE_GCF>{
	private String c002_LTE;
    private int GCFLTEc005;
    
    private String[] GCFTestBandAll;
    private String[] GCFTestBandSingle;
    private String[] GCFTestBandInterRatSingle;
    private String[] GCFTestBandInterRatAll;
    
    private int[] SupportFreq = {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    private String[] SupportFreq1 = {"FDD 01", "FDD 02", "FDD 03", "FDD 04", "FDD 05", "FDD 06",
    		"FDD 07", "FDD 08", "FDD 09", "FDD 10", "FDD 11", "FDD 12",
    		"FDD 13", "FDD 14", "FDD 15", "FDD 16", "FDD 17", "FDD 18",
    		"FDD 19", "FDD 20", "FDD 21", "FDD 22", "FDD 23", "FDD 24",
    		"FDD 25", "FDD 26", "FDD 27", "FDD 28", "FDD 29", "FDD 30"};
    
    private String firstPriorityBand = "FDD 01";
    
    private List ListBandLTE;
    
    private BandLTE_GCF tempAll;
    private BandLTE_GCF tempSingle;
    private BandLTE_GCF tempInterRatSingle;
    private BandLTE_GCF tempInterRatAll;
    
    BandLTE_GCF(){
    	
    }
    
    BandLTE_GCF(String c002_LTE, int GCFLTEc005){
        this.c002_LTE = c002_LTE;
        this.GCFLTEc005 = GCFLTEc005;
    }
    
    @Override
    public String toString(){
        return String.format("BandLTE_GCF(%s, %d)", c002_LTE, GCFLTEc005);
    }
    
    @Override
    public int compareTo(BandLTE_GCF other){
        return this.GCFLTEc005 - other.GCFLTEc005;
    }
    
    public void newBand(){
        ListBandLTE = Arrays.asList(
                new BandLTE_GCF("FDD 01", 50),
                new BandLTE_GCF("FDD 02", 5000),
                new BandLTE_GCF("FDD 03", 2),
                new BandLTE_GCF("FDD 04", 30),
                new BandLTE_GCF("FDD 05", 40),
                new BandLTE_GCF("FDD 06", 5000),
                new BandLTE_GCF("FDD 07", 10),
                new BandLTE_GCF("FDD 08", 60),
                new BandLTE_GCF("FDD 09", 5000),
                new BandLTE_GCF("FDD 10", 5000),
                new BandLTE_GCF("FDD 11", 70),
                new BandLTE_GCF("FDD 12", 80),
                new BandLTE_GCF("FDD 13", 90),
                new BandLTE_GCF("FDD 14", 100),
                new BandLTE_GCF("FDD 15", 5000),
                new BandLTE_GCF("FDD 16", 5000),
                new BandLTE_GCF("FDD 17", 5000),
                new BandLTE_GCF("FDD 18", 110),
                new BandLTE_GCF("FDD 19", 120),
                new BandLTE_GCF("FDD 20", 140),
                new BandLTE_GCF("FDD 21", 130),
                new BandLTE_GCF("FDD 22", 5000),
                new BandLTE_GCF("FDD 23", 5000),
                new BandLTE_GCF("FDD 24", 5000),
                new BandLTE_GCF("FDD 25", 150),
                new BandLTE_GCF("FDD 26", 160),
                new BandLTE_GCF("FDD 27", 5000),
                new BandLTE_GCF("FDD 28", 5000),
                new BandLTE_GCF("FDD 29", 5000),
                new BandLTE_GCF("FDD 30", 5000));
        Collections.sort(ListBandLTE);
        System.out.println(ListBandLTE);
    }
    
    public String[] GCFAll(){
        GCFTestBandAll = new String[30];
        for(int i=0; i<30; i++){
            if(SupportFreq[i]==1){
                tempAll = (BandLTE_GCF)ListBandLTE.get(i);
                GCFTestBandAll[i] = tempAll.c002_LTE;
            }           
        }
        for(int j=0; j<30; j++){
            if(GCFTestBandAll[j] != null)
            System.out.printf("%s\n", GCFTestBandAll[j]);
        }
    return GCFTestBandAll;
    }
    
    public String[] GCFSingle(){
        GCFTestBandSingle = new String[30];
        for(int i=0; i<30; i++){
            tempSingle = (BandLTE_GCF)ListBandLTE.get(i);
            if(SupportFreq[i]==1){
                GCFTestBandSingle[i] = tempSingle.c002_LTE;
                break;
            }
            else
            {
                GCFTestBandSingle[i] = null;
            }
        }
        for(int j=0; j<30; j++){
            if(GCFTestBandSingle[j] != null )
            System.out.printf("%s\n", GCFTestBandSingle[j]);
        }
        return GCFTestBandSingle;
    }
    
    public String[] GCFInterRatSingle(){
        GCFTestBandInterRatSingle = new String[30];
        for(int i=0;i<30;i++){
            tempInterRatSingle = (BandLTE_GCF)ListBandLTE.get(i);
            if(SupportFreq[i] == 1){
                GCFTestBandInterRatSingle[i] =firstPriorityBand + ", " + SupportFreq1[i];
            }
            else{
                GCFTestBandInterRatSingle[i] = null;
            }
        }
        for(int j=0; j<30; j++){
            if(GCFTestBandInterRatSingle[j] != null)
            System.out.printf("%s\n", GCFTestBandInterRatSingle[j]);
        }
        return GCFTestBandInterRatSingle;
    }
    
    public String[] GCFInterRatAll(){
        GCFTestBandInterRatAll = new String[900];
        int k = 0;
        for(int i=0;i<30;i++){
            tempInterRatAll = (BandLTE_GCF)ListBandLTE.get(i);
            if(SupportFreq[i] == 1){
                for(int j=0;j<30; j++ ){
                    GCFTestBandInterRatAll[k] = SupportFreq1[j] + ", " + SupportFreq1[i];
                    k = k + 1;
                }           
            }
            else{
                GCFTestBandInterRatAll[i] = "x";
            }
        }
        for(int j=0; j<900; j++){
            if(GCFTestBandInterRatAll[j] != null)
            System.out.printf("%s\n", GCFTestBandInterRatAll[j]);
        }
        return  GCFTestBandInterRatAll;
    }
}
