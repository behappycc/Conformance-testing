//encapsulation GCF2G
package cgcdb;

public class GCFTestcase_2G {
	private String TC_ID;
	private String SPEC;
	private String PROJECT_CODE;
	private String FINAL_RESULT;
	private String TABLE_ID;
	private String CATEGORY_TYPE;
	private String TESTBAND;
	private String IDENTIFY_CATEGORY_M;
	private String IDENTIFY_CATEGORY_D;
	private String CATEGORY_FDD_TYPE;
	private String CATEGORY;
	
	public GCFTestcase_2G(){
		
	}
	
	public GCFTestcase_2G(
			String TC_ID,
			String SPEC,
			String PROJECT_CODE,
			String FINAL_RESULT,
			String TABLE_ID,
			String CATEGORY_TYPE,
			String TESTBAND,
			String IDENTIFY_CATEGORY_M,
			String IDENTIFY_CATEGORY_D,
			String CATEGORY_FDD_TYPE,
			String CATEGORY){
		this.TC_ID = TC_ID;
		this.SPEC = SPEC;
		this.PROJECT_CODE = PROJECT_CODE;
		this.FINAL_RESULT = FINAL_RESULT;
		this.TABLE_ID = TABLE_ID;
		this.CATEGORY_TYPE = CATEGORY_TYPE;
		this.TESTBAND = TESTBAND;
		this.IDENTIFY_CATEGORY_M = IDENTIFY_CATEGORY_M;
		this.IDENTIFY_CATEGORY_D = IDENTIFY_CATEGORY_D;
		this.CATEGORY_FDD_TYPE = CATEGORY_FDD_TYPE;
		this.CATEGORY = CATEGORY;
	}
	
	public String getTC_ID(){
		return TC_ID;
	}

	public String getSPEC(){
		return SPEC;
	}
	
	public String getPROJECT_CODE(){
		return PROJECT_CODE;
	}
	
	public String getFINAL_RESULT(){
		return FINAL_RESULT;
	}
	
	public String getTABLE_ID(){
		return TABLE_ID;
	}
	
	public String getCATEGORY_TYPE(){
		return CATEGORY_TYPE;
	}
	
	public String getTESTBAND(){
		return TESTBAND;
	}
	
	public String getIDENTIFY_CATEGORY_M(){
		return IDENTIFY_CATEGORY_M;
	}

	public String getIDENTIFY_CATEGORY_D(){
		return IDENTIFY_CATEGORY_D;
	}

	public String getCATEGORY_FDD_TYPE(){
		return CATEGORY_FDD_TYPE;
	}

	public String getCATEGORY(){
		return CATEGORY;
	}
		
	public void setTC_ID(String TC_ID){
		this.TC_ID = TC_ID;
	}

	public void setSPEC(String SPEC){
		this.SPEC = SPEC;
	}
	
	public void setPROJECT_CODE(String PROJECT_CODE){
		this.PROJECT_CODE = PROJECT_CODE;
	}
	
	public void setFINAL_RESULT(String FINAL_RESULT){
		this.FINAL_RESULT = FINAL_RESULT;
	}
	
	public void setTABLE_ID(String TABLE_ID){
		this.TABLE_ID = TABLE_ID;
	}
	
	public void setCATEGORY_TYPE(String CATEGORY_TYPE){
		this.CATEGORY_TYPE = CATEGORY_TYPE;
	}
	
	public void setTESTBAND(String TESTBAND){
		this.TESTBAND = TESTBAND;
	}
	
	public void setIDENTIFY_CATEGORY_M(String IDENTIFY_CATEGORY_M){
		this.IDENTIFY_CATEGORY_M = IDENTIFY_CATEGORY_M;
	}

	public void setIDENTIFY_CATEGORY_D(String IDENTIFY_CATEGORY_D){
		this.IDENTIFY_CATEGORY_D = IDENTIFY_CATEGORY_D;
	}

	public void setCATEGORY_FDD_TYPE(String CATEGORY_FDD_TYPE){
		this.CATEGORY_FDD_TYPE = CATEGORY_FDD_TYPE;
	}

	public void setCATEGORY(String CATEGORY){
		this.CATEGORY = CATEGORY;
	}
}

