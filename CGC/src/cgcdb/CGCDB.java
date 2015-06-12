package cgcdb;

import java.sql.*;
import java.util.*;

public class CGCDB {
	Connection con = null;
	private Statement stmt;	
	static int exceptionx = 0;	
	private String DBNAME;
	
	//2G, 3G, LTE data from rice
	private HashMap<String, TCList_LTE> TC_LTE;
	private ArrayList<TCList_LTE> TCLIST_LTE;
	
	//category main and detail 2G
	private HashMap<String, CATEGORY_MAIN_2G> CM_2G;
	private ArrayList<CATEGORY_MAIN_2G> CMLIST_2G;
	private HashMap<String, CATEGORY_DETAIL_2G> CD_2G;
	private ArrayList<CATEGORY_DETAIL_2G> CDLIST_2G;
	
	//category main and detail 3G
	private HashMap<String, CATEGORY_MAIN_3G> CM_3G;
	private ArrayList<CATEGORY_MAIN_3G> CMLIST_3G;
	private HashMap<String, CATEGORY_DETAIL_3G> CD_3G;
	private ArrayList<CATEGORY_DETAIL_3G> CDLIST_3G;
	
	//category main and detail LTE
	private HashMap<String, CATEGORY_MAIN_LTE> CM_LTE;
	private ArrayList<CATEGORY_MAIN_LTE> CMLIST_LTE;
	private HashMap<String, CATEGORY_DETAIL_LTE> CD_LTE;
	private ArrayList<CATEGORY_DETAIL_LTE> CDLIST_LTE;
	
	//TC_DIFF different between GCG, PTCRB and ETSI
	private HashMap<String, TC_DIFF_2G3GLTE> TCD_2G;
	private ArrayList<TC_DIFF_2G3GLTE> TCDLIST_2G;
	private HashMap<String, TC_DIFF_2G3GLTE> TCD_3G;
	private ArrayList<TC_DIFF_2G3GLTE> TCDLIST_3G;
	private HashMap<String, TC_DIFF_2G3GLTE> TCD_LTE;
	private ArrayList<TC_DIFF_2G3GLTE> TCDLIST_LTE;
	
	//sys_011 band priority 
	private HashMap<String, SYS_011> SYS_011_2G3GLTE;
	private ArrayList<SYS_011> SYS_011LIST_2G3GLTE;
	
	//GCF and PTCRB testcase
	private ArrayList<GCFTestcase_2G> GCFLIST_2G;
	private ArrayList<GCFTestcase_3G> GCFLIST_3G;
	private ArrayList<GCFTestcase_LTE> GCFLIST_LTE;
	private ArrayList<GCFTestcase_2G> PTCRBLIST_2G;
	private ArrayList<GCFTestcase_3G> PTCRBLIST_3G;
	private ArrayList<GCFTestcase_LTE> PTCRBLIST_LTE;
	
