package requests;

public class VSSearchRequest {
	private boolean poDatumu = false; 
	private boolean poHitnosti = false;
	private boolean poTeritoriji= false;;
	private long terId;
	private long hitnost;
	
	private boolean poNazTer = false; 
	private boolean poNazOpst = false;
	private boolean poOpisu = false;
	private boolean poVol = false;
	private boolean poPraz = false;
	private String traziOvo;
	
	
	public boolean isPoDatumu() {
		return poDatumu;
	}
	public void setPoDatumu(boolean poDatumu) {
		this.poDatumu = poDatumu;
	}
	public boolean isPoHitnosti() {
		return poHitnosti;
	}
	public void setPoHitnosti(boolean poHitnosti) {
		this.poHitnosti = poHitnosti;
	}
	public boolean isPoTeritoriji() {
		return poTeritoriji;
	}
	public void setPoTeritoriji(boolean poTeritoriji) {
		this.poTeritoriji = poTeritoriji;
	}
	public long getTerId() {
		return terId;
	}
	public void setTerId(long terId) {
		this.terId = terId;
	}
	public long getHitnost() {
		return hitnost;
	}
	public void setHitnost(long hitnost) {
		this.hitnost = hitnost;
	}
	public boolean isPoNazTer() {
		return poNazTer;
	}
	public void setPoNazTer(boolean poNazTer) {
		this.poNazTer = poNazTer;
	}
	public boolean isPoNazOpst() {
		return poNazOpst;
	}
	public void setPoNazOpst(boolean poNazOpst) {
		this.poNazOpst = poNazOpst;
	}
	public boolean isPoOpisu() {
		return poOpisu;
	}
	public void setPoOpisu(boolean poOpisu) {
		this.poOpisu = poOpisu;
	}
	public boolean isPoVol() {
		return poVol;
	}
	public void setPoVol(boolean poVol) {
		this.poVol = poVol;
	}
	public boolean isPoPraz() {
		return poPraz;
	}
	public void setPoPraz(boolean poPraz) {
		this.poPraz = poPraz;
	}
	public String getTraziOvo() {
		return traziOvo;
	}
	public void setTraziOvo(String traziOvo) {
		this.traziOvo = traziOvo;
	}

}
