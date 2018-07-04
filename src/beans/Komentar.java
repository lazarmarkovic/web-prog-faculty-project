package beans;

import java.io.Serializable;
import java.util.Date;
import helpers.DateTime;


public class Komentar implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8989891835864855706L;
	private long id;
	private String tekst;
	private Date datum;
	private Korisnik korisnik;
	private VanrednaSituacija vs;
	
	
	public Komentar() {
		this.datum = DateTime.getInstance().getDateTime();
	}

	public Komentar(String tekst, Korisnik korisnik, VanrednaSituacija vs, long id) {
		this();
		this.tekst = tekst;
		this.korisnik = korisnik;
		this.vs = vs;
		this.id = id;
	}

	
	public String getTekst() {
		return tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	public Date getDatum() {
		return datum;
	}
	
	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public VanrednaSituacija getVs() {
		return vs;
	}

	public void setVs(VanrednaSituacija vs) {
		this.vs = vs;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}
