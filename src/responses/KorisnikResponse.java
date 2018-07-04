package responses;

import java.util.ArrayList;

import beans.Korisnik;
import beans.Teritorija;
import beans.VanrednaSituacija;
import beans.enums.StanjeVolontera;
import beans.enums.Vrsta;

public class KorisnikResponse {
	private String korisnickoIme;
	private String ime;
	private String prezime;
	private String telefon;
	private String email;
	private String pathSlike;
	private Vrsta vrsta;
	
	private Teritorija teritorija;
	private StanjeVolontera stanjeVolontera;
	private ArrayList<VSResponseWithoutKorisnikAndKomentari> vanredneSituacije;
	
	public KorisnikResponse() {
		vanredneSituacije = new ArrayList<VSResponseWithoutKorisnikAndKomentari>();
	}

	public KorisnikResponse(String korisnickoIme, String ime, String prezime, String telefon, String email,
			String pathSlike, Vrsta vrsta, Teritorija teritorija, StanjeVolontera stanjeVolontera,  ArrayList<VanrednaSituacija> vs) {
		this();
		this.korisnickoIme = korisnickoIme;
		this.ime = ime;
		this.prezime = prezime;
		this.telefon = telefon;
		this.email = email;
		this.pathSlike = pathSlike;
		this.vrsta = vrsta;
		
		if (vrsta.equals(Vrsta.VOLONTER)) {
			this.teritorija = teritorija;
			this.stanjeVolontera = stanjeVolontera;
			for (VanrednaSituacija i : vs) {
				this.vanredneSituacije.add(new VSResponseWithoutKorisnikAndKomentari(i));
			}
		}
	}
	
	public KorisnikResponse(Korisnik k) {
		this(k.getKorisnickoIme(), k.getIme(), k.getPrezime(), k.getTelefon(), k.getEmail(), 
				k.getPathSlike(), k.getVrsta(),k.getTeritorija(), k.getStanjeVolontera(), 
				k.getVanredneSituacije());
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
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

	public Vrsta getVrsta() {
		return vrsta;
	}

	public void setVrsta(Vrsta vrsta) {
		this.vrsta = vrsta;
	}

	public Teritorija getTeritorija() {
		return teritorija;
	}

	public void setTeritorija(Teritorija teritorija) {
		this.teritorija = teritorija;
	}

	public StanjeVolontera getStanjeVolontera() {
		return stanjeVolontera;
	}

	public void setStanjeVolontera(StanjeVolontera stanjeVolontera) {
		this.stanjeVolontera = stanjeVolontera;
	}

	public ArrayList<VSResponseWithoutKorisnikAndKomentari> getVanredneSituacije() {
		return vanredneSituacije;
	}

	public void setVanredneSituacije(ArrayList<VSResponseWithoutKorisnikAndKomentari> vanredneSituacije) {
		this.vanredneSituacije = vanredneSituacije;
	}
	
	
}
