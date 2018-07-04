package responses;

import java.util.Date;

import beans.Komentar;
import beans.Korisnik;
import helpers.DateTime;

public class KomentarResponse {
	private long id;
	private String tekst;
	private String datum;
	private KorisnikAloneResponse korisnik;
	
	public KomentarResponse() {}
	
	public KomentarResponse(long id, String tekst, Date dat, Korisnik korisnik) {
		this();
		this.id = id;
		this.tekst = tekst;
		this.datum = DateTime.getInstance().dateTimeToNormalStr(dat);
		this.korisnik = new KorisnikAloneResponse(korisnik);
	}
	
	
	public KomentarResponse(Komentar k) {
		this(k.getId(), k.getTekst(), k.getDatum(), k.getKorisnik());
	}
	
	
	public String getTekst() {
		return tekst;
	}
	public void setTekst(String tekst) {
		this.tekst = tekst;
	}
	public String getDatum() {
		return datum;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}
	public KorisnikAloneResponse getKorisnik() {
		return korisnik;
	}
	public void setKorisnik(KorisnikAloneResponse korisnik) {
		this.korisnik = korisnik;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
}
