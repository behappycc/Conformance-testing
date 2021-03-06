/*
 * implenent GCF2G
 * single, all, interratsingle, interratall
 */
package cgcdb;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Band2G_GCF implements Comparable<Band2G_GCF>{
	private int c002_2G; 
	private int GCF2Gc005;
	//private int PTCRB2Gc006;
	
	private int[] GCFTestBandAll;
	private int[] GCFTestBandSingle;
	private String[] GCFTestBandInterRatSingle;
	private String[] GCFTestBandInterRatAll;
	
	private int[] SupportFreq = {0, 1, 1, 1};
	private int[] SupportFreq1 = {850, 900, 1800, 1900};
	private int firstPriorityBand = 900;
	
	private List ListBand2G;
	
	private Band2G_GCF tempAll;
	private Band2G_GCF tempSingle;
	private Band2G_GCF tempInterRatSingle;
	private Band2G_GCF tempInterRatAll;
	
	Band2G_GCF(){
		
	}
	
	Band2G_GCF(int c002_2G, int GCF2Gc005){
		this.c002_2G = c002_2G;
		this.GCF2Gc005 = GCF2Gc005;
		//this.PTCRB2Gc006 = PTCRB2Gc006;
	}
	
	@Override
	public String toString(){
		return String.format("Band2G_GCF(%d, %d)", c002_2G, GCF2Gc005);
	}
	
	@Override
	public int compareTo(Band2G_GCF other){
		return this.GCF2Gc005 - other.GCF2Gc005;
	}

	public void newBand(){
		ListBand2G = Arrays.asList(
				new Band2G_GCF(1800, 20),
				new Band2G_GCF(1900, 30),
				new Band2G_GCF(850, 40),
				new Band2G_GCF(900, 10));
		Collections.sort(ListBand2G);
		System.out.println(ListBand2G);
	}
	
	public int[] GCFAll(){		
		GCFTestBandAll = new int[4];
		for(int i=0; i<4; i++){
			if(SupportFreq[i]==1){
				tempAll = (Band2G_GCF)ListBand2G.get(i);
				GCFTestBandAll[i] = tempAll.c002_2G;
			}
		}
		for(int j=0; j<4; j++){
			System.out.printf("%d\n", GCFTestBandAll[j]);
		}
		return GCFTestBandAll;
	}
	
	public int[] GCFSingle(){
		GCFTestBandSingle = new int[4];
		for(int i=0; i<4; i++){
			tempSingle = (Band2G_GCF)ListBand2G.get(i);
			if(SupportFreq[i]==1){
				GCFTestBandSingle[i] = tempSingle.c002_2G;
				break;
			}
			else
			{
				GCFTestBandSingle[i] = 0;
			}
		}
		for(int j=0; j<4; j++){
			System.out.printf("%d\n", GCFTestBandSingle[j]);
		}
		return GCFTestBandSingle;
	}
	
	public String[] GCFInterRatSingle(){
		GCFTestBandInterRatSingle = new String[4];
		for(int i=0;i<4;i++){
			tempInterRatSingle = (Band2G_GCF)ListBand2G.get(i);
			if(SupportFreq[i] == 1){
				//GCFTestBandInterRatSingle[i] = tempSingle.c002_2G;
				GCFTestBandInterRatSingle[i] = String.valueOf(firstPriorityBand) + ", " + String.valueOf(SupportFreq1[i]);
			}
			else{
				GCFTestBandInterRatSingle[i] = "x";
			}
		}
		for(int j=0; j<4; j++){
			System.out.printf("%s\n", GCFTestBandInterRatSingle[j]);
		}
		return GCFTestBandInterRatSingle;
	}
	
	public String[] GCFInterRatAll(){
		GCFTestBandInterRatAll = new String[16];
		int k = 0;
		for(int i=0;i<4;i++){
			tempInterRatAll = (Band2G_GCF)ListBand2G.get(i);
			if(SupportFreq[i] == 1){
				for(int j=0;j<4; j++ ){
					GCFTestBandInterRatAll[k] = String.valueOf(SupportFreq1[j]) + ", " +String.valueOf(SupportFreq1[i]);
					k = k + 1;
				}			
			}
			else{
				GCFTestBandInterRatAll[i] = "x";
			}
		}
		for(int j=0; j<16; j++){
			System.out.printf("%s\n", GCFTestBandInterRatAll[j]);
		}
		return  GCFTestBandInterRatAll;
	}

}