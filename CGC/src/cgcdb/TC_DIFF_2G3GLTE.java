package cgcdb;

public class TC_DIFF_2G3GLTE {
	protected String TC_DIFF_SNO;
	protected String STANDARD;
	protected String ETSI_TC;
	protected String ETSI_DESC;
	protected String OTHER_TC;
	protected String OTHER_TC_DESC;
	
	public TC_DIFF_2G3GLTE(){
		
	}
	 
	public TC_DIFF_2G3GLTE(
			String TC_DIFF_SNO,
			String STANDARD,
			String ETSI_TC,
			String ETSI_DESC,
			String OTHER_TC,
			String OTHER_TC_DESC){
		this.TC_DIFF_SNO = TC_DIFF_SNO;
		this.STANDARD = STANDARD;
		this.ETSI_TC = ETSI_TC;
		this.ETSI_DESC = ETSI_DESC;
		this.OTHER_TC = OTHER_TC;
		this.OTHER_TC_DESC = OTHER_TC_DESC;
	}
	
	public String getTC_DIFF_SNO(){
		return TC_DIFF_SNO;
	}
	
	public String getSTANDARD(){
		return STANDARD;
	}
	
	public String getETSI_TC(){
		return ETSI_TC;
	}
	
	public String getETSI_DESC(){
		return ETSI_DESC;
	}
	
	public String getOTHER_TC(){
		return OTHER_TC;
	}
	
	public String getOTHER_TC_DESC(){
		return OTHER_TC_DESC;
	}
}

