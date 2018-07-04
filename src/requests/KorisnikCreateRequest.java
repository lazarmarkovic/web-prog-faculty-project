package requests;

public class KorisnikCreateRequest {
	private String korisnickoIme;
	private String password;
	private String ime;
	private String prezime;
	private String telefon;
	private String email;
	private String pathSlike;
	private long teritorijaId = -1;
	
	
	public KorisnikCreateRequest() {}


	public String getKorisnickoIme() {
		return korisnickoIme;
	}


	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getIme() {
		return ime;
	}


	public void setIme(String ime) {
		this.ime = ime;
	}


	public String getPrezime() {
		return prezime;
	}


	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}


	public String getTelefon() {
		return telefon;
	}


	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPathSlike() {
		return pathSlike;
	}


	public void setPathSlike(String pathSlike) {
		this.pathSlike = pathSlike;
	}


	public long getTeritorijaId() {
		return teritorijaId;
	}


	public void setTeritorijaId(long teritorijaId) {
		this.teritorijaId = teritorijaId;
	}
	
	
}
