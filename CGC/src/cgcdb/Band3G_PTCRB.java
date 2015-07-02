/*
 * implenent PTCRB2G
 * single, all, interratsingle, interratall
 */
package cgcdb;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Band3G_PTCRB implements Comparable<Band3G_PTCRB>{
	private String c002_3G;
	private int PTCRB3Gc006;
	
	private String[] GCFTestBandAll;
    private String[] GCFTestBandSingle;
    private String[] GCFTestBandInterRatSingle;
    private String[] GCFTestBandInterRatAll;
    
    private int[] SupportFreq = {0, 1, 1, 1, 1, 1};
    private String[] SupportFreq1 = {"FDD I", "FDD II", "FDD III", "FDD IV", "FDD V", "FDD VIII"};
    
    private String firstPriorityBand = "FDD II";
    
    private List ListBand3G;
    
    private Band3G_PTCRB tempAll;
    private Band3G_PTCRB tempSingle;
    private Band3G_PTCRB tempInterRatSingle;
    private Band3G_PTCRB tempInterRatAll;
    
   Band3G_PTCRB(){
	   
   }
   
   Band3G_PTCRB(String c002_3G, int PTCRB3Gc006){
	   this.c002_3G = c002_3G;
	   this.PTCRB3Gc006 = PTCRB3Gc006;
   }
   
   @Override
   public String toString(){
       return String.format("Band3G_PTCRB(%s, %d)", c002_3G, PTCRB3Gc006);
   }
   
   @Override
   public int compareTo(Band3G_PTCRB other){
       return this.PTCRB3Gc006 - other.PTCRB3Gc006;
   }
   
   public void newBand(){
       ListBand3G = Arrays.asList(
               new Band3G_PTCRB("FDD I", 40),
               new Band3G_PTCRB("FDD II", 10),
               new Band3G_PTCRB("FDD III", 60),
               new Band3G_PTCRB("FDD IV", 30),
               new Band3G_PTCRB("FDD V", 20),
               new Band3G_PTCRB("FDD VIII", 50));
       Collections.sort(ListBand3G);
       System.out.println(ListBand3G);
   }
   
   public String[] PTCRBAll(){
	   GCFTestBandAll = new String[6];
	   for(int i=0; i<6; i++){
		   if(SupportFreq[i]==1){
			   tempAll = (Band3G_PTCRB)ListBand3G.get(i);
			   GCFTestBandAll[i] = tempAll.c002_3G;
		   }		   
	   }
	   for(int j=0; j<6; j++){
		   if(GCFTestBandAll[j] != null)
		   System.out.printf("%s\n", GCFTestBandAll[j]);
	   }
   return GCFTestBandAll;
   }
   
   public String[] PTCRBSingle(){
       GCFTestBandSingle = new String[6];
       for(int i=0; i<6; i++){
           tempSingle = (Band3G_PTCRB)ListBand3G.get(i);
           if(SupportFreq[i]==1){
               GCFTestBandSingle[i] = tempSingle.c002_3G;
               break;
           }
           else
           {
               GCFTestBandSingle[i] = null;
           }
       }
       for(int j=0; j<6; j++){
    	   if(GCFTestBandSingle[j] != null )
           System.out.printf("%s\n", GCFTestBandSingle[j]);
       }
       return GCFTestBandSingle;
   }
   
   public String[] PTCRBInterRatSingle(){
       GCFTestBandInterRatSingle = new String[6];
       for(int i=0;i<6;i++){
           tempInterRatSingle = (Band3G_PTCRB)ListBand3G.get(i);
           if(SupportFreq[i] == 1){
               GCFTestBandInterRatSingle[i] =firstPriorityBand + ", " + SupportFreq1[i];
           }
           else{
               GCFTestBandInterRatSingle[i] = null;
           }
       }
       for(int j=0; j<6; j++){
    	   if(GCFTestBandInterRatSingle[j] != null)
           System.out.printf("%s\n", GCFTestBandInterRatSingle[j]);
       }
       return GCFTestBandInterRatSingle;
   }
   
   public String[] PTCRBInterRatAll(){
       GCFTestBandInterRatAll = new String[36];
       int k = 0;
       for(int i=0;i<6;i++){
           tempInterRatAll = (Band3G_PTCRB)ListBand3G.get(i);
           if(SupportFreq[i] == 1){
               for(int j=0;j<6; j++ ){
                   GCFTestBandInterRatAll[k] = SupportFreq1[j] + ", " + SupportFreq1[i];
                   k = k + 1;
               }           
           }
           else{
               GCFTestBandInterRatAll[i] = "x";
           }
       }
       for(int j=0; j<36; j++){
    	   if(GCFTestBandInterRatAll[j] != null)
           System.out.printf("%s\n", GCFTestBandInterRatAll[j]);
       }
       return  GCFTestBandInterRatAll;
   }
}