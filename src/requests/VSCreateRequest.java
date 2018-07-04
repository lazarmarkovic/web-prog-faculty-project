package requests;


import beans.enums.NivoHitnosti;
import beans.enums.StanjeVSituacije;

public class VSCreateRequest {
	private String nazivMesta;
	private String opstina;
	private String opis;
	private String tacnaLokacija;
	private long teritorijaId;
	private NivoHitnosti nivoHitnosti;
	private String pathSlike;
	private StanjeVSituacije stanje;
	private String volonterKime;
	
	
	public String getNazivMesta() {
		return nazivMesta;
	}
	public void setNazivMesta(String nazivMesta) {
		this.nazivMesta = nazivMesta;
	}
	public String getOpstina() {
		return opstina;
	}
	public void setOpstina(String opstina) {
		this.opstina = opstina;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public String getTacnaLokacija() {
		return tacnaLokacija;
	}
	public void setTacnaLokacija(String tacnaLokacija) {
		this.tacnaLokacija = tacnaLokacija;
	}
	public long getTeritorijaId() {
		return teritorijaId;
	}
	public void setTeritorijaId(long teritorijaId) {
		this.teritorijaId = teritorijaId;
	}
	public NivoHitnosti getNivoHitnosti() {
		return nivoHitnosti;
	}
	public void setNivoHitnosti(NivoHitnosti nivoHitnosti) {
		this.nivoHitnosti = nivoHitnosti;
	}
	public String getPathSlike() {
		return pathSlike;
	}
	public void setPathSlike(String pathSlike) {
		this.pathSlike = pathSlike;
	}
	public StanjeVSituacije getStanje() {
		return stanje;
	}
	public void setStanje(StanjeVSituacije stanje) {
		this.stanje = stanje;
	}
	public String getVolonterKime() {
		return volonterKime;
	}
	public void setVolonterKime(String volonterKime) {
		this.volonterKime = volonterKime;
	}
	
}
