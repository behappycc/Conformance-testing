package cgcdb;

public class CATEGORY_DETAIL_2G3GLTE implements CATEGORY_DETAIL{
	protected String IDENTIFY_CATEGORY_D;
	protected String IDENTIFY_CATEGORY;
	protected String RELEASE;
	protected String CATEGORY_FDD_TYPE;
	protected String CATEGORY;
	protected String VALID_PALTFORM;
	
	public CATEGORY_DETAIL_2G3GLTE(){
		
	}
	
	public CATEGORY_DETAIL_2G3GLTE(
			String IDENTIFY_CATEGORY_D,
			String IDENTIFY_CATEGORY,
			String RELEASE,
			String CATEGORY_FDD_TYPE,
			String CATEGORY,
			String VALID_PALTFORM){
		this.IDENTIFY_CATEGORY_D = IDENTIFY_CATEGORY_D;
		this.IDENTIFY_CATEGORY = IDENTIFY_CATEGORY;
		this.RELEASE = RELEASE;
		this.CATEGORY_FDD_TYPE = CATEGORY_FDD_TYPE;
		this.CATEGORY = CATEGORY;
		this.VALID_PALTFORM = VALID_PALTFORM;
	}
	
	public String getIDENTIFY_CATEGORY_D(){
		return IDENTIFY_CATEGORY_D;
	}

	public String getIDENTIFY_CATEGORY(){
		return IDENTIFY_CATEGORY;
	}

	public String getRELEASE(){
		return RELEASE;
	}

	public String getCATEGORY_FDD_TYPE(){
		return CATEGORY_FDD_TYPE;
	}

	public String getCATEGORY(){
		return CATEGORY;
	}

	public String getVALID_PALTFORM(){
		return VALID_PALTFORM;
	}

	public void setIDENTIFY_CATEGORY_D(String IDENTIFY_CATEGORY_D){
		this.IDENTIFY_CATEGORY_D = IDENTIFY_CATEGORY_D;
	}

	public void setIDENTIFY_CATEGORY(String IDENTIFY_CATEGORY){
		this.IDENTIFY_CATEGORY = IDENTIFY_CATEGORY;
	}

	public void setRELEASE(String RELEASE){
		this.RELEASE = RELEASE;
	}

	public void setCATEGORY_FDD_TYPE(String CATEGORY_FDD_TYPE){
		this.CATEGORY_FDD_TYPE = CATEGORY_FDD_TYPE;
	}

	public void setCATEGORY(String CATEGORY){
		this.CATEGORY = CATEGORY;
	}

	public void setVALID_PALTFORM(String VALID_PALTFORM){
		this.VALID_PALTFORM = VALID_PALTFORM;
	}

}
