package cgcdb;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Band3G_GCF implements Comparable<Band3G_GCF>{
	private String c002_3G;
	private int GCF3Gc005;
	
	private String[] GCFTestBandAll;
    private String[] GCFTestBandSingle;
    private String[] GCFTestBandInterRatSingle;
    private String[] GCFTestBandInterRatAll;
    
    private int[] SupportFreq = {0, 1, 1, 1, 1, 1};
    private String[] SupportFreq1 = {"FDD I", "FDD II", "FDD III", "FDD IV", "FDD V", "FDD VIII"};
    
    private String firstPriorityBand = "FDD I";
    
    private List ListBand3G;
    
    private Band3G_GCF tempAll;
    private Band3G_GCF tempSingle;
    private Band3G_GCF tempInterRatSingle;
    private Band3G_GCF tempInterRatAll;
    
   Band3G_GCF(){
	   
   }
   
   Band3G_GCF(String c002_3G, int GCF3Gc005){
	   this.c002_3G = c002_3G;
	   this.GCF3Gc005 = GCF3Gc005;
   }
   
   @Override
   public String toString(){
       return String.format("Band3G_GCF(%s, %d)", c002_3G, GCF3Gc005);
   }
   
   @Override
   public int compareTo(Band3G_GCF other){
       return this.GCF3Gc005 - other.GCF3Gc005;
   }
   
   public void newBand(){
       ListBand3G = Arrays.asList(
               new Band3G_GCF("FDD I", 10),
               new Band3G_GCF("FDD II", 40),
               new Band3G_GCF("FDD III", 30),
               new Band3G_GCF("FDD IV", 60),
               new Band3G_GCF("FDD V", 50),
               new Band3G_GCF("FDD VIII", 20));
       Collections.sort(ListBand3G);
       System.out.println(ListBand3G);
   }
   
   public String[] GCFAll(){
	   GCFTestBandAll = new String[6];
	   for(int i=0; i<6; i++){
		   if(SupportFreq[i]==1){
			   tempAll = (Band3G_GCF)ListBand3G.get(i);
			   GCFTestBandAll[i] = tempAll.c002_3G;
		   }		   
	   }
	   for(int j=0; j<6; j++){
		   if(GCFTestBandAll[j] != null)
		   System.out.printf("%s\n", GCFTestBandAll[j]);
	   }
   return GCFTestBandAll;
   }
   
   public String[] GCFSingle(){
       GCFTestBandSingle = new String[6];
       for(int i=0; i<6; i++){
           tempSingle = (Band3G_GCF)ListBand3G.get(i);
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
   
   public String[] GCFInterRatSingle(){
       GCFTestBandInterRatSingle = new String[6];
       for(int i=0;i<6;i++){
           tempInterRatSingle = (Band3G_GCF)ListBand3G.get(i);
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
   
   public String[] GCFInterRatAll(){
       GCFTestBandInterRatAll = new String[36];
       int k = 0;
       for(int i=0;i<6;i++){
           tempInterRatAll = (Band3G_GCF)ListBand3G.get(i);
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

/*
protected int[] GCF3Gc005 = {10, 40, 30, 60, 50, 20};
protected int[] PTCRB3Gc006 = {40, 10, 60, 30, 20, 50};
*/