/*
 * implenent PTCRB
 * single, all, interratsingle, interratall
 */
package cgcdb;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Band2G_PTCRB implements Comparable<Band2G_PTCRB>{
	private int c002_2G; 
	//private int PTCRB2Gc006;
	private int PTCRB2Gc006;
	
	private int[] PTCRBTestBandAll;
	private int[] PTCRBTestBandSingle;
	private String[] PTCRBTestBandInterRatSingle;
	private String[] PTCRBTestBandInterRatAll;
	
	private int[] SupportFreq = {0, 1, 1, 1};
	private int[] SupportFreq1 = {850, 900, 1800, 1900};
	private int firstPriorityBand = 900;
	
	private List ListBand2G;
	
	private Band2G_PTCRB tempAll;
	private Band2G_PTCRB tempSingle;
	private Band2G_PTCRB tempInterRatSingle;
	private Band2G_PTCRB tempInterRatAll;
	
	Band2G_PTCRB(){
		
	}
	
	Band2G_PTCRB(int c002_2G, int PTCRB2Gc006){
		this.c002_2G = c002_2G;
		this.PTCRB2Gc006 = PTCRB2Gc006;
		//this.PTCRB2Gc006 = PTCRB2Gc006;
	}
	
	@Override
	public String toString(){
		return String.format("Band2G_PTCRB(%d, %d)", c002_2G, PTCRB2Gc006);
	}
	
	@Override
	public int compareTo(Band2G_PTCRB other){
		return this.PTCRB2Gc006 - other.PTCRB2Gc006;
	}
	
	public void newBand(){
		ListBand2G = Arrays.asList(
				new Band2G_PTCRB(1800, 40),
				new Band2G_PTCRB(1900, 10),
				new Band2G_PTCRB(850, 20),
				new Band2G_PTCRB(900, 30));
		Collections.sort(ListBand2G);
		System.out.println(ListBand2G);
	}
	
	public int[] PTCRBAll(){		
		PTCRBTestBandAll = new int[4];
		for(int i=0; i<4; i++){
			if(SupportFreq[i]==1){
				tempAll = (Band2G_PTCRB)ListBand2G.get(i);
				PTCRBTestBandAll[i] = tempAll.c002_2G;
			}
		}
		for(int j=0; j<4; j++){
			System.out.printf("%d\n", PTCRBTestBandAll[j]);
		}
		return PTCRBTestBandAll;
	}
	
	public int[] PTCRBSingle(){
		PTCRBTestBandSingle = new int[4];
		for(int i=0; i<4; i++){
			tempSingle = (Band2G_PTCRB)ListBand2G.get(i);
			if(SupportFreq[i]==1){
				PTCRBTestBandSingle[i] = tempSingle.c002_2G;
				break;
			}
			else
			{
				PTCRBTestBandSingle[i] = 0;
			}
		}
		for(int j=0; j<4; j++){
			System.out.printf("%d\n", PTCRBTestBandSingle[j]);
		}
		return PTCRBTestBandSingle;
	}
	
	public String[] PTCRBInterRatSingle(){
		PTCRBTestBandInterRatSingle = new String[4];
		for(int i=0;i<4;i++){
			tempInterRatSingle = (Band2G_PTCRB)ListBand2G.get(i);
			if(SupportFreq[i] == 1){
				//PTCRBTestBandInterRatSingle[i] = tempSingle.c002_2G;
				PTCRBTestBandInterRatSingle[i] = String.valueOf(firstPriorityBand) + ", " + String.valueOf(SupportFreq1[i]);
			}
			else{
				PTCRBTestBandInterRatSingle[i] = "x";
			}
		}
		for(int j=0; j<4; j++){
			System.out.printf("%s\n", PTCRBTestBandInterRatSingle[j]);
		}
		return PTCRBTestBandInterRatSingle;
	}
	
	public String[] PTCRBInterRatAll(){
		PTCRBTestBandInterRatAll = new String[16];
		int k = 0;
		for(int i=0;i<4;i++){
			tempInterRatAll = (Band2G_PTCRB)ListBand2G.get(i);
			if(SupportFreq[i] == 1){
				for(int j=0;j<4; j++ ){
					PTCRBTestBandInterRatAll[k] = String.valueOf(SupportFreq1[j]) + ", " +String.valueOf(SupportFreq1[i]);
					k = k + 1;
				}			
			}
			else{
				PTCRBTestBandInterRatAll[i] = "x";
			}
		}
		for(int j=0; j<16; j++){
			System.out.printf("%s\n", PTCRBTestBandInterRatAll[j]);
		}
		return  PTCRBTestBandInterRatAll;
	}

}