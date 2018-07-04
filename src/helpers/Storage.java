package helpers;

public class Storage {
	private static Storage instance = null;
	private String IMAGES_PATH;
	private String STATIC_PATH;
	private String DATAC_FILE;
	private String ADMIN_FILE;
	
	protected Storage() {}
	
	public static Storage getInstance() {
		if(instance == null) {
			instance = new Storage();
		}
		return instance;
	}

	public String getIMAGES_PATH() {
		return IMAGES_PATH;
	}

	public void setIMAGES_PATH(String iMAGES_PATH) {
		IMAGES_PATH = iMAGES_PATH;
	}

	public String getSTATIC_PATH() {
		return STATIC_PATH;
	}

	public void setSTATIC_PATH(String sTATIC_PATH) {
		STATIC_PATH = sTATIC_PATH;
	}

	public String getDATAC_FILE() {
		return DATAC_FILE;
	}

	public void setDATAC_FILE(String dATAC_FILE) {
		DATAC_FILE = dATAC_FILE;
	}

	public String getADMIN_FILE() {
		return ADMIN_FILE;
	}

	public void setADMIN_FILE(String aDMINS_FILE) {
		ADMIN_FILE = aDMINS_FILE;
	}

}
