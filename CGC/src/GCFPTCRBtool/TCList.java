package GCFPTCRBtool;

import java.sql.*;

import cgcdb.*;

public class TCList {
	private CGCDB db;
	
	public TCList(Connection con, String database_name){
		db = new CGCDB(con, database_name);
	}
	
	public void Cluster2G3G4G(){
		db.loadTCList_LTE();
		db.get2GTestcase();
		db.get3GTestcase();
		db.get4GTestcase();
	}
	
	public void ClusterGCF2G(){
		db.loadTCList_LTE();
		db.loadCM_2G();
		db.loadCD_2G();
		db.loadGCF_2GG();
		db.getGCFTestcase_2G();
		//db.printGCF();
	}
	
	public void ClusterGCF3G(){
		db.loadTCList_LTE();
		db.loadCM_3G();
		db.loadCD_3G();
		db.loadGCF_3G();
		db.getGCFTestcase_3G();
	}
	
	public void ClusterGCFLTE(){
		db.loadTCList_LTE();
		db.loadCM_LTE();
		db.loadCD_LTE();
		db.loadGCF_LTE();
		db.getGCFTestcase_LTE();
	}
	
	public void ClusterPTCRB2G(){
		
	}
	
	public void ClusterPTCRB3G(){
		
	}
	
	public void ClusterPTCRBLTE(){
		db.loadTCList_LTE();
		db.loadCM_LTE();
		db.loadCD_LTE();
		db.loadTCD_LTE();
		db.loadPTCRB_LTE();
		db.getPTCRBTestcase_LTE();
	}
	
	public void loadTCList_LTE(){
		db.loadTCList_LTE();
		db.print2GListProtocol("LYW200", "2G");
//		db.get2GTestcase();
//		db.get3GTestcase();
//		db.get4GTestcase();
//		db.loadCM_2G();
//		db.getCM_2G();
//		db.printCM();
	}
	
	public void testSingle(){
		Main main = new Main();
		main.test();	
	}
	
	public static void main(String[] args){
		String conUrl = "jdbc:sqlserver://140.112.42.144\\SQLEXPRESS:1433;user=sa;password=bl618;";
		Connection con = null;
		
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(conUrl);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		TCList tcList = new TCList(con, "NTUTC");
		tcList.loadTCList_LTE();
		//tcList.Cluster2G3G4G();
		//tcList.ClusterGCF2G();
		//tcList.ClusterGCF3G();
		//tcList.ClusterGCFLTE();
		//tcList.ClusterPTCRBLTE();
		//tcList.testSingle();
	}
}
