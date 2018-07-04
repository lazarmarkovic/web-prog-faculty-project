package responses;

public class ImageUploadResponse {
	private String fileName;
	
	public ImageUploadResponse() {}

	public ImageUploadResponse(String fileName) {
		this();
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
