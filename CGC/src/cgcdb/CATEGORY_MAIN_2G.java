package cgcdb;

public class CATEGORY_MAIN_2G extends CATEGORY_MAIN_2G3GLTE{
	private String SPEC_VERSION;
		
	public CATEGORY_MAIN_2G(
			String IDENTIFY_CATEGORY,
			String CATEGORY_TYPE,
			String INTERIMS_VERSION,
			String TABLE_SPEC,
			String TABLE_ID,
			String TABLE_DESC,
			String SPEC_VERSION,
			String TESTBAND){
		this.IDENTIFY_CATEGORY = IDENTIFY_CATEGORY;
		this.CATEGORY_TYPE = CATEGORY_TYPE;
		this.INTERIMS_VERSION = INTERIMS_VERSION;
		this.TABLE_SPEC = TABLE_SPEC;
		this.TABLE_ID = TABLE_ID;
		this.TABLE_DESC = TABLE_DESC;
		this.SPEC_VERSION = SPEC_VERSION;
		this.TESTBAND = TESTBAND;
	}
	
	public String getSPEC_VERSION(){
		return SPEC_VERSION;
	}
}
