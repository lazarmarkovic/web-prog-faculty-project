package requests;

public class KomentarCreateRequest {
	private String tekst;
	private String korisnikKime;
	private long vsId;

	
	public KomentarCreateRequest() {}

	public String getTekst() {
		return tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	public String getKorisnikKime() {
		return korisnikKime;
	}

	public void setKorisnikKime(String korisnikKime) {
		this.korisnikKime = korisnikKime;
	}

	public long getVsId() {
		return vsId;
	}

	public void setVsId(long vsId) {
		this.vsId = vsId;
	}
	
}
