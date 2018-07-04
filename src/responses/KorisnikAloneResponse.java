package responses;

import beans.Korisnik;

public class KorisnikAloneResponse {
	private String korisnickoIme;
	private String pathSlike;
	
	public KorisnikAloneResponse() {}

	public KorisnikAloneResponse(String korisnickoIme, String pathSlike) {
		this();
		this.korisnickoIme = korisnickoIme;
		this.pathSlike = pathSlike;
	}
	
	public KorisnikAloneResponse(Korisnik k) {
		this(k.getKorisnickoIme(), k.getPathSlike());
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getPathSlike() {
		return pathSlike;
	}

	public void setPathSlike(String pathSlike) {
		this.pathSlike = pathSlike;
	}
	
	
	
	
}
