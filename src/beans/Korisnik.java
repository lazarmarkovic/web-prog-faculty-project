package beans;

import java.io.Serializable;
import java.util.ArrayList;

import beans.enums.StanjeVolontera;
import beans.enums.Vrsta;



public class Korisnik implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8405023124261784966L;
	private String korisnickoIme;
	private String password;
	private String ime;
	private String prezime;
	private String telefon;
	private String email;
	private String pathSlike;
	private Vrsta vrsta;
	private Teritorija teritorija;
	private StanjeVolontera stanjeVolontera;
	
	private ArrayList<VanrednaSituacija> vanredneSituacije;
	
	
	public Korisnik() {
		vanredneSituacije = new ArrayList<VanrednaSituacija>();
	}

	public Korisnik(String korisnickoIme, String password, String ime, String prezime, String telefon, String email,
			String pathSlike) {
		this();
		this.korisnickoIme = korisnickoIme;
		this.password = password;
		this.ime = ime;
		this.prezime = prezime;
		this.telefon = telefon;
		this.email = email;
		this.pathSlike = pathSlike;
	}

	
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
	
	public ArrayList<VanrednaSituacija> getVanredneSituacije() {
		return vanredneSituacije;
	}

	public void setVanredneSituacije(ArrayList<VanrednaSituacija> vanredneSituacije) {
		this.vanredneSituacije = vanredneSituacije;
	}
	
	public void addVanredvnaSituacija(VanrednaSituacija vs) {
		this.vanredneSituacije.add(vs);
	}
	
	public void removeVanrednaSituacija(VanrednaSituacija vs) {
		this.vanredneSituacije.remove(vs);
	}
	
	public boolean existsVanrednaSituacija(VanrednaSituacija vs) {
		return this.vanredneSituacije.contains(vs);
	}
	
	public boolean existsVanrednaSituacija(long id) {
		for (VanrednaSituacija vs : this.vanredneSituacije) {
			if (vs.getId() == id)
				return true;
		}
		return false;
	}
}
