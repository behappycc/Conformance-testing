//method for 2G, 3G, LTE
package cgcdb;

public class CATEGORY_MAIN_2G3GLTE implements CATEGORY_MAIN{
	protected String IDENTIFY_CATEGORY;
	protected String CATEGORY_TYPE;
	protected String INTERIMS_VERSION;
	protected String TABLE_SPEC;
	protected String TABLE_ID;
	protected String TABLE_DESC;
	protected String TESTBAND;
	
	public CATEGORY_MAIN_2G3GLTE(){
		
	}
	
	public CATEGORY_MAIN_2G3GLTE(
			String IDENTIFY_CATEGORY,
			String CATEGORY_TYPE,
			String INTERIMS_VERSION,
			String TABLE_SPEC,
			String TABLE_ID,
			String TABLE_DESC,
			String TESTBAND
			){
		this.IDENTIFY_CATEGORY = IDENTIFY_CATEGORY;
		this.CATEGORY_TYPE = CATEGORY_TYPE;
		this.INTERIMS_VERSION = INTERIMS_VERSION;
		this.TABLE_SPEC = TABLE_SPEC;
		this.TABLE_ID = TABLE_ID;
		this.TABLE_DESC = TABLE_DESC;
		this.TESTBAND = TESTBAND;
	}
	
	public String getIDENTIFY_CATEGORY(){
		return IDENTIFY_CATEGORY;
	}
	
	public String getCATEGORY_TYPE(){
		return CATEGORY_TYPE;
	}
	
	public String getINTERIMS_VERSION(){
		return INTERIMS_VERSION;
	}
	
	public String getTABLE_SPEC(){
		return TABLE_SPEC;
	}
	
	public String getTABLE_ID(){
		return TABLE_ID;
	}
	
	public String getTABLE_DESC(){
		return TABLE_DESC;
	}
	
	public String getTESTBAND(){
		return TESTBAND;
	}

}


