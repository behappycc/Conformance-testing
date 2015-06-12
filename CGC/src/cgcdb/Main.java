package cgcdb;

public class Main {
	private Band2G_GCF band2g_gcf;
	private Band2G_PTCRB band2g_ptcrb;
	private Band3G_GCF band3g_gcf;
	private Band3G_PTCRB band3g_ptcrb;
	
	public Main(){
		band2g_gcf = new Band2G_GCF();
		band2g_ptcrb = new Band2G_PTCRB();
		band3g_gcf = new Band3G_GCF();
		band3g_ptcrb = new Band3G_PTCRB();
	}
	
	public void test(){
		band2g_gcf.newBand();
		band2g_ptcrb.newBand();
		band3g_gcf.newBand();
		band3g_ptcrb.newBand();
		//band2g_gcf.GCFAll();
		//band2g_gcf.GCFSingle();
		//band2g_gcf.GCFInterRatSingle();
		//band2g_gcf.GCFInterRatAll();
		
		//band2g_ptcrb.PTCRBAll();
		System.out.printf("GCFAll\n");
		band3g_gcf.GCFAll();
		System.out.printf("GCFSingle\n");
		band3g_gcf.GCFSingle();
		System.out.printf("GCFInterRatSingle\n");
		band3g_gcf.GCFInterRatSingle();
		System.out.printf("GCFInterRatAll\n");
		band3g_gcf.GCFInterRatAll();
		
		//band3g_ptcrb.PTCRBAll();
		//band3g_ptcrb.PTCRBInterRatSingle();
		
	}
	/*
	public static void main(String[] args){
		Main main = new Main();
		main.test();		
	}
	*/	
}