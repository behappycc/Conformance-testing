package cgcdb;

public class TCList_LTE {
	private String SEQ;
	private String PROJECT_CODE;
	private String SPEC;
	private String TC_ID;
	private String TC_DESC;
	private String RELEASE;
	private String FINAL_RESULT;
	
	public TCList_LTE(
			String SEQ,
			String PROJECT_CODE,
			String SPEC,
			String TC_ID,
			String TC_DESC,
			String RELEASE,
			String FINAL_RESULT){
		this.SEQ = SEQ;
		this.PROJECT_CODE = PROJECT_CODE;
		this.SPEC = SPEC;
		this.TC_ID = TC_ID;
		this.TC_DESC = TC_DESC;
		this.RELEASE = RELEASE;
		this.FINAL_RESULT = FINAL_RESULT;
	}
	
	public String getSEQ(){
		return SEQ;
	}
	
	public String getPROJECT_CODE(){
		return PROJECT_CODE;
	}
	
	public String getSPEC(){
		return SPEC;
	}
	
	public String getTC_ID(){
		return TC_ID;
	}
	
	public String getTC_DESC(){
		return TC_DESC;
	}
	
	public String getRELEASE(){
		return RELEASE;
	}
	
	public String getFINAL_RESULT(){
		return FINAL_RESULT;
	}
}