	//connect to server	
	public CGCDB(Connection con, String DBNAME){
		this.DBNAME = DBNAME;		
		try{
			this.con = con;
			stmt = con.createStatement();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//load 2G, 3G, LTE data from rice
	public void loadTCList_LTE(){
		TC_LTE = new HashMap<String, TCList_LTE>();
		TCLIST_LTE = new ArrayList<TCList_LTE>();
		String SQL = "SELECT ALL [SEQ],"
				+"[PROJECT_CODE],"
				+"[SPEC],"
				+"[TC_ID],"
				+"[TC_DESC],"
				+"[RELEASE],"				
				+"[FINAL_RESULT]"
				+ "FROM [" + DBNAME + "].[dbo].[TCLIST_LTE]";
		
		try{
			ResultSet rs = stmt.executeQuery(SQL);
			while(rs.next()){
				TCList_LTE tclist_lte = new TCList_LTE(rs.getString("SEQ"),
						rs.getString("PROJECT_CODE"),
						rs.getString("SPEC"),
						rs.getString("TC_ID"),
						rs.getString("TC_DESC"),
						rs.getString("RELEASE"),
						rs.getString("FINAL_RESULT"));
				
				TC_LTE.put(rs.getString("TC_ID"), tclist_lte);
				TCLIST_LTE.add(tclist_lte);
			}
		}catch(SQLException exception){
			exception.printStackTrace();
		}
	}
	
	//load category main 2G
	public void loadCM_2G(){
		CM_2G = new HashMap<String, CATEGORY_MAIN_2G>();
		CMLIST_2G = new ArrayList<CATEGORY_MAIN_2G>();
		String SQL = "SELECT ALL [IDENTIFY_CATEGORY],"
				+"[CATEGORY_TYPE],"
				+"[INTERIMS_VERSION],"
				+"[TABLE_SPEC],"
				+"[TABLE_ID],"
				+"[TABLE_DESC],"
				+"[SPEC_VERSION],"
				+"[TESTBAND]"
				+ "FROM [" + DBNAME + "].[dbo].[CATEGORY_2GMAIN]";			
		try{
			ResultSet rs = stmt.executeQuery(SQL);
			while(rs.next()){
				CATEGORY_MAIN_2G cm_2G = new CATEGORY_MAIN_2G(rs.getString("IDENTIFY_CATEGORY"),
						rs.getString("CATEGORY_TYPE"),
						rs.getString("INTERIMS_VERSION"),
						rs.getString("TABLE_SPEC"),
						rs.getString("TABLE_ID"),
						rs.getString("TABLE_DESC"),
						rs.getString("SPEC_VERSION"),
						rs.getString("TESTBAND"));
				
				CM_2G.put(rs.getString("TABLE_ID"), cm_2G);
				CMLIST_2G.add(cm_2G);
			}
		}catch(SQLException exception){
			exception.printStackTrace();
		}
	}
	
	//load category detail 2G
	public void loadCD_2G(){
		CD_2G = new HashMap<String, CATEGORY_DETAIL_2G>();
		CDLIST_2G = new ArrayList<CATEGORY_DETAIL_2G>();
		String SQL = "SELECT ALL [IDENTIFY_CATEGORY_D],"
				+"[IDENTIFY_CATEGORY],"
				+"[RELEASE],"
				+"[CATEGORY_FDD_TYPE],"
				+"[CATEGORY],"
				+"[VALID_PALTFORM]"
				+ "FROM [" + DBNAME + "].[dbo].[CATEGORY_2GDETAIL]";
		try{
			ResultSet rs = stmt.executeQuery(SQL);
			while(rs.next()){
				CATEGORY_DETAIL_2G cd_2G = new CATEGORY_DETAIL_2G(rs.getString("IDENTIFY_CATEGORY_D"),
						rs.getString("IDENTIFY_CATEGORY"),
						rs.getString("RELEASE"),
						rs.getString("CATEGORY_FDD_TYPE"),
						rs.getString("CATEGORY"),
						rs.getString("VALID_PALTFORM"));

				CD_2G.put(rs.getString("IDENTIFY_CATEGORY"), cd_2G);
				CDLIST_2G.add(cd_2G);
			}
		}catch(SQLException exception){
			exception.printStackTrace();
		}					
	}
	
	public void loadCM_3G(){
		CM_3G = new HashMap<String, CATEGORY_MAIN_3G>();
		CMLIST_3G = new ArrayList<CATEGORY_MAIN_3G>();
		String SQL = "SELECT ALL [IDENTIFY_CATEGORY],"
				+"[CATEGORY_TYPE],"
				+"[INTERIMS_VERSION],"
				+"[TABLE_SPEC],"
				+"[TABLE_ID],"
				+"[TABLE_DESC],"
				+"[TESTBAND]"
				+ "FROM [" + DBNAME + "].[dbo].[CATEGORY_3G_MAIN]";			
		try{
			ResultSet rs = stmt.executeQuery(SQL);
			while(rs.next()){
				CATEGORY_MAIN_3G cm_3G = new CATEGORY_MAIN_3G(rs.getString("IDENTIFY_CATEGORY"),
						rs.getString("CATEGORY_TYPE"),
						rs.getString("INTERIMS_VERSION"),
						rs.getString("TABLE_SPEC"),
						rs.getString("TABLE_ID"),
						rs.getString("TABLE_DESC"),
						rs.getString("TESTBAND"));
				
				CM_3G.put(rs.getString("TABLE_ID"), cm_3G);
				CMLIST_3G.add(cm_3G);
			}
		}catch(SQLException exception){
			exception.printStackTrace();
		}
	}
	
	public void loadCD_3G(){
		CD_3G = new HashMap<String, CATEGORY_DETAIL_3G>();
		CDLIST_3G = new ArrayList<CATEGORY_DETAIL_3G>();
		String SQL = "SELECT ALL [IDENTIFY_CATEGORY_D],"
				+"[IDENTIFY_CATEGORY],"
				+"[RELEASE],"
				+"[CATEGORY_FDD_TYPE],"
				+"[CATEGORY],"
				+"[VALID_PALTFORM]"
				+ "FROM [" + DBNAME + "].[dbo].[CATEGORY_3G_DETAIL]";
		try{
			ResultSet rs = stmt.executeQuery(SQL);
			while(rs.next()){
				CATEGORY_DETAIL_3G cd_3G = new CATEGORY_DETAIL_3G(rs.getString("IDENTIFY_CATEGORY_D"),
						rs.getString("IDENTIFY_CATEGORY"),
						rs.getString("RELEASE"),
						rs.getString("CATEGORY_FDD_TYPE"),
						rs.getString("CATEGORY"),
						rs.getString("VALID_PALTFORM"));

				CD_3G.put(rs.getString("IDENTIFY_CATEGORY"), cd_3G);
				CDLIST_3G.add(cd_3G);
			}
		}catch(SQLException exception){
			exception.printStackTrace();
		}		
				
	}
	
	public void loadCM_LTE(){
		CM_LTE = new HashMap<String, CATEGORY_MAIN_LTE>();
		CMLIST_LTE = new ArrayList<CATEGORY_MAIN_LTE>();
		String SQL = "SELECT ALL [IDENTIFY_CATEGORY],"
				+"[CATEGORY_TYPE],"
				+"[INTERIMS_VERSION],"
				+"[TABLE_SPEC],"
				+"[TABLE_ID],"
				+"[TABLE_DESC],"
				+"[SPEC_VERSION],"
				+"[TESTBAND]"
				+ "FROM [" + DBNAME + "].[dbo].[CATEGORY_LTE_MAIN]";			
		try{
			ResultSet rs = stmt.executeQuery(SQL);
			while(rs.next()){
				CATEGORY_MAIN_LTE cm_lte = new CATEGORY_MAIN_LTE(rs.getString("IDENTIFY_CATEGORY"),
						rs.getString("CATEGORY_TYPE"),
						rs.getString("INTERIMS_VERSION"),
						rs.getString("TABLE_SPEC"),
						rs.getString("TABLE_ID"),
						rs.getString("TABLE_DESC"),
						rs.getString("SPEC_VERSION"),
						rs.getString("TESTBAND"));
							
				CM_LTE.put(rs.getString("TABLE_ID"), cm_lte);
				CMLIST_LTE.add(cm_lte);
			}
		}catch(SQLException exception){
			exception.printStackTrace();
		}
	}
	
	public void loadCD_LTE(){
		CD_LTE = new HashMap<String, CATEGORY_DETAIL_LTE>();
		CDLIST_LTE = new ArrayList<CATEGORY_DETAIL_LTE>();
		String SQL = "SELECT ALL [IDENTIFY_CATEGORY_D],"
				+"[IDENTIFY_CATEGORY],"
				+"[RELEASE],"
				+"[CATEGORY_FDD_TYPE],"
				+"[CATEGORY],"
				+"[VALID_PALTFORM]"
				+ "FROM [" + DBNAME + "].[dbo].[CATEGORY_LTE_DETAIL]";
		try{
			ResultSet rs = stmt.executeQuery(SQL);
			while(rs.next()){
				CATEGORY_DETAIL_LTE cd_lte = new CATEGORY_DETAIL_LTE(rs.getString("IDENTIFY_CATEGORY_D"),
						rs.getString("IDENTIFY_CATEGORY"),
						rs.getString("RELEASE"),
						rs.getString("CATEGORY_FDD_TYPE"),
						rs.getString("CATEGORY"),
						rs.getString("VALID_PALTFORM"));

				CD_LTE.put(rs.getString("IDENTIFY_CATEGORY"), cd_lte);
				CDLIST_LTE.add(cd_lte);
			}
		}catch(SQLException exception){
			exception.printStackTrace();
		}		
				
	}
	
	//load TC_DIFF
	public void loadTCD_2G(){
		TCD_2G = new HashMap<String, TC_DIFF_2G3GLTE>();
		TCDLIST_2G = new ArrayList<TC_DIFF_2G3GLTE>();
		String SQL = "SELECT ALL [TC_DIFF_SNO],"
				+"[STANDARD],"
				+"[ETSI_TC],"
				+"[ETSI_DESC],"
				+"[OTHER_TC],"
				+"[OTHER_TC_DESC]"
				+ "FROM [" + DBNAME + "].[dbo].[TC_DIFF]";
		try{
			ResultSet rs = stmt.executeQuery(SQL);
			while(rs.next()){
				TC_DIFF_2G3GLTE tc_diff_2G = new TC_DIFF_2G3GLTE(rs.getString("TC_DIFF_SNO"),
						rs.getString("STANDARD"),
						rs.getString("ETSI_TC"),
						rs.getString("ETSI_DESC"),
						rs.getString("OTHER_TC"),
						rs.getString("OTHER_TC_DESC"));

				TCD_2G.put(rs.getString("OTHER_TC" + "TC_DIFF_SNO"), tc_diff_2G);
				TCDLIST_2G.add(tc_diff_2G);
			}
		}catch(SQLException exception){
			exception.printStackTrace();
		}	
	}
	
	//load TC_DIFF_3G
	public void loadTCD_3G(){
		TCD_3G = new HashMap<String, TC_DIFF_2G3GLTE>();
		TCDLIST_3G = new ArrayList<TC_DIFF_2G3GLTE>();
		String SQL = "SELECT ALL [TC_DIFF_SNO],"
				+"[STANDARD],"
				+"[ETSI_TC],"
				+"[ETSI_DESC],"
				+"[OTHER_TC],"
				+"[OTHER_TC_DESC]"
				+ "FROM [" + DBNAME + "].[dbo].[TC_DIFF_3G]";
		try{
			ResultSet rs = stmt.executeQuery(SQL);
			while(rs.next()){
				TC_DIFF_2G3GLTE tc_diff_3G = new TC_DIFF_2G3GLTE(rs.getString("TC_DIFF_SNO"),
						rs.getString("STANDARD"),
						rs.getString("ETSI_TC"),
						rs.getString("ETSI_DESC"),
						rs.getString("OTHER_TC"),
						rs.getString("OTHER_TC_DESC"));

				TCD_3G.put(rs.getString("OTHER_TC" + "TC_DIFF_SNO"), tc_diff_3G);
				TCDLIST_3G.add(tc_diff_3G);
			}
		}catch(SQLException exception){
			exception.printStackTrace();
		}	
	}
	
	//load TC_DIFF_LTE
	public void loadTCD_LTE(){
		TCD_LTE = new HashMap<String, TC_DIFF_2G3GLTE>();
		TCDLIST_LTE = new ArrayList<TC_DIFF_2G3GLTE>();
		String SQL = "SELECT ALL [TC_DIFF_SNO],"
				+"[STANDARD],"
				+"[ETSI_TC],"
				+"[ETSI_DESC],"
				+"[OTHER_TC],"
				+"[OTHER_TC_DESC]"
				+"FROM [" + DBNAME + "].[dbo].[TC_DIFF_LTE]";
		try{
			ResultSet rs = stmt.executeQuery(SQL);
			while(rs.next()){
				TC_DIFF_2G3GLTE tc_diff_LTE = new TC_DIFF_2G3GLTE(rs.getString("TC_DIFF_SNO"),
						rs.getString("STANDARD"),
						rs.getString("ETSI_TC"),
						rs.getString("ETSI_DESC"),
						rs.getString("OTHER_TC"),
						rs.getString("OTHER_TC_DESC"));

				TCD_LTE.put(rs.getString("OTHER_TC" + "TC_DIFF_SNO"), tc_diff_LTE);
				TCDLIST_LTE.add(tc_diff_LTE);
			}
		}catch(SQLException exception){
			exception.printStackTrace();
		}	
	}
	
	//print PROJECT_CODE in 2G, 3G, LTE. e.g. print2GListProtocol("LYW200", "2G");
	public void print2GListProtocol(String project_code, String spec){
		System.out.println(spec);
		System.out.println(project_code);
		for(int i=0;i<TCLIST_LTE.size();i++){
			TCList_LTE tc = TCLIST_LTE.get(i);
			if(spec.equals(tc.getSPEC())&& project_code.equals(tc.getPROJECT_CODE())){
				System.out.println(tc.getSPEC());
				System.out.println(tc.getTC_ID());
				System.out.println(TCLIST_LTE.size());
			}
		}
	}
	
	//update 2G testcase
	public void get2GTestcase(){
		String s = "2G";
		try{
			stmt.executeUpdate("delete from [" + DBNAME + "].[dbo].[TCLIST_2G];");			
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO [" + DBNAME + "].[dbo].[TCLIST_2G] VALUES( ?, ?, ?)");
			for(int i=0;i<TCLIST_LTE.size();i++){
				TCList_LTE tc = TCLIST_LTE.get(i);
				if(s.equals(tc.getSPEC())){
					pstmt.setString(1, tc.getSPEC());
					pstmt.setString(2, tc.getTC_ID());
					pstmt.setString(3, tc.getFINAL_RESULT());
					pstmt.addBatch();
				}
			}
			pstmt.executeBatch();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//update 3G testcase
	public void get3GTestcase(){
		String s = "3G";
		try{
			stmt.executeUpdate("delete from [" + DBNAME + "].[dbo].[TCLIST_3G];");			
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO [" + DBNAME + "].[dbo].[TCLIST_3G] VALUES( ?, ?, ?)");
			for(int i=0;i<TCLIST_LTE.size();i++){
				TCList_LTE tc = TCLIST_LTE.get(i);
				if(s.equals(tc.getSPEC())){
					pstmt.setString(1, tc.getSPEC());
					pstmt.setString(2, tc.getTC_ID());
					pstmt.setString(3, tc.getFINAL_RESULT());
					pstmt.addBatch();
				}
			}
			pstmt.executeBatch();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//update 4G testcase
	public void get4GTestcase(){
		try{
			stmt.executeUpdate("delete from [" + DBNAME + "].[dbo].[TCLIST_4G];");			
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO [" + DBNAME + "].[dbo].[TCLIST_4G] VALUES( ?, ?, ?)");
			for(int i=0;i<TCLIST_LTE.size();i++){
				TCList_LTE tc = TCLIST_LTE.get(i);
				if(tc.getSPEC().startsWith("TS", 0)){
					pstmt.setString(1, tc.getSPEC());
					pstmt.setString(2, tc.getTC_ID());
					pstmt.setString(3, tc.getFINAL_RESULT());
					pstmt.addBatch();
				}
			}
			pstmt.executeBatch();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//load GCF 2G. e.g. loadGCF_2G("LYW200", "2G", single);
	public void loadGCF_2G(String project_code, String spec, String testband){
		String finalresulta = "A";
		String finalresultr = "R";
		String gcf = "GCF";
		int[] SupportFreq = {0, 1, 1, 1};
		int[] SupportFreq1 = {850, 900, 1800, 1900};
		GCFLIST_2G = new ArrayList<GCFTestcase_2G>();
		for(int i=0;i<TCLIST_LTE.size();i++){
			TCList_LTE tc= TCLIST_LTE.get(i);
			if(spec.equals(tc.getClass()) && (finalresulta.equals(tc.getFINAL_RESULT()) || 
					finalresultr.equals(tc.getFINAL_RESULT()))){
				try{
					CATEGORY_MAIN_2G cm = CM_2G.get(tc.getTC_ID());
					CATEGORY_DETAIL_2G cd = CD_2G.get(cm.getIDENTIFY_CATEGORY());
					
					GCFTestcase_2G gcf_2G = new GCFTestcase_2G(tc.getTC_ID(),
							tc.getSPEC(),
							tc.getPROJECT_CODE(),
							tc.getFINAL_RESULT(),
							cm.getTABLE_ID(),
							cm.getCATEGORY_TYPE(),
							cm.getTESTBAND(),  //how to find testband single / all
							cm.getIDENTIFY_CATEGORY(),
							cd.getIDENTIFY_CATEGORY(),
							cd.getCATEGORY_FDD_TYPE(),
							cd.getCATEGORY());				
					GCFLIST_2G.add(gcf_2G);
				}
				catch(NullPointerException e){
					
				}
			}
		}
	}
	/*
	//load GCF 2G
	public void loadGCF_2GG(){
		String s1 = "2G";
		String s2 = "A";
		String s3 = "R";
		GCFLIST_2G = new ArrayList<GCFTestcase_2G>();
		//GCFTestcase_2G gcf_2G = new GCFTestcase_2G();
		for(int i=0;i<TCLIST_LTE.size();i++){
			TCList_LTE tc= TCLIST_LTE.get(i);
			if(s1.equals(tc.getSPEC()) && (s2.equals(tc.getFINAL_RESULT()) || s3.equals(tc.getFINAL_RESULT()))){
				try{
					CATEGORY_MAIN_2G cm = CM_2G.get(tc.getTC_ID());
					CATEGORY_DETAIL_2G cd = CD_2G.get(cm.getIDENTIFY_CATEGORY());
					
					//System.out.println(tc.getTC_ID());
					
					GCFTestcase_2G gcf_2G = new GCFTestcase_2G(tc.getTC_ID(),
							tc.getSPEC(),
							tc.getFINAL_RESULT(),
							cm.getTABLE_ID(),
							cm.getCATEGORY_TYPE(),
							cm.getTESTBAND(),  //how to find testband single / all
							cm.getIDENTIFY_CATEGORY(),
							cd.getIDENTIFY_CATEGORY(),
							cd.getCATEGORY_FDD_TYPE(),
							cd.getCATEGORY());
					
					GCFLIST_2G.add(gcf_2G);
				}
				catch(NullPointerException e){
					//System.out.println("exception");
				}			
			}
		}
	}
	*/
	public void loadGCF_3G(){
		String s1 = "A";
		String s2 = "R";
		GCFLIST_3G = new ArrayList<GCFTestcase_3G>();
		for(int i=0;i<TCLIST_LTE.size();i++){
			TCList_LTE tc= TCLIST_LTE.get(i);
			if((s1.equals(tc.getFINAL_RESULT()) || s2.equals(tc.getFINAL_RESULT())) && (tc.getSPEC().startsWith("TS 26") || tc.getSPEC().startsWith("TS 31") || tc.getSPEC().startsWith("TS 34"))){
				try{
					CATEGORY_MAIN_3G cm = CM_3G.get(tc.getTC_ID());
					CATEGORY_DETAIL_3G cd = CD_3G.get(cm.getIDENTIFY_CATEGORY());
										
					GCFTestcase_3G gcf_3G = new GCFTestcase_3G(tc.getTC_ID(),
							tc.getSPEC(),
							tc.getFINAL_RESULT(),
							cm.getTABLE_ID(),
							cm.getCATEGORY_TYPE(),
							cm.getTESTBAND(),  //how to find testband single / all
							cm.getIDENTIFY_CATEGORY(),
							cd.getIDENTIFY_CATEGORY(),
							cd.getCATEGORY_FDD_TYPE(),
							cd.getCATEGORY());
					
					GCFLIST_3G.add(gcf_3G);
				}
				catch(NullPointerException e){
					//System.out.println("exception");
				}				
			}
		}
	}

	public void loadGCF_LTE(){
		GCFLIST_LTE = new ArrayList<GCFTestcase_LTE>();
		String s1 = "A";
		String s2 = "R";
		for(int i=0;i<TCLIST_LTE.size();i++){
			TCList_LTE tc= TCLIST_LTE.get(i);
			if( (s1.equals(tc.getFINAL_RESULT()) || s2.equals(tc.getFINAL_RESULT()))&& (tc.getSPEC().startsWith("TS 36") || tc.getSPEC().startsWith("TS 37"))){
				try{
					CATEGORY_MAIN_LTE cm = CM_LTE.get(tc.getTC_ID());
					CATEGORY_DETAIL_LTE cd = CD_LTE.get(cm.getIDENTIFY_CATEGORY());
										
					GCFTestcase_LTE gcf_LTE = new GCFTestcase_LTE(tc.getTC_ID(),
							tc.getSPEC(),
							tc.getFINAL_RESULT(),
							cm.getTABLE_ID(),
							cm.getCATEGORY_TYPE(),
							cm.getTESTBAND(),  //how to find testband single / all
							cm.getIDENTIFY_CATEGORY(),
							cd.getIDENTIFY_CATEGORY(),
							cd.getCATEGORY_FDD_TYPE(),
							cd.getCATEGORY());
					
					GCFLIST_LTE.add(gcf_LTE);
				}
				catch(NullPointerException e){
					//System.out.println("exception");
				}
				
			}
		}
	}
	
	
	public void loadPTCRB_LTE(){
	    PTCRBLIST_LTE = new ArrayList<GCFTestcase_LTE>();
	    String s1 = "A";
	    String s2 = "R";
	    String s3 = "NAPRD";
	    String s4 = "2"; //PTCRB CM_type
	    
	    for(int i=0;i<TCLIST_LTE.size();i++){
	        TCList_LTE tc= TCLIST_LTE.get(i);
	         if( (s1.equals(tc.getFINAL_RESULT()) || s2.equals(tc.getFINAL_RESULT()))&& (tc.getSPEC().startsWith("TS 36") || tc.getSPEC().startsWith("TS 37"))){
	            for(int j=0;j<TCDLIST_LTE.size();j++){
	                TC_DIFF_2G3GLTE tcd_lte = TCDLIST_LTE.get(j);
	                if(tcd_lte.getETSI_TC().startsWith(tc.getTC_ID())){
	                    for(int k=0;k<CMLIST_LTE.size();k++){
	                        CATEGORY_MAIN_LTE cm = CMLIST_LTE.get(k);
	                        if((cm.getTABLE_ID().equals(tcd_lte.getOTHER_TC())) && cm.getCATEGORY_TYPE().equals(s4)){
	                            try{
	                                CATEGORY_DETAIL_LTE cd = CD_LTE.get(cm.getIDENTIFY_CATEGORY());
	                                GCFTestcase_LTE gcf_LTE = new GCFTestcase_LTE(tcd_lte.getOTHER_TC(),
	                                 tc.getSPEC(),
	                                 tc.getFINAL_RESULT(),
	                                 tc.getTC_ID(),
	                                 cm.getCATEGORY_TYPE(),
	                                 cm.getTESTBAND(),
	                                 cm.getIDENTIFY_CATEGORY(),
	                                 cd.getIDENTIFY_CATEGORY_D(),
	                                 cd.getCATEGORY_FDD_TYPE(),
	                                 cd.getCATEGORY());
	                                 PTCRBLIST_LTE.add(gcf_LTE);
	                            }
	                            catch(NullPointerException e){
	                                  System.out.println("exception");
	                                  exceptionx = exceptionx + 1;
	                                  System.out.println(exceptionx);
	                            }
	                            finally {
	                                System.out.println("無論如何都會印出的訊息");
	                            }
	                        }
	                    }
	                }
	            }
	         }
	    }
	}
	
	public void getGCFTestcase_2G(){
		try{stmt.executeUpdate("TRUNCATE TABLE [" + DBNAME + "].[dbo].[GCFTCLIST_2GG];");
		PreparedStatement pstmt = con.prepareStatement("INSERT INTO [" + DBNAME + "].[dbo].[GCFTCLIST_2GG] VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");	
		for(int i =0;i<GCFLIST_2G.size();i++){
			GCFTestcase_2G gcf_2G = GCFLIST_2G.get(i);		
			pstmt.setString(1, gcf_2G.getTC_ID());
			pstmt.setString(2, gcf_2G.getSPEC());
			pstmt.setString(3, gcf_2G.getFINAL_RESULT());
			pstmt.setString(4, gcf_2G.getTABLE_ID());
			pstmt.setString(5, gcf_2G.getCATEGORY_TYPE());
			pstmt.setString(6, gcf_2G.getTESTBAND());
			pstmt.setString(7, gcf_2G.getIDENTIFY_CATEGORY_M());
			pstmt.setString(8, gcf_2G.getIDENTIFY_CATEGORY_D());
			pstmt.setString(9, gcf_2G.getCATEGORY_FDD_TYPE());
			pstmt.setString(10, gcf_2G.getCATEGORY());
			pstmt.addBatch();
		}
		pstmt.executeBatch();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void getGCFTestcase_3G(){
		try{stmt.executeUpdate("TRUNCATE TABLE [" + DBNAME + "].[dbo].[GCFTCLIST_3GG];");
		PreparedStatement pstmt = con.prepareStatement("INSERT INTO [" + DBNAME + "].[dbo].[GCFTCLIST_3GG] VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");	
		for(int i =0;i<GCFLIST_3G.size();i++){
			GCFTestcase_3G gcf_3G = GCFLIST_3G.get(i);		
			pstmt.setString(1, gcf_3G.getTC_ID());
			pstmt.setString(2, gcf_3G.getSPEC());
			pstmt.setString(3, gcf_3G.getFINAL_RESULT());
			pstmt.setString(4, gcf_3G.getTABLE_ID());
			pstmt.setString(5, gcf_3G.getCATEGORY_TYPE());
			pstmt.setString(6, gcf_3G.getTESTBAND());
			pstmt.setString(7, gcf_3G.getIDENTIFY_CATEGORY_M());
			pstmt.setString(8, gcf_3G.getIDENTIFY_CATEGORY_D());
			pstmt.setString(9, gcf_3G.getCATEGORY_FDD_TYPE());
			pstmt.setString(10, gcf_3G.getCATEGORY());
			pstmt.addBatch();
		}
		pstmt.executeBatch();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void getGCFTestcase_LTE(){
		try{stmt.executeUpdate("TRUNCATE TABLE [" + DBNAME + "].[dbo].[GCFTCLIST_LTEE];");
		PreparedStatement pstmt = con.prepareStatement("INSERT INTO [" + DBNAME + "].[dbo].[GCFTCLIST_LTEE] VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");	
		for(int i =0;i<GCFLIST_LTE.size();i++){
			GCFTestcase_LTE gcf_LTE = GCFLIST_LTE.get(i);		
			pstmt.setString(1, gcf_LTE.getTC_ID());
			pstmt.setString(2, gcf_LTE.getSPEC());
			pstmt.setString(3, gcf_LTE.getFINAL_RESULT());
			pstmt.setString(4, gcf_LTE.getTABLE_ID());
			pstmt.setString(5, gcf_LTE.getCATEGORY_TYPE());
			pstmt.setString(6, gcf_LTE.getTESTBAND());
			pstmt.setString(7, gcf_LTE.getIDENTIFY_CATEGORY_M());
			pstmt.setString(8, gcf_LTE.getIDENTIFY_CATEGORY_D());
			pstmt.setString(9, gcf_LTE.getCATEGORY_FDD_TYPE());
			pstmt.setString(10, gcf_LTE.getCATEGORY());
			pstmt.addBatch();
		}
		pstmt.executeBatch();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//TODO
	public void getPTCRBTestcase_LTE(){
		try{stmt.executeUpdate("TRUNCATE TABLE [" + DBNAME + "].[dbo].[GCFTCLIST_LTEE];");
		PreparedStatement pstmt = con.prepareStatement("INSERT INTO [" + DBNAME + "].[dbo].[GCFTCLIST_LTEE] VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");	
		for(int i =0;i<PTCRBLIST_LTE.size();i++){
			GCFTestcase_LTE gcf_LTE = PTCRBLIST_LTE.get(i);		
			pstmt.setString(1, gcf_LTE.getTC_ID());
			pstmt.setString(2, gcf_LTE.getSPEC());
			pstmt.setString(3, gcf_LTE.getFINAL_RESULT());
			pstmt.setString(4, gcf_LTE.getTABLE_ID());
			pstmt.setString(5, gcf_LTE.getCATEGORY_TYPE());
			pstmt.setString(6, gcf_LTE.getTESTBAND());
			pstmt.setString(7, gcf_LTE.getIDENTIFY_CATEGORY_M());
			pstmt.setString(8, gcf_LTE.getIDENTIFY_CATEGORY_D());
			pstmt.setString(9, gcf_LTE.getCATEGORY_FDD_TYPE());
			pstmt.setString(10, gcf_LTE.getCATEGORY());
			pstmt.addBatch();
		}
		pstmt.executeBatch();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void getCM_2G(){
		try{
			stmt.executeUpdate("TRUNCATE TABLE [" + DBNAME + "].[dbo].[CATEGORY_MAIN_2GG];");			
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO [" + DBNAME + "].[dbo].[CATEGORY_MAIN_2GG] VALUES(?, ?, ?, ?, ?, ?, ?)");
			for(int i=0;i<CMLIST_2G.size();i++){
				CATEGORY_MAIN_2G cm = CMLIST_2G.get(i);
					pstmt.setString(1, cm.getIDENTIFY_CATEGORY());
					pstmt.setString(2, cm.getCATEGORY_TYPE());
					pstmt.setString(3, cm.getINTERIMS_VERSION());
					pstmt.setString(4, cm.getTABLE_SPEC());
					pstmt.setString(5, cm.getTABLE_ID());
					pstmt.setString(6, cm.getTABLE_DESC());
					pstmt.setString(7, cm.getSPEC_VERSION());
					pstmt.addBatch();
			}
			pstmt.executeBatch();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void printCM(){
		for(int i=0;i<CMLIST_2G.size();i++){
			CATEGORY_MAIN_2G cm = CMLIST_2G.get(i);
				System.out.println(CMLIST_2G.size());
			}
		}
	
	public void printGCF(){
		for(int i=0;i<GCFLIST_2G.size();i++){
			GCFTestcase_2G gcf_2G = GCFLIST_2G.get(i);
			System.out.println(gcf_2G.getTC_ID());
			System.out.println(gcf_2G.getSPEC());
			System.out.println(gcf_2G.getFINAL_RESULT());
			System.out.println(gcf_2G.getTABLE_ID());
			System.out.println(gcf_2G.getCATEGORY_TYPE());
			System.out.println(gcf_2G.getTESTBAND());
			System.out.println(gcf_2G.getIDENTIFY_CATEGORY_M());
			System.out.println(gcf_2G.getIDENTIFY_CATEGORY_D());
			System.out.println(gcf_2G.getCATEGORY_FDD_TYPE());
			System.out.println(gcf_2G.getCATEGORY());
		}
	}
}
